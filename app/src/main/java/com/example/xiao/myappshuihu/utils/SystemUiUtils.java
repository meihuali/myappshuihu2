package com.example.xiao.myappshuihu.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.lang.reflect.Field;

/*
 *  项目名：  MojiWeather
 *  包名：    com.liuguilin.mojiweather.utils
 *  文件名:   SystemUiUtils
 *  创建者:   LiuGuiLinAndroid
 *  创建时间:  2017/3/18 11:29
 *  描述：    系统ui工具类
    //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
 */
public class SystemUiUtils {
		/*
		* 设置透明状态 这个方法是老刘写的沉浸式状态栏
		* 吧这个类放到自己项目中· 然后在 values 下面的 styles下面 的主题中
		* 把下面这两句话的颜色 改成自定一定的颜色就可以了·然后在每个布局中写的时候加那个顶部的
		* 返回箭头的那个RelativeLayout 布局的时候·调用自己写的 背景色就可以了
		*   <item name="colorPrimary">@color/main_statusColor</item>
        *   <item name="colorPrimaryDark">@color/main_statusColor</item>
		* */

    public static void showWindowUi(Activity activity) {
//        activity.requestWindowFeature(Window.FEATURE_NO_TITLE); //这句好不需要·
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
	
	    /**
     * 设置沉浸式状态栏  这个是copy的 别人的·不好用
     */
/*    protected void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final ViewGroup linear_bar = (ViewGroup) activity.findViewById(R.id.rl_title);
            final int statusHeight = getStatusBarHeight(activity);
            linear_bar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = linear_bar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) linear_bar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    linear_bar.setLayoutParams(params);
                }
            });
        }
    }*/
	
	
	    /**
     * 获取状态栏的高度  这个也是copy 的别人的也不好用
     * @return
     */
   /* protected int getStatusBarHeight(Activity activity){
        try
        {
            Class<?> c=Class.forName("com.android.internal.R$dimen");
            Object obj=c.newInstance();
            Field field=c.getField("status_bar_height");
            int x=Integer.parseInt(field.get(obj).toString());
            return  activity.getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;*/
    }


