package com.example.xiao.myappshuihu.appliction;

import android.app.Activity;
import android.app.Application;

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
