package com.example.anonymous.greendao.model;


import com.example.anonymous.greendao.DBManager;
import com.example.anonymous.greendao.gen.DaoMaster;
import com.example.anonymous.greendao.gen.DaoSession;
import com.example.anonymous.greendao.gen.NZYDataDao;
import com.example.xiao.myappshuihu.entity.NZYData;
import com.example.xiao.myappshuihu.entity.ZDYData;

import java.util.List;

public class NZYDataModel {
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private NZYDataDao nzyDataDao;
    private static NZYDataModel mNZYDataModel;

    private NZYDataModel() {
        daoMaster = new DaoMaster(DBManager.getInstance().getWritableDatabase());
        daoSession = daoMaster.newSession();
        nzyDataDao = daoSession.getNZYDataDao();
    }

    public static NZYDataModel getInstance() {
        if (mNZYDataModel == null) {
            mNZYDataModel = new NZYDataModel();
        }
        return mNZYDataModel;
    }

    public List<NZYData> getAlermList(String machineid) {

        return nzyDataDao.queryBuilder().where(NZYDataDao.Properties.MACHINE_ID.eq(machineid)).list();
    }

    public void insert(NZYData data) {
        NZYData findData = nzyDataDao.queryBuilder().where(NZYDataDao.Properties.NZ_MS.eq(data.getNZ_MS())).build().unique();
        if (findData == null) {
            nzyDataDao.insert(data);
        } else {
            nzyDataDao.update(data);
        }
    }

    public void delete(String aPP_ID) {
        NZYData findData = nzyDataDao.queryBuilder().where(NZYDataDao.Properties.NZ_ID.eq(aPP_ID)).build().unique();
        if (findData != null) {
            nzyDataDao.delete(findData);
        }
    }

    public void updateOrederId(String order_id, String nz_id) {
        NZYData findData = nzyDataDao.queryBuilder().where(NZYDataDao.Properties.NZ_ID.eq(nz_id)).build().unique();
        if (findData != null) {
            findData.setORDER_ID(order_id);
            nzyDataDao.update(findData);
        }
    }

    public void update(NZYData data) {
        nzyDataDao.update(data);
    }

    public void delete(NZYData data) {
        nzyDataDao.delete(data);
    }


    public void addDefault(List<ZDYData> mList, String machineid) {
        String[] timeString = new String[]{"07:00", "12:00", "17:00", "19:00", "21:09"};
        for (int i = 0; i < mList.size() - 1; i++) {
            NZYData data = new NZYData();
            data.setNZ_ID(System.currentTimeMillis() + "");
            data.setNZ_ZDYID(mList.get(i).ZDY_ID);
            data.setNZ_MS(mList.get(i).ZDY_NAME);
            data.setMACHINE_ID(machineid);
            data.setNZ_TIME(timeString[i]);
            insert(data);
        }
    }
}
