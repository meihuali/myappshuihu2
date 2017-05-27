package com.example.xiao.myappshuihu.fragmengt;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AnimationAdapter;
import com.example.xiao.myappshuihu.dialog.PopupWindowUtils;
import com.example.xiao.myappshuihu.entity.FenLeiLieBiaoBean;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoBean;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoTwoBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.GliderImagsLoader;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：FenLei
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 5:51
 * 描述：此类上部分 是采用banner 图片自动轮播 自定义控件
 */
public class FenLei extends Fragment implements OnBannerListener{
    //声明自定义轮播控件
    private Banner banner;
    //声明recycleView
    private RecyclerView mRecyclerView;
    //声明 adapter
    private AnimationAdapter mAnimationAdapter;
    //声明假数据 集合
    List<ShangChenLiBiaoBean> list = new ArrayList<>();
    private ImageView imageView;

    //这里是真数据 集合 网络请求得到
    private List<FenLeiLieBiaoBean.DataBean> list1 = new ArrayList<>();
    //轮播图 解析出来的那个URl  封装的 实体
    private List<String> listImage = new ArrayList<>();
    //商品列表 集合
    private List<ShangPinLieBiaoBean.DataBean> shpoinglist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.activity_fenlei, container, false);
        initView(view);
        initData(view);
        initDataShoping();
        return view;
    }
        /*商品列表请求接口*/
    private void initDataShoping() {
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("shangpinliebiao "+t);
                //解析 商品列表JSON
                parsers(t);
            }
        })        .url(ConfigUtils.CONFIG+ConfigUtils.SOPING_HOU_ZHUI_TWO) //接口地址
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
                shpoinglist.addAll(shangpingjiexi);
                mAnimationAdapter.notifyDataSetChanged(); //刷新adapter

            } else {
                Toasts.makeTexts(getActivity(),"商品列表status出错");
            }
        } catch (Exception e) {
            Toasts.makeTexts(getActivity(),"分类列表接口挂了");
        }

    }

    /*设置图片轮播的数据*/
    private void initData(View view) {
//        /*获取本地 封装的 url路径的 图片地址*/
//        String[] urls =  getResources().getStringArray(R.array.jiashujuImages);
//        List list = Arrays.asList(urls);
//        List<?> images=new ArrayList<>();
//        images.addAll(list);
        String lujin = "Healthkettle/image.php/type/2";
        String url = ConfigUtils.CONFIG+lujin;
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("lunbo  "+t);
                //解析
                parser(t);

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
        try {
            FenLeiLieBiaoBean lbib = gson.fromJson(t, FenLeiLieBiaoBean.class);
            String  status = lbib.getStatus();
            if (status.equals("1")) {
                List<FenLeiLieBiaoBean.DataBean> list2 = lbib.getData();
                list1.addAll(list2);

                //简单实用banner
                for (int i = 0; i < list1.size(); i++) {
                    String url = list1.get(i).getImg();
                    String urls = ConfigUtils.LU_BO_IMAGE+url;
                    listImage.add(urls);
                }
                //加载轮播图片
                banner.setImages(listImage)
                        .setImageLoader(new GliderImagsLoader())
                        .setOnBannerListener(this)
                        .start();
            }
        } catch (Exception e) {
            Toasts.makeTexts(getActivity(),"分类界面图片轮播接口挂了");
        }

    }

    /*初始化*/
    private void initView(View view) {
        imageView = (ImageView) view.findViewById(R.id.imge_gouwuche);
        banner = (Banner) view.findViewById(R.id.banner);
        //初始化recycleView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 这里开始设置 适配器 adapter
        initAdapter(view);
    }

    private void initAdapter(View view) {
//        for (int i = 0; i < 30; i++) {
//            ShangChenLiBiaoBean slbb = new ShangChenLiBiaoBean();
//            slbb.setAddress("广州");
//            slbb.setMoney("159");
//            slbb.setFukuanrenshu("4.3万人付款");
//            slbb.setTitale("资金水壶");
//            slbb.setFreeshipping("包邮");
//            list.add(slbb);
//        }
        //设置适配器
        mAnimationAdapter = new AnimationAdapter(R.layout.shangchengliebiao_item,shpoinglist,getActivity());
        mRecyclerView.setAdapter(mAnimationAdapter);
//        这一句是开启 item 动画
        mAnimationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        /*给item 子控件设置监听*/
        mAnimationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.imge_gouwuche:
                        ShangPinLieBiaoBean.DataBean splbtb =  shpoinglist.get(position);
                        PopupWindowUtils pwu = new PopupWindowUtils(getActivity(),splbtb);
                        pwu.showPopupWindow();

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
