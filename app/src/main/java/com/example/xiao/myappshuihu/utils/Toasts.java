package com.example.xiao.myappshuihu.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.utils
 * 文件名：Toas
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/10 0010 上午 11:33
 * 描述：TODO
 */
public class Toasts {

    public static void makeTexts(Context context,String text) {
        android.widget.Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
}
