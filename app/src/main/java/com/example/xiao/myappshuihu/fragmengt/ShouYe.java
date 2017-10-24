package com.example.xiao.myappshuihu.fragmengt;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anonymous.greendao.model.NZYDataModel;
import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.dialog.PopupWindowRigth;
import com.example.xiao.myappshuihu.entity.HuoQuSheBeiBean;
import com.example.xiao.myappshuihu.entity.MdateBean;
import com.example.xiao.myappshuihu.entity.ZDYData;
import com.example.xiao.myappshuihu.ui.SplashActivity;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.CropCircleTransformation;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.StringUtil;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.view.ScreenUtils;
import com.example.xiao.myappshuihu.view.SlidingArcView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import me.khrystal.library.widget.CircleRecyclerView;
import me.khrystal.library.widget.CircularHorizontalModeBTT;
import me.khrystal.library.widget.ItemViewMode;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：ShouYe
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 4:36
 * 描述：TODO
 */
public class ShouYe extends Fragment implements View.OnClickListener {
    private Button btn_add;
    private SlidingArcView qtView;
    private List<ZDYData> mList;
    private String machineid;
    private String wendumama;
    private boolean isFristr = true;
    private List<HuoQuSheBeiBean.DataBean> list = new ArrayList<>();
    private String machineids;

    //容器
    public List<View> mLists = new ArrayList<>();
    private ViewPager mViewpage;
    private View view;
    private View view1; //,view2;,view3;
    private ImageView img_shuihu;
    private List<ZDYData> mList_ceshi_2;
    GuideAdapter guideAdapter;
    private List<ZDYData> getmListSqiteDB;

    /*主界面圆弧控件的 属性*/
    private CircleRecyclerView mCircleRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ItemViewMode mItemViewMode;
    private List<Integer> mImgList;
    private boolean mIsNotLoop;

