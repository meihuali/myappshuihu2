package com.example.xiao.myappshuihu.fragmengt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.dialog.PopupWindowRigth;
import com.example.xiao.myappshuihu.entity.HuoQuSheBeiBean;
import com.example.xiao.myappshuihu.entity.ZDYData;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.view.ScreenUtils;
import com.example.xiao.myappshuihu.view.SlidingArcView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

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
    private List<HuoQuSheBeiBean.DataBean> list;
    private String machineids;

    //容器
    public List<View> mLists = new ArrayList<>();
    private ViewPager mViewpage;
    private View view;
    private View view1,view2,view3;
    private ImageView img_shuihu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_shouye, container, false);
        initRequest();
        initView(view);
        initLinestener();
        return view;
    }

    /*点击事件*/
    private void initLinestener() {
        /*        qtView.setQtScrollListener(new SlidingArcView.QTScrollListener() {
            @Override
            public void onSelect(View v, int index) {
                Toast.makeText(getApplicationContext(),"选中条目"+index,Toast.LENGTH_SHORT).show();
            }
        });*/
        qtView.setQtItemClickListener(new SlidingArcView.QTItemClickListener() {
            @Override
            public void onClick(View v, int index) {
                if (isFristr) {
                    Toast.makeText(getActivity(), "点击选中条目" + index, Toast.LENGTH_SHORT).show();
                    ZDYData zdydata = mList.get(index);
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

                    String url = "http://gkettle.sunyie.com/teapot/heat/machineid/" + machineid + "/appid/" + appi + "/temp/" + wendumama + "/boil/" + feiteng + "/purify/" + jinghua + "/keepwarm/" + baowen + "/heattime/" + 0 + "/costtime/" + 180 + "/week/" + 0000000 + "/startremind/1/endremind/" + 1 + "/nowaterremind/" + 1 + "/nowaterremindthreshold/" + zhongliang;
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
                    machineid = ShareUtils.getString(getActivity(),"micID","");
                    String appid = ToolsGetAppId.getinitAppId(getActivity());
                    String url = "http://gkettle.sunyie.com/teapot/stopheat/machineid/"+machineid+"/appid/"+appid;
                    OkGo.get(url)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    L.e("停止烧水结果 "+s);
                                }

                                @Override
                                public void onError(Call call, Response response, Exception e) {
                                    super.onError(call, response, e);
                                    ToastUtil.Short(getActivity(),"停止按钮接口请求失败");
                                }
                            });
                    isFristr = true;
                }

            }
        });
    }

    // private String titles[] = {"养生汤", "花茶", "煮沸", "水果茶", "默认"};
    // private int src[] = {R.drawable.yangshengtang2, R.drawable.huacha2, R.drawable.zhufei2, R.drawable.shuiguicha2, R.drawable.i5};

    private void initView(View view) {

        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        qtView = (SlidingArcView) view.findViewById(R.id.view);

        machineid = ShareUtils.getString(getActivity(), "micID", "");
        mList = ZDYDataModel.getInstance().getAlermList(1, machineid);
        qtView.setData(mList);


    }

    /*
    * 进来该界面的时候去请求
    * */
    private void initRequest() {
        //初始化viewpage
        mViewpage = (ViewPager) view.findViewById(R.id.mViewPager);

        String appid = ToolsGetAppId.getinitAppId(getActivity());
        String url = ConfigUtils.SHUIHU_ZONG_JIEKOU+"/app/getmachinelist/appid/"+appid+"/page/1/pagesize/500";
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("获取绑定设备 "+s);
                        Gson gson = new Gson();
                        try {
                            HuoQuSheBeiBean hqsbb =  gson.fromJson(s, HuoQuSheBeiBean.class);
                            list = hqsbb.getData();

                            for (int i = 0; i < list.size(); i++) {
                                //获取水壶是否在线
                                String isonline = list.get(i).getIsonline();
                                //获取设备号
                                machineids =  list.get(i).getMachineid();
                                View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.pager_item_one, null,false);
                                img_shuihu = (ImageView) view1.findViewById(R.id.img_shuihu);
                                mLists.add(view1);
                            }
                            ShareUtils.putString(getActivity(),"micID",machineids);
                            L.e("解析出集合个数 "+mLists.size());
                            //设置page adapter
                            mViewpage.setAdapter(new GuideAdapter(mLists));


                        } catch (Exception e) {
                            ToastUtil.Short(getActivity(),"获取设备列表解析失败");
                        }

                    }
                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getActivity(),"请求绑定设备列表失败");
                    }
                });
        /*
        * viewapge滑动监听
        * */
        mViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                String zaixian = "online";
                String buzaixian = "offline";
                String isonline =  list.get(position).getIsonline();
                L.e("滑动的位子  "+position    +"测试水壶是否在线 " +isonline);
                if (!isonline.equals(zaixian)) {
                    img_shuihu.getBackground().setAlpha(100);
                }
                // 获取滑动的数据源 的micdID 然后查询数据 更换主界面下面 对应的 圆弧按钮
                String huadongGetID = list.get(position).getMachineid();
                L.e("获取滑动后获取水壶设备号 "+huadongGetID);
                mList = ZDYDataModel.getInstance().getAlermList(1, huadongGetID);
                qtView.setData(mList);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class GuideAdapter extends PagerAdapter {
        private List<View> lists;
        public GuideAdapter(List<View> list){
            this.lists = list;
        }

        @Override
        public int getCount() {
            L.e("测试  "+lists.size());
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
}
