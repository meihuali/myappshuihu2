package com.example.anonymous.greendao.model;


import com.example.anonymous.greendao.DBManager;
import com.example.anonymous.greendao.gen.DaoMaster;
import com.example.anonymous.greendao.gen.DaoSession;
import com.example.anonymous.greendao.gen.ZDYDataDao;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.entity.ZDYData;

import java.util.ArrayList;
import java.util.List;
//cesi
public class ZDYDataModel {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ZDYDataDao zdyDataDao;
    private static ZDYDataModel mZDYDataModel;

    private ZDYDataModel() {
        daoMaster = new DaoMaster(DBManager.getInstance().getWritableDatabase());
        daoSession = daoMaster.newSession();
        zdyDataDao = daoSession.getZDYDataDao();
    }

    public static ZDYDataModel getInstance() {
        if (mZDYDataModel == null) {
            mZDYDataModel = new ZDYDataModel();
        }
        return mZDYDataModel;
    }
    /*
    * 这个是在设置里面 修改后 然后在进去 设置的时候 取数据
    * */
    public List<ZDYData> getAlermList(int state, String machineid) {

        if (state == 1) {
            //原始的 通过ZDY_ISOPEN 来查询
           // return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).where(ZDYDataDao.Properties.ZDY_ISOPEN.eq(1)).list();
            return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).list();
        } else {
            return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).list();
        }

    }
    /*
    * 这个是在主界面 取的时候·
    * */
    public List<ZDYData> getAlermList2(int state, String machineid) {

        if (state == 2) {
            //原始的 通过ZDY_ISOPEN 来查询
           return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).where(ZDYDataDao.Properties.ZDY_ISOPEN.eq(1)).list();
           // return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).list();
        } else {
            return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.MACHINE_ID.eq(machineid)).list();
        }

    }

    public ZDYData inquiry(String app_id) {
        return zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.ZDY_ID.eq(app_id)).build().unique();
    }

    public void insert(ZDYData data) {
        ZDYData findData = zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.ZDY_ID.eq(data.getZDY_ID())).build().unique();
        if (findData == null) {
            zdyDataDao.insert(data);
        } else {
            zdyDataDao.update(data);
        }
    }

    public void delete(String aPP_ID) {
        ZDYData findData = zdyDataDao.queryBuilder().where(ZDYDataDao.Properties.ZDY_ID.eq(aPP_ID)).build().unique();
        if (findData != null) {
            zdyDataDao.delete(findData);
        }
    }

    public void update(ZDYData data) {
        zdyDataDao.update(data);
    }

    public void addDefault(String machineid) {
        Long id = System.currentTimeMillis();
        ZDYData data = new ZDYData();
        data.MACHINE_ID = machineid;
        data.ZDY_ID = ( id+ "");
        data.ZDY_ISDE = 1;
        data.ZDY_ISOPEN = 1;
        data.ZDY_NAME = BaseAPPliction.getInstance().getString(R.string.boil);
        data.ZDY_SW = "100°c";
        data.ZDY_TIME = "0";
        data.ZDY_TIME1 = "0";
        data.ZDY_ISZF = "0";
        data.ZDY_IMAGE = String.valueOf(R.drawable.yangshengtang2);
        data.ZDY_ErWeiMa = "a";
        data.ZDY_IS_BOIL = "a";
        data.ZDY_IS_PURIFY = "a";
        data.ZDY_PURIFY_TIME = "a";
//            data.ZDY_IMAGEURL = "a";
        data.ZDY_KEEPWARN_TIME = "a";

        ZDYData data1 = new ZDYData();
        data1.MACHINE_ID = machineid;
        data1.ZDY_ID = (id+1 + "");
        data1.ZDY_ISDE = 0;
        data1.ZDY_ISOPEN = 1;
        data1.ZDY_NAME = BaseAPPliction.getInstance().getString(R.string.coffee);
        data1.ZDY_SW = "85°c";
        data1.ZDY_TIME = "0";
        data1.ZDY_TIME1 = "0";
        data1.ZDY_ISZF = "0";
        data1.ZDY_IMAGE = String.valueOf(R.drawable.huacha2);
        data1.ZDY_ErWeiMa = "a";
        data1.ZDY_IS_BOIL = "a";
        data1.ZDY_IS_PURIFY = "a";
        data1.ZDY_PURIFY_TIME = "a";
//            data1.ZDY_IMAGEURL = "a";
        data1.ZDY_KEEPWARN_TIME = "a";


        ZDYData data2 = new ZDYData();
        data2.MACHINE_ID = machineid;
        data2.ZDY_ID = (id+2 + "");
        data2.ZDY_ISDE = 0;
        data2.ZDY_ISOPEN = 1;
        data2.ZDY_NAME = BaseAPPliction.getInstance().getString(R.string.tea);
        data2.ZDY_SW = "80°c";
        data2.ZDY_TIME = "0";
        data2.ZDY_TIME1 = "0";
        data2.ZDY_ISZF = "0";
        data2.ZDY_IMAGE = String.valueOf(R.drawable.zhufei2);
        data2.ZDY_ErWeiMa = "a";
        data2.ZDY_IS_BOIL = "a";
        data2.ZDY_IS_PURIFY = "a";
        data2.ZDY_PURIFY_TIME = "a";
//            data2.ZDY_IMAGEURL = "a";
        data2.ZDY_KEEPWARN_TIME = "a";


        ZDYData data3 = new ZDYData();
        data3.MACHINE_ID = machineid;
        data3.ZDY_ID = (id+3 + "");
        data3.ZDY_ISDE = 0;
        data3.ZDY_ISOPEN = 1;
        data3.ZDY_NAME = BaseAPPliction.getInstance().getString(R.string.milk);
        data3.ZDY_SW = "45°c";
        data3.ZDY_TIME = "0";
        data3.ZDY_TIME1 = "0";
        data3.ZDY_ISZF = "0";
        data3.ZDY_IMAGE = String.valueOf(R.drawable.shuiguicha2);
        data3.ZDY_ErWeiMa = "a";
        data3.ZDY_IS_BOIL = "a";
        data3.ZDY_IS_PURIFY = "a";
        data3.ZDY_PURIFY_TIME = "a";
//            data3.ZDY_IMAGEURL = "a";
        data3.ZDY_KEEPWARN_TIME = "a";

        ZDYData data4 = new ZDYData();
        data4.MACHINE_ID = machineid;
        data4.ZDY_ID = (id+4 + "");
        data4.ZDY_ISDE = 0;
        data4.ZDY_ISOPEN = 1;
        data4.ZDY_NAME = BaseAPPliction.getInstance().getString(R.string.juhuacha);
        data4.ZDY_SW = "45°c";
        data4.ZDY_TIME = "0";
        data4.ZDY_TIME1 = "0";
        data4.ZDY_ISZF = "0";
        data4.ZDY_IMAGE = String.valueOf(R.drawable.i5);
        data4.ZDY_ErWeiMa = "a";
        data4.ZDY_IS_BOIL = "a";
        data4.ZDY_IS_PURIFY = "a";
        data4.ZDY_PURIFY_TIME = "a";
//            data4.ZDY_IMAGEURL = "a";
        data4.ZDY_KEEPWARN_TIME = "a";
        List<ZDYData> list = new ArrayList<>();
        list.add(data);
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        zdyDataDao.insertInTx(list);
    }
}
