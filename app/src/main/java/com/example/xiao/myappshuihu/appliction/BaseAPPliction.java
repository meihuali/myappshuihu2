package com.example.xiao.myappshuihu.appliction;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.anonymous.greendao.DBManager;
import com.example.xiao.myappshuihu.utils.CrashHandler;
import com.example.xiao.myappshuihu.utils.LogToFile;
import com.hss01248.dialog.MyActyManager;
import com.hss01248.dialog.StyledDialog;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
    public static Map<String, Activity> destoryMap = new HashMap<String, Activity>();
    //创建
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mInstance = this;
        DBManager.getInstance();
     /*   CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        LogToFile.init(getApplicationContext());*/

        quanjuDialog();


    }
    /*
    * 全局dialog
    * */
    private void quanjuDialog() {
        StyledDialog.init(this);

        //在activity生命周期callback中拿到顶层activity引用:
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                //在这里保存顶层activity的引用(内部以软引用实现)
                MyActyManager.getInstance().setCurrentActivity(activity);

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });


    }

    public static BaseAPPliction getInstance() {
        if (mInstance == null) {
            mInstance = new BaseAPPliction();
        }
        return mInstance;
    }




    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */
    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            if (key.equals(activityName)) {
                destoryMap.get(key).finish();
            }
        }
    }


}
