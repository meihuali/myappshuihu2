package com.example.anonymous.greendao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.anonymous.greendao.gen.DaoMaster;



public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory); //
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
