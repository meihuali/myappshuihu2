package com.example.anonymous.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.example.anonymous.greendao.helper.MySQLiteOpenHelper;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;


public class DBManager {
    private MySQLiteOpenHelper devOpenHelper;
    private static DBManager instance;
    private static final String DB_NAME = "nz_quickdial.db";

    private DBManager() {
        devOpenHelper = new MySQLiteOpenHelper(BaseAPPliction.getInstance(), DB_NAME, null);
    }

    public static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public SQLiteDatabase getWritableDatabase() {
        if (devOpenHelper == null) {
            devOpenHelper = new MySQLiteOpenHelper(BaseAPPliction.getInstance(), DB_NAME, null);
        }
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        return db;
    }

}
