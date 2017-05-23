package com.example.xiao.myappshuihu.sqlite;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.xiao.myappshuihu.data.User;
import com.example.xiao.myappshuihu.utils.DateUtil;


/**
 * 用户基础数据的缓存类
 * @author Administrator
 *
 */
public class UserSP {
	public static final String NAME             = "userinfo";
	public static final String USER_ACCOUNT     = "userAccount";  //用户账号
	public static final String USER_PASS        = "userPass";  //用户账号
	public static final String USER_IMEI        = "userImei";
	public static final String IS_ONE           = "is_one";

	public static final String IS_AUTO_PHONE               = "isautophone";
	public static final String IS_AUTO_LOGIN               = "isautologin";
	public static final String USER_PHONE               = "phone";
	public static final String USER_EMAIL               = "email";
	public static final String USER_NAME               = "name";
	public static final String USER_PASSWORD             = "password";
	public static final String Attendance_Date             = "attendance_date";

	private static UserSP Instance;
	private SharedPreferences  sharedPreferences;
	
	private UserSP(){
		
	}
	public static UserSP getInstance(){
		if(Instance == null){
			Instance = new UserSP();
		}
		return Instance;
	}
	
	public String getUserInfoStringKey(Context context,String key){

		sharedPreferences = context.getSharedPreferences(NAME, 0);
		String value = sharedPreferences.getString(key, null);
		return value;
	}
	public String getUserInfoString(Context context,String key){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		String value = sharedPreferences.getString(key, "");
		return value;
	}
	public User getUser(Context context){//获取本地保存的用户信息
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		String value = sharedPreferences.getString("USER", "");
		return new User(value);
	}
	public void saveUser(Context context,User u){//注册时的信息
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString("USER", u.toString()).commit();
	}
	public int getUserInfoIntKey(Context context,String key){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		int value = sharedPreferences.getInt(key, 0);
		return value;
	}
	public boolean getUserInfoBoolKey(Context context,String key){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		boolean value = sharedPreferences.getBoolean(key, false);
		return value;
	}
	
	/**
	 * 保存用户输入的手机号码  必须在登陆以后才保存
	 * @param context
	 * @param
	 */
	public void setUserNumber(Context context,String numberName,String pass){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		sharedPreferences.edit().putString(USER_ACCOUNT, numberName).commit();
		sharedPreferences.edit().putString(USER_PASS, pass).commit();
	}

	
	public void setUserImei(Context context,String imei){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		sharedPreferences.edit().putString(USER_IMEI, imei).commit();
	}
	
	
	public void setUserip(Context context,String IP){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		sharedPreferences.edit().putString(IP, IP).commit();
	}
	
	/**
	 * 保存用户输入的手机号码  必须在登陆以后才保存
	 * @param context
	 * @param
	 */
	public void setUserIsOne(Context context,int state){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		sharedPreferences.edit().putInt(IS_ONE, state).commit();
	}
	
	/**
	 * 清理用户登陆信息
	 * @param context
	 */
	
	public void clearAP(Context context){
		sharedPreferences = context.getSharedPreferences(NAME, 0); 
		sharedPreferences.edit().putString(USER_ACCOUNT, null).commit();
		sharedPreferences.edit().putString(USER_PASS, null).commit();
	}

	public void setUserAutoPhone(Context context,boolean state){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putBoolean(IS_AUTO_PHONE, state).commit();
	}
	public void setUserAutoLogin(Context context,boolean state){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putBoolean(IS_AUTO_LOGIN, state).commit();
	}
	public void setUserPhone(Context context,String IP){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(USER_PHONE, IP).commit();
	}
	public void setUserPassword(Context context,String IP){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(USER_PASSWORD, IP).commit();
	}
	public void setUserEmail(Context context,String IP){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(USER_EMAIL, IP).commit();
	}
	public void setUserName(Context context,String IP){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(USER_NAME, IP).commit();
	}

	public void setAttendant(Context context,String in,String out){
		String a= DateUtil.getNow();
		a+= (in==null?"#":"#"+in);
		a+= (out==null?"#":"#"+out);
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(Attendance_Date, a).commit();
	}
	public void resetAttendant(Context context){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString(Attendance_Date, DateUtil.getNow()+"##").commit();
	}
	public String[] getAttendance(Context context){
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		String value = sharedPreferences.getString(Attendance_Date, "20140101##");
		return value.split("#",-1);
	}



	public String getUserLabel(Context context){//获取本地保存的用户信息
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		String value = sharedPreferences.getString("USERLABEL", "");
		return  value;
	}
	public void setUserLabel(Context context,String label){//获取本地保存的用户信息
		sharedPreferences = context.getSharedPreferences(NAME, 0);
		sharedPreferences.edit().putString("USERLABEL", label).commit();
	}
}
