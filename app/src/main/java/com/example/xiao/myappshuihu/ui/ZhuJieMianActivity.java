package com.example.xiao.myappshuihu.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.Perserent.SwitchStatePerserent;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.GuideAdapter;
import com.example.xiao.myappshuihu.adapter.MyAdapter;
import com.example.xiao.myappshuihu.dialog.PopItemCenterWindow;
import com.example.xiao.myappshuihu.dialog.TipsPopWindow;
import com.example.xiao.myappshuihu.entity.HuoQuSheBeiBean;
import com.example.xiao.myappshuihu.entity.SwitchStateBean;
import com.example.xiao.myappshuihu.entity.ZDYData;
import com.example.xiao.myappshuihu.fragmengt.ShouYe;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.CropCircleTransformation;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.view.IsSwitchStateView;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularHorizontalModeBTT;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.ui
 * 文件名：ZhuJieMianActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/28 0028 10:28
 * 描述：操作设备的界面 也就是主界面·
 */
public class ZhuJieMianActivity extends AppCompatActivity implements View.OnClickListener, IsSwitchStateView {
    private CircleRecyclerView mCircleRecyclerView;
    private CircularHorizontalModeBTT mItemViewMode;
    private LinearLayoutManager mLayoutManager;
    private boolean mIsNotLoop;
    private ViewPager mViewpage;
    private List<HuoQuSheBeiBean.DataBean> list = new ArrayList<>();
    //容器
    public List<View> mLists = new ArrayList<>();
    private String machineids;
    private String typeMicid;
    private ImageView img_shuihu;
    private GuideAdapter guideAdapter;
    private List<ZDYData> getmListSqiteDB;
    private List<ZDYData> mList;
    private boolean isFristr = true;
    private String wendumama;
    private String machineid;
    private ImageView img_back;
    private LinearLayout ll_layoutqiehuan;
    private boolean isType = true;
    private RelativeLayout fl_layout_zhengmian;
    private FrameLayout fl_layout_beimian;
    private LinearLayout ll_layout_setttings;
    private LinearLayout btn_kaiguan;
    private int pos;
    private ImageView img_guan,img_kai;
    private LinearLayout ll_layoutdingshi;
    private LinearLayout ll_layoutlishijilu;
    private LinearLayout ll_layout_saoyisao;
    private LinearLayout ll_layout_xiangqing;
    private A adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caozuoshebei);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.fenhongse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                // .statusBarDarkFont(true,0.2f)    如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        //删除设备以后 再次掉这个方法来刷新界面
        initRequest();
    }

    /*
    * 进来该界面的时候去请求 获取绑定过的所有设备
    * */
    private void initRequest() {
        //初始化viewpage
        mViewpage = (ViewPager) findViewById(R.id.mViewPager);

        final String appid = ToolsGetAppId.getinitAppId(this);
        String url = ConfigUtils.SHUIHU_ZONG_JIEKOU + "/app/getmachinelist/appid/" + appid + "/page/1/pagesize/500";
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        String zaixian = "online";
                        String buzaixian = "offline";

                        L.e("获取绑定设备 " + s);
                        Gson gson = new Gson();
                        try {
                            HuoQuSheBeiBean hqsbb = gson.fromJson(s, HuoQuSheBeiBean.class);
                            list.clear();
                            list.addAll(hqsbb.getData());
                            mLists.clear();
                            for (int i = 0; i < list.size(); i++) {
                                machineids = list.get(i).getMachineid();
                                typeMicid = machineids;
                                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.pager_item_one, null, false);
                                img_shuihu = (ImageView) view1.findViewById(R.id.img_shuihu);
                                mLists.add(view1);
                                //获取水壶是否在线
                                String isonline = list.get(i).getIsonline();
                        /*        if (isonline.equals(zaixian)) {
                                    img_shuihu.getBackground().setAlpha(255);
                                } else {
                                    img_shuihu.getBackground().setAlpha(100);
                                }*/
                            }
                            ShareUtils.putString(getApplicationContext(), "typeMicid", machineids);

                            //设置page adapter
                            if (guideAdapter == null) {
                                guideAdapter = new GuideAdapter(mLists);
                                mViewpage.setAdapter(guideAdapter);
                                //从数据库取出数据 准备显示在顶部的 圆弧控件上
                                getSqliteDb();
                            } else {
                                guideAdapter.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            // ToastUtil.Short(getApplicationContext(), "获取设备列表解析失败");
                            StyledDialog.buildIosAlert("提示", "请返回上一个界面点击加号添加水壶设备", new MyDialogListener() {
                                @Override
                                public void onFirst() {
                                }
                                @Override
                                public void onSecond() {
                                }
                            }).setBtnText("确定", "").show();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(), "请求绑定设备列表失败");
                    }
                });







        /*
        * viewapge滑动监听
        * */
        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               L.e("滑动 " + position);
                String zaixian = "online";
                String buzaixian = "offline";
                String isonline =  list.get(position).getIsonline();
                typeMicid = list.get(position).getMachineid();
                L.e("滑动后的设备号：" +typeMicid);
                if (isonline.equals(zaixian)) {
                    img_shuihu.getBackground().setAlpha(255);
                } else {
                    img_shuihu.getBackground().setAlpha(100);
                }

                // 获取滑动的数据源 的micdID 然后查询数据 更换主界面下面 对应的 圆弧按钮
                ShareUtils.putString(getApplicationContext(),"micID",typeMicid);
                //这里每次滑动后去数据库取出 对于的水壶下面的圆弧控件上的参数
                mList  = ZDYDataModel.getInstance().getAlermList(1, typeMicid);
                L.e("测试集合 "+mList.size());
                if (!mList.isEmpty()) {
                    getmListSqiteDB.clear();
                    getmListSqiteDB.addAll(mList);
                }
                //每次滑动 获取开关状态
                SwitchStatePerserent states = new SwitchStatePerserent(ZhuJieMianActivity.this);
                ToolsGetAppId.getinitAppId(ZhuJieMianActivity.this);
                states.getSwitchState(typeMicid,appid);
            }

            @Override
            public void onPageSelected(int position) {
             //   L.e("滑动 " + "onPageSelected" + position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    /*
    * 左右滑动后 获取开关的状态
    * */
    @Override
    public void showSwitchStateRelust(Object object) {
        SwitchStateBean stateBean = (SwitchStateBean) object;
        String state = stateBean.getData().getState();
        if (state.equals("0")) { // 0表示开关 关闭
            img_guan.setVisibility(View.VISIBLE);
            img_kai.setVisibility(View.GONE);
        } else { //表示开启
            img_guan.setVisibility(View.GONE);
            img_kai.setVisibility(View.VISIBLE);
        }
    }


    /*
    * 这里是请求上面的viewpage 成功以后 取出数据库的数据 然后显示在 圆弧上
    * */
    private void getSqliteDb() {
//        Collections.shuffle(list_b);
//        mCircleRecyclerView.setAdapter(new A(list_b));

        getmListSqiteDB = ZDYDataModel.getInstance().getAlermList(1, machineids);
        L.e("获取数据库的数据 " + getmListSqiteDB.size());
        if (getmListSqiteDB.size() !=0) {
            Collections.shuffle(getmListSqiteDB);
            adapter = new A(getmListSqiteDB);
            //圆弧 item 的 点击事件
            adapter.setOnItemClickListener(new A.OnItemClickListener() {
                @Override
                public void onItemClcik(View view, int position) {
                    //试下点击效果
                    //点击圆弧 烧水
                    pos = position;
                    //  shrxsytes(pos);
                }
            });
            mCircleRecyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    /*
    * 点击圆弧上的按钮 烧水
    * */
    private void shrxsytes(int index) {
        ZDYData zdydata = getmListSqiteDB.get(index);
        //appid
        String appi = ToolsGetAppId.getinitAppId(this);
        //温度
        String wendu = zdydata.ZDY_SW;
        // 80°c
        if (wendu.contains("°c")) {
            String wendus = wendu.substring(0, wendu.indexOf("°"));
            int wenduma = Integer.parseInt(wendus);
            wendumama = wenduma + "C";
        }

        //是否煮沸
        String feiteng = zdydata.ZDY_ISZF;
        // 净化时间
        String jinghua = zdydata.ZDY_TIME1;
        //保温时间
        String baowen = zdydata.ZDY_TIME;
        //加热时间  暂时为0
        //水的重量
        String zhongliang = "0.5L";

        String url = "http://gkettle.sunyie.com/teapot/heat/machineid/" + typeMicid + "/appid/" + appi + "/temp/" + wendumama + "/boil/" + feiteng + "/purify/" + jinghua + "/keepwarm/" + baowen + "/heattime/" + 0 + "/costtime/" + 180 + "/week/" + 0000000 + "/startremind/1/endremind/" + 1 + "/nowaterremind/" + 1 + "/nowaterremindthreshold/" + zhongliang;
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("烧水结果  " +"开始烧水 "+s);
                        img_guan.setVisibility(View.GONE);
                        img_kai.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(), "烧水接口走onError");
                    }
                });
        isFristr = false;
        //保存烧水结果的状态
      //  ShareUtils.putBoolean(getApplicationContext(),"isFristr",isFristr);
    }

    /*
    * 停止烧水
    * */
    public void stopshaoshui() {
        //回去micid
        machineid = ShareUtils.getString(getApplicationContext(), "micID", "");
        String appid = ToolsGetAppId.getinitAppId(this);
        String url = "http://gkettle.sunyie.com/teapot/stopheat/machineid/" + typeMicid + "/appid/" + appid;
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("烧水结果 " + "停止烧水 "+s);
                        img_kai.setVisibility(View.GONE);
                        img_guan.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(), "停止按钮接口走onError了");
                    }
                });
        isFristr = true;
        //保存烧水结果的状态
      //  ShareUtils.putBoolean(getApplicationContext(),"isFristr",isFristr);
    }





    private void initView() {
        ll_layout_xiangqing = (LinearLayout) findViewById(R.id.ll_layout_xiangqing);
        ll_layout_xiangqing.setOnClickListener(this);
        ll_layout_saoyisao = (LinearLayout) findViewById(R.id.ll_layout_saoyisao);
        ll_layout_saoyisao.setOnClickListener(this);
        ll_layoutlishijilu = (LinearLayout) findViewById(R.id.ll_layoutlishijilu);
        ll_layoutlishijilu.setOnClickListener(this);
        ll_layoutdingshi = (LinearLayout)findViewById(R.id.ll_layoutdingshi);
        ll_layoutdingshi.setOnClickListener(this);
        img_guan = (ImageView) findViewById(R.id.img_guan);
        img_kai = (ImageView) findViewById(R.id.img_kai);
        //这里程序进来 取出烧水按钮的 开关 状态
        boolean status = ShareUtils.getBoolean(getApplicationContext(),"isFristr",true);
        if (status == false) {
            img_kai.setVisibility(View.VISIBLE);
            img_guan.setVisibility(View.GONE);
            isFristr = status;
        } else {
            img_guan.setVisibility(View.VISIBLE);
            img_kai.setVisibility(View.GONE);
            isFristr = status;
        }
        btn_kaiguan = (LinearLayout) findViewById(R.id.btn_kaiguan);
        btn_kaiguan.setOnClickListener(this);
        ll_layout_setttings = (LinearLayout) findViewById(R.id.ll_layout_setttings);
        ll_layout_setttings.setOnClickListener(this);
        fl_layout_zhengmian = (RelativeLayout) findViewById(R.id.fl_layout_zhengmian);
        fl_layout_beimian = (FrameLayout) findViewById(R.id.fl_layout_beimian);
        ll_layoutqiehuan = (LinearLayout) findViewById(R.id.ll_layoutqiehuan);
        ll_layoutqiehuan.setOnClickListener(this);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        //这里初始化圆弧recycleview 自定义控件
        mCircleRecyclerView = (CircleRecyclerView)findViewById(R.id.circle_rv);
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mItemViewMode = new CircularHorizontalModeBTT(185, false);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(!mIsNotLoop);

        //圆弧 item滑动 的 时候获取中间位子 每次滑动停下来获取中间的 item 的索引位置
        mCircleRecyclerView.setCurrentItemCallback(new CircleRecyclerView.CurrentItemCallback() {
            @Override
            public void onItemInCenter(View centerItem) {
                int position = (int) centerItem.getTag(R.string.item_position);
                int chiaId = position%getmListSqiteDB.size();
                pos = chiaId;
            }
        });

        /*
        * 圆弧中间那个item的 点击事件
        * */
        mCircleRecyclerView.setOnCenterItemClickListener(new CircleRecyclerView.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClick(View v) {
                int postion = (int) v.getTag(R.string.item_position);
                int chiaId = postion%getmListSqiteDB.size();
                //获取目标温度
                String mubiaowendu =  getmListSqiteDB.get(chiaId).getZDY_SW();
                //获取净化时间
                String jinghuashijian =  getmListSqiteDB.get(chiaId).getZDY_PURIFY_TIME();
                //获取保温时间
                String baowenshijian = getmListSqiteDB.get(chiaId).getZDY_KEEPWARN_TIME();
                //获取上次加热时间
                String shangcijiarenshijian = getmListSqiteDB.get(chiaId).getZDY_IS_BOIL();

                TipsPopWindow tipsPopWindow = new TipsPopWindow(ZhuJieMianActivity.this,mubiaowendu,jinghuashijian,baowenshijian,shangcijiarenshijian);
                tipsPopWindow.showPopupWindow(v);
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            //圆弧正反面切换
            case R.id.ll_layoutqiehuan:
                if (isType) {
                    fl_layout_zhengmian.setVisibility(View.GONE);
                    fl_layout_beimian.setVisibility(View.VISIBLE);
                    isType = false;
                } else {
                    fl_layout_zhengmian.setVisibility(View.VISIBLE);
                    fl_layout_beimian.setVisibility(View.GONE);
                    isType = true;
                }
                break;
            //设置
            case R.id.ll_layout_setttings:
                ToastUtil.Short(getApplicationContext(),"测试下");
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
                break;
            //定时
            case R.id.ll_layoutdingshi:
                startActivity(new Intent(getApplicationContext(),YuYueActivity.class));
                break;
            //历史记录
            case R.id.ll_layoutlishijilu:
                startActivity(new Intent(getApplicationContext(),LiShiJiLuAcativity.class));
                break;
            //扫一扫添加玫瑰茶那种
            case R.id.ll_layout_saoyisao:
                startActivity(new Intent(getApplicationContext(),SaoYiSaoActivity.class));
                break;
            //设备详情
            case R.id.ll_layout_xiangqing:
                startActivity(new Intent(getApplicationContext(),DelteMachineActivity.class));
                break;

            //开关控制烧水
            case R.id.btn_kaiguan:
                // ShareUtils.putInt(getApplicationContext(),"index_postion",pos);
                if (isFristr) {
                    shrxsytes(pos);
                } else {
                    stopshaoshui();
                }
                break;


        }
    }



    static class A extends RecyclerView.Adapter<ZhuJieMianActivity.VH> {
        //就是这个adapter
        //
        public interface OnItemClickListener {
            void onItemClcik(View view, int position);

        }
        private ZhuJieMianActivity.A.OnItemClickListener OnItemClickListener;
        public void setOnItemClickListener(ZhuJieMianActivity.A.OnItemClickListener OnItemClickListener){
            this.OnItemClickListener = OnItemClickListener;

        }


        private List<ZDYData> list_a;
        public A(List<ZDYData> getmListSqiteDB) {
            this.list_a = getmListSqiteDB;
        }

        @Override
        public ZhuJieMianActivity.VH onCreateViewHolder(ViewGroup parent, int viewType) {
            CircleRecyclerView parent1 = (CircleRecyclerView) parent;

            ZhuJieMianActivity.VH h = null;
            if (parent1.getLayoutManager().canScrollHorizontally()) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_h, parent, false);
                h = new ZhuJieMianActivity.VH(view);

            }
            return h;
        }

        @Override
        public void onBindViewHolder(final ZhuJieMianActivity.VH holder, final int position) {

            Context context = holder.itemView.getContext();
            holder.tv.setText(list_a.get(position % list_a.size()).getZDY_NAME());
            holder.itemView.setTag(R.string.item_position, position);

            if (list_a.get(position % list_a.size()).getZDY_IMAGE().contains(".jpg")) {
                Glide.with(context)
                        .load(list_a.get(position % list_a.size()).getZDY_IMAGE())
                        .bitmapTransform(new CropCircleTransformation(context))
                        .into(holder.iv);
            } else {
                Glide.with(context)
                        .load(Integer.parseInt(list_a.get(position % list_a.size()).getZDY_IMAGE()))
                        .bitmapTransform(new CropCircleTransformation(context))
                        .into(holder.iv);
            }


            if (OnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OnItemClickListener.onItemClcik(holder.itemView,position % list_a.size());
                    }
                });
            }

        }

        //是否可以循环 不要写在主页里面 ，通过方法传进来，和数据list一样
        //写适配器不要写成内部类，就算写成内部类也不要直接去引用外部类的成员变量
        //改起来会很麻烦的 嗯好的·

        @Override
        public int getItemCount() {
            return mIsNotLoop ? list_a.size() : Integer.MAX_VALUE;
        }


        private boolean mIsNotLoop;

        public boolean ismIsNotLoop() {
            return mIsNotLoop;
        }

        public void setmIsNotLoop(boolean mIsNotLoop) {
            this.mIsNotLoop = mIsNotLoop;
        }
    }

    static class VH extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView iv;

        public VH(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_text);
            iv = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }


    private void requestPermission() {
        AndPermission.with(getApplicationContext())
                .requestCode(300)
                .permission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .callback(listener)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(getApplicationContext(), rationale)
                                .show();
                    }
                })
                .start();
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。
            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 200) {

            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                // TODO ...
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(getApplicationContext(), deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(ZhuJieMianActivity.this, 300).show();


            }

        }
    };

}
