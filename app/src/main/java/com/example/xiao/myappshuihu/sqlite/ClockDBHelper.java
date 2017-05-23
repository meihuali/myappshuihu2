package com.example.xiao.myappshuihu.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClockDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Clock.db";
    private static final int DATABASE_VERSION = 1;
    private static final String Clock_Table = "clock";//一个表，用于记录定时任务
    //private static final String Atten_Table = "clock";//一个表，用于记录用户每天的签到签退状态。
    public ClockDBHelper(Context context) {
        //CursorFactory设置为null,使用默认值  
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //数据库第一次被创建时onCreate会被调用  
    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS person" +  
//                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, info TEXT)");

        String sql = "CREATE TABLE if not exists clock (id integer PRIMARY KEY AUTOINCREMENT," +    //自动生成的唯一标识
                "orderid text," +   //根据当前时间生成的order
                "machineid text," +    // meachine
                "appid text," +   //appid
                "time text," +   //11:00这种格式
                "repeat text DEFAULT '0000000'," +  //从星期一到星期日，0为不预约，1为开启
                "title text," +    //标题，如11点自动打开
                "option text," +  //开或者关
                "start_remind int," +  //开始提醒，1或者0
                "end_remind int," +  //开或者关
                "status text," +  //打开或者关闭
                "mode text)";   //根据电器类型不同，存储不同的数据，如灯泡的阅读模式，
        db.execSQL(sql);
    }

    //如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade  
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
    }
}  
