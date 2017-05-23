package com.example.xiao.myappshuihu.sqlite;

import android.content.Context;

import com.example.anonymous.greendao.gen.DaoMaster;
import com.example.anonymous.greendao.gen.DaoSession;
import com.example.anonymous.greendao.gen.ShangChenLiBiaoBeanDao;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;

import java.util.List;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.sqlite
 * 文件名：MyGreenDaoUtils
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/12 0012 上午 10:42
 * 描述：数据库操作类 曾删改查
 */
public class MyGreenDaoUtils {
    /*这个是保存数据*/
    public static ShangChenLiBiaoBeanDao saveData(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShangChenLiBiaoBeanDao userDao = daoSession.getShangChenLiBiaoBeanDao();
        return userDao;
    }

    /**
     * 查询用户列表 也可以说是取数据
     */
    public static List<ShangChenLiBiaoBean> getqueryUserList(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShangChenLiBiaoBeanDao userDao = daoSession.getShangChenLiBiaoBeanDao();
        List<ShangChenLiBiaoBean> userList = userDao.queryBuilder()
                .where(ShangChenLiBiaoBeanDao.Properties.Id.notEq(1))
//                .limit(5) //这里是限制 只取5条 最后五条
                .build().list();
        return userList;
    }

    //删除数据
        /*这个是删除数据*/
    public static void deleData(Context context,ShangChenLiBiaoBean name) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "mydb", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        ShangChenLiBiaoBeanDao userDao = daoSession.getShangChenLiBiaoBeanDao();
            /*下面，的那个name 就是要删除的 数据*/
        //这里传入的直接就是 实体对象·也就是说·你删除什么内容·就传递什么内容
        userDao.delete(name);
    }

}
