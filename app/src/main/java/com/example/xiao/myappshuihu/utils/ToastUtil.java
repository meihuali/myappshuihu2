package com.example.xiao.myappshuihu.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	/**
	 * 显示短时间的Toast
	 * @param context
	 * @param content
	 */
	public static void Short(Context context,String content){
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 显示长时间的Toast
	 * @param context
	 * @param content
	 */
	public static void Long(Context context,String content){
		Toast.makeText(context, content, Toast.LENGTH_LONG).show();
	}
}
