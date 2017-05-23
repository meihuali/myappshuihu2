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
import com.example.xiao.myappshuihu.adapter.ZhuanTiAdapter;
import com.example.xiao.myappshuihu.entity.LunBoImageBean;
import com.example.xiao.myappshuihu.entity.ZhuanTiBean;
import com.example.xiao.myappshuihu.ui.WebViewActivity;
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
 * 文件名：ZhuanTi
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 5:53
 * 描述：TODO
 */
public class ZhuanTi extends Fragment implements OnBannerListener {
    //声明自定义轮播控件
    private Banner banner;
    //声明recycleView
    private RecyclerView mRecyclerView;
    //声明 adapter
    private ZhuanTiAdapter mAnimationAdapter;
    //这里是真数据 集合 网络请求得到
    private List<LunBoImageBean.DataBean> list1 = new ArrayList<>();
    //轮播图 解析出来的那个URl  封装的 实体
    private List<String> listImage = new ArrayList<>();
    //商品列表 集合
    private List<ZhuanTiBean.DataBean> shpoinglist = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_zhuanti, container, false);
        initView(view);
        initData(view);
        initDataShoping();
        return view;
    }
    /*商品列表网络接口*/
    private void initDataShoping() {

        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("shangpinliebiao "+t);
                try {
                    //解析 商品列表JSON
                    parsers(t);

                } catch (Exception e) {
                    Toasts.makeTexts(getActivity(),"专题商品列表接口解析奔溃");
                }



            }
        })        .url(ConfigUtils.CONFIG+ConfigUtils.SOPING_HOU_ZHUANTI_TWO) //接口地址
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
        ZhuanTiBean splbb = gson.fromJson(t, ZhuanTiBean.class);
        String statatus = splbb.getStatus();
        if (statatus.equals("1")) {
            List<ZhuanTiBean.DataBean> shangpingjiexi = splbb.getData();
            shpoinglist.addAll(shangpingjiexi);

        } else {
            Toasts.makeTexts(getActivity(),"商品列表status出错");
        }

    }

    /*轮播图片*/
    private void initData(View view) {
        String lujin = "Healthkettle/image.php/type/3";
        String url = ConfigUtils.CONFIG+lujin;
        /*图片轮播网络请求*/
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("lunbo  "+t);
                try {
                    //解析
                    parser(t);
                } catch (Exception e) {
                    Toasts.makeTexts(getActivity(),"轮播接口解析失败");
                }

            }
        })
                .url(url) //接口地址
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
            mAnimationAdapter.notifyDataSetChanged();
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
    }

    /*控件初始化*/
    private void initView(View view) {
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
        //设置适配器
        mAnimationAdapter = new ZhuanTiAdapter(R.layout.zhuanti_item,shpoinglist,getActivity());
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
                        /*先获取被点击的item 上的所有字段的数据*/
      /*                  String address = lists.get(position).getAddress();
                        String Freeshipping =  lists.get(position).getFreeshipping();
                        String Fukunreshu =  lists.get(position).getFukuanrenshu();
                        String money = lists.get(position).getMoney();
                        String titale = lists.get(position).getTitale();*/

                        /*点击按钮 显示 popupWindow*/
                        ZhuanTiBean.DataBean sclbb = shpoinglist.get(position);
//                        PopupWindowUtils pwu = new PopupWindowUtils(getActivity(),sclbb);
//                        pwu.showPopupWindow();
                        break;

                    //点击总item 要做的事情
                    case R.id.ll_layout_item:
                        /*跳转到详细网页*/
                        startActivity(new Intent(getActivity(), WebViewActivity.class));
                        Toast.makeText(getActivity(),"你点击了 "+position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    public void OnBannerClick(int position) {
        //        Toast.makeText(getActivity(),"你点击了 "+position,Toast.LENGTH_SHORT).show();
    }
}
