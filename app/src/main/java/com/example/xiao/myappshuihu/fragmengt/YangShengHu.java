package com.example.xiao.myappshuihu.fragmengt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AnimationAdapterYSH;
import com.example.xiao.myappshuihu.dialog.PopupWindowUtils;
import com.example.xiao.myappshuihu.entity.LunBoImageBean;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoBean;
import com.example.xiao.myappshuihu.ui.WebViewActivity;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.GliderImagsLoader;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.NetWorkUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.http.VolleyError;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：YangShengHu
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 5:49
 * 描述：TODO
 */
public class YangShengHu extends Fragment implements OnBannerListener {

    //声明自定义轮播控件
    private Banner banner;
    //声明recycleView
    private RecyclerView mRecyclerView;
    //声明 adapter
    private AnimationAdapterYSH mAnimationAdapter;
    //声明假数据 集合
    List<ShangChenLiBiaoBean> lists = new ArrayList<>();
    //这里是真数据 集合 网络请求得到
    private List<LunBoImageBean.DataBean> list1 = new ArrayList<>();
    //轮播图 解析出来的那个URl  封装的 实体
    private List<String> listImage = new ArrayList<>();
    //商品列表 集合
    private List<ShangPinLieBiaoBean.DataBean> shpoinglist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_yangshenghu, container, false);
        initView(view);
        initData(view);
//        判断是否有开启网络
/*
        if (NetWorkUtils.isNetworkConnected(getActivity())) {

        } else {
            Toasts.makeTexts(getActivity(),"请开启网络···");
        }
*/

        initDataShoping();
        return view;
    }
    /*商品列表网络请求接口*/
    private void initDataShoping() {
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("养生壶商品列表 "+t);
                //解析 商品列表JSON
                parsers(t);
            }
        })        .url(ConfigUtils.CONFIG+ConfigUtils.SOPING_HOU_ZHUI) //接口地址
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(0)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(RxVolley.ContentType.FORM)
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }
    /*解析商品列表*/
    private void parsers(String t) {
        Gson gson = new Gson();
        try {
            ShangPinLieBiaoBean splbb = gson.fromJson(t, ShangPinLieBiaoBean.class);
            String statatus = splbb.getStatus();
            if (statatus.equals("1")) {
                List<ShangPinLieBiaoBean.DataBean> shangpingjiexi = splbb.getData();
                shpoinglist.clear();
                shpoinglist.addAll(shangpingjiexi);
                // 这里开始设置 适配器 adapter
                initAdapter(shpoinglist);
            } else {
                Toasts.makeTexts(getActivity(),"商品列表status出错");
            }

        } catch (Exception e) {
            Toasts.makeTexts(getActivity(),"养生壶商品列表解析出错");
        }


    }

    /*设置图片轮播的数据*/
    private void initData(View view) {
        String lujin = "Healthkettle/image.php/type/1";
        String url = ConfigUtils.CONFIG+lujin;
        /*图片轮播网络请求*/
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("lunbo  "+t);
                //解析
                try {
                    parser(t);
                } catch (Exception e) {
                    Toasts.makeTexts(getActivity(),"养生壶接口解析失败");
                }
            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                Toasts.makeTexts(getActivity(),"网络请求失败···");
                //加载图片轮播 请求失败再次加载
//                loadeImagesFor();
            }
        })
                .url(ConfigUtils.CONFIG+lujin) //接口地址
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(0)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(RxVolley.ContentType.FORM)
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }
    /*解析数据源呈现轮播图片*/
    private void parser(String t) {
        Gson gson = new Gson();
        LunBoImageBean lbib = gson.fromJson(t, LunBoImageBean.class);
        String  status = lbib.getStatus();
        if (status.equals("1")) {
            List<LunBoImageBean.DataBean> list2 = lbib.getData();
            list1.addAll(list2);
            //简单实用banner
            for (int i = 0; i < list1.size(); i++) {
                String url = list1.get(i).getImg();
                String urls = ConfigUtils.LU_BO_IMAGE+url;
                listImage.add(urls);
            }
            //加载图片轮播
            loadeImagesFor(listImage);

        }
    }
    /*加载图片轮播*/
    private void loadeImagesFor(List<String> listImage) {
  /*      String url= "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1495442349&di=e2d8708ace7837a9977456156d93cd76&src=http://imgq.duitang.com/uploads/item/201412/31/20141231221103_EjHjV.thumb.700_0.jpeg";
        List<String> list = new ArrayList<>();
        list.add(url);*/
        //加载轮播图片  listImage
        L.e("图片路径 "+listImage);
        banner.setImages(listImage)
                .setImageLoader(new GliderImagsLoader())
                .setOnBannerListener(this)
                .start();
    }

    /*初始化控件*/
    private void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        //初始化recycleView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
    /*设置adapter*/
    private void initAdapter(final List<ShangPinLieBiaoBean.DataBean> shpoinglist) {
        //设置适配器
        L.e("商品列表 "+shpoinglist);
        mAnimationAdapter = new AnimationAdapterYSH(R.layout.shangchengliebiaoysh_item,shpoinglist,getActivity());
        mRecyclerView.setAdapter(mAnimationAdapter);
//        这一句是开启 item 动画
        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

                /*给item 子控件设置监听*/
        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId()) {
                    /*子控件点击事件*/
                    case R.id.imge_gouwuche:
                        //点击获取当前·被点击的 对象
                        ShangPinLieBiaoBean.DataBean splb =  shpoinglist.get(position);
                        /*点击按钮 显示 popupWindow 然后将 被点击的对象 传递过去*/
                        PopupWindowUtils pwu = new PopupWindowUtils(getActivity(),splb);
                        pwu.showPopupWindow();
                        break;

                    //点击总item 要做的事情
                    case R.id.ll_layout_item:
                        try {
                              /*跳转到详细网页*/
                            ShangPinLieBiaoBean.DataBean splb1 =  shpoinglist.get(position);
                            //获取淘宝地址
                            String weburl = splb1.getUrl();
                            //获取该商品的图片
                            String shangpingIMG = splb1.getImg();

                            Intent intent = new Intent(getActivity(),WebViewActivity.class);
                            //回去当前被点击的对象的金额
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("shangpingduixiang",splb1);
                            intent.putExtras(bundle);
                            intent.putExtra("weburl",weburl);
                            intent.putExtra("shangpingIMG", shangpingIMG);
                            startActivity(intent);
                        } catch (Exception e) {
                            ToastUtil.Short(getActivity(),"对象为空");
                        }

                        break;
                }
            }
        });

    }

    /*这个方法是setOnBannerListener 的点击事件回调 */
    @Override
    public void OnBannerClick(int position) {
        //        Toast.makeText(getActivity(),"你点击了 "+position,Toast.LENGTH_SHORT).show();
    }

    /*如果你需要更好的体验 可以在 onsatrt 的时候去 启动轮播*/
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.start();

    }

}
