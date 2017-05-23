package com.example.xiao.myappshuihu.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by Administrator on 2017/5/16 0016.
 *  此类用来获取手机唯一设备号
 *      <!--获取手机设备号信息权限-->
        <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 */
public class ToolsGetAppId {
    private static final int REQUEST_CODE = 100;
    private static String appId;

    public static String getinitAppId(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {
            //else里面的 这一段遍历就是获取APPId
            TelephonyManager tm = (TelephonyManager) activity.getSystemService(TELEPHONY_SERVICE);
             appId = tm.getDeviceId() + "0";
            if (appId.length() < 16) {
                appId = appId + "00000000000000000000";
                appId = appId.substring(0, 16);
//                MyApplication.getInstance().setAppId(appId);
                Log.e("appid1", appId);
            }
        }
        return appId;
    }



}
