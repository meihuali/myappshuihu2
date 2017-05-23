package com.example.xiao.myappshuihu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 项目名：MyAppXiaoYuan
 * 包名：com.example.xiao.myappxiaoyuan.utils
 * 文件名：SetTexts
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 6:32
 * 描述：TODO
 */
public class SetTexts {
    //设置字体
    public static void setFont(Context mContext, TextView textview) {
        Typeface fontType = Typeface.createFromAsset(mContext.getAssets(), "fonts/FONT.TTF");
        textview.setTypeface(fontType);
    }

}
