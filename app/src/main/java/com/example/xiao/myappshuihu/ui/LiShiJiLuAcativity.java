package com.example.xiao.myappshuihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AnimationAdapterYSH;
import com.example.xiao.myappshuihu.adapter.MyLiShiJiLuAdapter;
import com.example.xiao.myappshuihu.entity.LiShiJiLuBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 * 该界面用于呈现 历史记录
 */

public class LiShiJiLuAcativity extends AppCompatActivity implements View.OnClickListener {
    //声明集合用来接收 封装实体
    private List<LiShiJiLuBean.DataBean> liShiJiLuLIST = new ArrayList<>();
    private RecyclerView mRecyclerView;
    //声明adapter
    private MyLiShiJiLuAdapter myLiShiJiLuAdapter;
    private ImageView image_backs;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lishijilu);
        initViews();
        initData();
    }
        /*网络请求历史记录*/
    private void initData() {
      //先获取手机的 设备号
        String appid = ToolsGetAppId.getinitAppId(LiShiJiLuAcativity.this);
        L.e("appid "+appid);
        String APPid = "d7e728b2-2257-4ddc-8e55-4d2b83de17f2";
        String url = ConfigUtils.LI_SHI_JI_LU+APPid+ConfigUtils.LI_SHI_JI_LU_HOUZHUI;
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.e("lishishuju "+t);
                //  解析
                parses(t);
            }


        })  .url(url) //接口地址
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(0)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(RxVolley.ContentType.FORM)
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }
    /*解析访问的 历史记录接口*/
    private void parses(String t) {
        Gson gson = new Gson();
        LiShiJiLuBean liShiJiLu_OBJ = gson.fromJson(t,LiShiJiLuBean.class);
        int status = liShiJiLu_OBJ.getStatus();
        if (status == 1) {
            List<LiShiJiLuBean.DataBean> list = liShiJiLu_OBJ.getData();
            liShiJiLuLIST.addAll(list);
            myLiShiJiLuAdapter.notifyDataSetChanged();
        }

    }

    /*控件初始化*/
    private void initViews() {
        image_backs = (ImageView) findViewById(R.id.image_backs);
        image_backs.setOnClickListener(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // 这里开始设置 适配器 adapter
        initAdapter();
    }

    private void initAdapter() {
        myLiShiJiLuAdapter= new MyLiShiJiLuAdapter(R.layout.lishijilu_item,liShiJiLuLIST);
        mRecyclerView.setAdapter(myLiShiJiLuAdapter);
      // 这一句是开启 item 动画
        myLiShiJiLuAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_backs:
                finish();
            break;
        }
    }
}
