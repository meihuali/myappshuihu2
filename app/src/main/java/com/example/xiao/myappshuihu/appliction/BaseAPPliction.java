package com.example.xiao.myappshuihu.appliction;

import android.app.Application;




/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.appliction
 * 文件名：BaseAPPliction
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 下午 6:32
 * 描述：TODO
 */
public class BaseAPPliction extends Application {
    private static BaseAPPliction mInstance;

    //创建
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        mInstance = this;


    }


    public static BaseAPPliction getInstance() {
        if (mInstance == null) {
            mInstance = new BaseAPPliction();
        }
        return mInstance;
    }






}
