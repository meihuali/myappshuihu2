package com.example.xiao.myappshuihu.ui;

/*
 *  项目名：  SmartButler 
 *  包名：    com.imooc.smartbutler.ui
 *  文件名:   SplashActivity
 *  创建者:   LGL
 *  创建时间:  2016/10/28 22:51
 *  描述：    闪屏页
 */

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.example.xiao.myappshuihu.MainActivity;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.StaticClass;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import static com.mob.tools.gui.BitmapProcessor.start;


public class SplashActivity extends AppCompatActivity {

    /**
     * 1.延时2000ms
     * 2.判断程序是否第一次运行
     * 3.自定义字体
     * 4.Activity全屏主题
     */

    private TextView tv_splash;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticClass.HANDLER_SPLASH:
                    //判断程序是否是第一次运行
                    if (isFirst()) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    finish();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }
    //初始化View
    private void initView() {
        // 这里动态授权·SD卡 读写权限·


       // 延时2000ms
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 3000);

//        tv_splash = (TextView) findViewById(R.id.tv_splash);
//        //设置字体
//        UtilTools.setFont(this,tv_splash);
    }




    //判断程序是否第一次运行
    private boolean isFirst() {
        boolean isFirst = ShareUtils.getBoolean(this,StaticClass.SHARE_IS_FIRST,true);
        if(isFirst){
            ShareUtils.putBoolean(this,StaticClass.SHARE_IS_FIRST,false);
            //是第一次运行
            return true;
        }else {
            return false;
        }

    }

}