    //测试数据源 假数据
    private List<MdateBean> list_b = new ArrayList<>();
    private MdateBean mb;
    private String typeMicid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shouye, container, false);
        requestPermission();
        // initRequest();

        initView(view);

        return view;
    }






    private void initView(View view) {
        //这里初始化圆弧recycleview 自定义控件
        mCircleRecyclerView = (CircleRecyclerView) view.findViewById(R.id.circle_rv);
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        mItemViewMode = new CircularHorizontalModeBTT(200, false);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode);
        mCircleRecyclerView.setNeedCenterForce(true);
        mCircleRecyclerView.setNeedLoop(!mIsNotLoop);

        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        machineid = ShareUtils.getString(getActivity(), "micID", "");

    }

    static class A extends RecyclerView.Adapter<VH> {
        //就是这个adapter
        //
        public interface OnItemClickListener {
            void onItemClcik(View view,int position);

        }
        private OnItemClickListener OnItemClickListener;
        public void setOnItemClickListener(OnItemClickListener OnItemClickListener){
            this.OnItemClickListener = OnItemClickListener;

        }


        private List<ZDYData> list_a;
        public A(List<ZDYData> getmListSqiteDB) {
            this.list_a = getmListSqiteDB;
        }

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            CircleRecyclerView parent1 = (CircleRecyclerView) parent;

            VH h = null;
            if (parent1.getLayoutManager().canScrollHorizontally()) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_h, parent, false);
                h = new VH(view);

            }
            return h;
        }

        @Override
        public void onBindViewHolder(final VH holder, final int position) {

            Context context = holder.itemView.getContext();
            holder.tv.setText(list_a.get(position % list_a.size()).getZDY_NAME());

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
        mViewpage = (ViewPager) view.findViewById(R.id.mViewPager);

        String appid = ToolsGetAppId.getinitAppId(getActivity());
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
                                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.pager_item_one, null, false);
                                img_shuihu = (ImageView) view1.findViewById(R.id.img_shuihu);
                                mLists.add(view1);
                                //获取水壶是否在线
                                String isonline = list.get(i).getIsonline();
                                if (isonline.equals(buzaixian)) {
                                    img_shuihu.getBackground().setAlpha(100);
                                } else {
                                    img_shuihu.getBackground().setAlpha(255);
                                }
                            }
                        //    ShareUtils.putString(getActivity(), "micID", machineids);
                            L.e("解析出集合个数 " + mLists.size());
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
                            ToastUtil.Short(getActivity(), "获取设备列表解析失败");
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getActivity(), "请求绑定设备列表失败");
                    }
                });
        /*
        * viewapge滑动监听
        * */
        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                L.e("滑动 " + position);
            }

            @Override
            public void onPageSelected(int position) {
                L.e("滑动 " + "onPageSelected" + position);

                String zaixian = "online";
                String buzaixian = "offline";
                String isonline =  list.get(position).getIsonline();
                typeMicid = list.get(position).getMachineid();
                L.e("滑动后的设备号：" +typeMicid);
                if (!isonline.equals(zaixian)) {
                    img_shuihu.getBackground().setAlpha(100);
                } else {
                    img_shuihu.getBackground().setAlpha(255);
                }

                // 获取滑动的数据源 的micdID 然后查询数据 更换主界面下面 对应的 圆弧按钮
//                String huadongGetID = list.get(position).getMachineid();
                ShareUtils.putString(getActivity(),"micID",typeMicid);

                //这里每次滑动后去数据库取出 对于的水壶下面的圆弧控件上的参数
                // mList = ZDYDataModel.getInstance().getAlermList(1, huadongGetID);
                mList  = ZDYDataModel.getInstance().getAlermList(1, typeMicid);
                if (!mList.isEmpty()) {

                    getmListSqiteDB.clear();
                    getmListSqiteDB.addAll(mList);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
            A adapter = new A(getmListSqiteDB);

            adapter.setOnItemClickListener(new A.OnItemClickListener() {
                @Override
                public void onItemClcik(View view, int position) {
                    ToastUtil.Short(view.getContext(),position + "");
                    //试下点击效果
                    //点击圆弧 烧水
                    int pos = position;
                    shrxsytes(pos);
                }
            });
            mCircleRecyclerView.setAdapter(adapter);

        }


    }


    /*
    * 点击圆弧上的按钮 烧水
    * */
    private void shrxsytes(int index) {
        if (isFristr) {
            Toast.makeText(getActivity(), "点击选中条目" + index, Toast.LENGTH_SHORT).show();
            ZDYData zdydata = getmListSqiteDB.get(index);
            //appid
            String appi = ToolsGetAppId.getinitAppId(getActivity());
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
                            L.e("烧水结果  " + s);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            ToastUtil.Short(getActivity(), "烧水接口请求失败");
                        }
                    });
            isFristr = false;
        } else {
            //回去micid
            machineid = ShareUtils.getString(getActivity(), "micID", "");
            String appid = ToolsGetAppId.getinitAppId(getActivity());
            String url = "http://gkettle.sunyie.com/teapot/stopheat/machineid/" + typeMicid + "/appid/" + appid;
            OkGo.get(url)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(String s, Call call, Response response) {
                            L.e("停止烧水结果 " + s);
                        }

                        @Override
                        public void onError(Call call, Response response, Exception e) {
                            super.onError(call, response, e);
                            ToastUtil.Short(getActivity(), "停止按钮接口请求失败");
                        }
                    });
            isFristr = true;
        }
    }

    class GuideAdapter extends PagerAdapter {
        private List<View> lists;

        public GuideAdapter(List<View> list) {
            this.lists = list;
        }

        @Override
        public int getCount() {
            L.e("测试个数  " + lists.size());
            return lists.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((ViewPager) container).addView(lists.get(position));
            return lists.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(lists.get(position));

            //super.destroyItem(container, position, object);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                PopupWindowRigth pwr = new PopupWindowRigth(getActivity());
                pwr.showPopupWindow(R.id.btn_add);

                break;
        }
    }

    /*
* 动态授权
* */
    private void requestPermission() {
        AndPermission.with(getActivity())
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
                        AndPermission.rationaleDialog(getActivity(), rationale)
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
            if (AndPermission.hasAlwaysDeniedPermission(getActivity(), deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(getActivity(), 300).show();


            }

        }
    };
}
