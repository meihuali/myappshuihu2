package com.example.xiao.myappshuihu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// ���߷���
	private static String getFormat(String format) {
		Date de = new Date();
		DateFormat df = new SimpleDateFormat(format);
		return df.format(de);
	}

	public static String _getFormalTime(){
		return getFormat("yyyyMMddHHmmss");
	}
	public static String DateFormal(){
		return getFormat("yyyyMMdd");
	}
	public static SimpleDateFormat MonthFormal(){
		return new SimpleDateFormat("yyyy-MM");
	}
	public static String getNow(){
		Date d=new    Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(d);
	}
	public static String getNowTime(){
		Date d=new    Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(d);
	}
	public static String getLastMonth(){
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH,-1);//reduce a month to be last month
		return MonthFormal().format(lastDate.getTime());

	}

}


