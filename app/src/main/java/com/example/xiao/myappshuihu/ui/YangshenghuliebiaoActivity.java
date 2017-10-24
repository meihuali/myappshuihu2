package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.YanshenghuliebBean;
import com.example.xiao.myappshuihu.utils.GliderImagsLoader;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.ui
 * 文件名：YangshenghuliebiaoActivity
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/26 0026 15:44
 * 描述：TODO
 */
public class YangshenghuliebiaoActivity extends AppCompatActivity implements OnBannerListener, View.OnClickListener {
    //声明自定义轮播控件
    private Banner banner;
    private List<YanshenghuliebBean> mlist = new ArrayList<>();
    private ImageView img_close;
    private ImageView img_addKetrt;
    private ImageView img_onclick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_yangshenghuolibiao);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.fenhongse) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();



        initView();
    }

    private void initView() {
        img_onclick = (ImageView) findViewById(R.id.img_onclick);
        img_onclick.setOnClickListener(this);
        img_addKetrt = (ImageView) findViewById(R.id.img_addKetrt);
        img_addKetrt.setOnClickListener(this);
        img_close  =(ImageView) findViewById(R.id.img_close);
        img_close.setOnClickListener(this);
        //声明自定义轮播控件
        banner = (Banner) findViewById(R.id.banner);

        banner.setImages(mlist)
                .setImageLoader(new GliderImagsLoader())
                .setOnBannerListener(this)
                .start();
    }

    /*
     * 这个方法是setOnBannerListener 的点击事件回调
      * */
    @Override
    public void OnBannerClick(int position) {
          Toast.makeText(getApplicationContext(),"你点击了 "+position,Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.img_addKetrt:
                    startActivity(new Intent(getApplicationContext(),AddOldInfoActivity.class));
                break;
                //跳转到操作界面
                case R.id.img_onclick:
                    startActivity(new Intent(getApplicationContext(),ZhuJieMianActivity.class));
                    break;
        }
    }
}
