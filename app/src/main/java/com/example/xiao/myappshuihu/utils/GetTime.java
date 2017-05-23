package com.example.xiao.myappshuihu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class GetTime {
	/**
	 *此类用来转换时间戳 将时间戳转成时间
	 * @param
	 * @return
	 */

	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt *1000);
		res = simpleDateFormat.format(date);
		return res;
	}

}
