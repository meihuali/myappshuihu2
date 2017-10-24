package com.example.xiao.myappshuihu.entity;

import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by luoyang on 2017/7/4.
 */
@Entity
public class NZYData {
    @Id
    public Long id;
    public String NZ_ID;
    public String MACHINE_ID;
    public String NZ_NAME = "Label";
    public int NZ_TX1;
    public String NZ_SW = "0000000";
    public String NZ_TIME = "06:00";
    public String NZ_MS = "Alarm";
    public int NZ_ISOPEN;
    public int NZ_START = 1;//0是打开，1是关闭，默认关闭
    public int NZ_END = 1;//0是打开，1是关闭，默认关闭
    public int NZ_LELVER_OPEN;
    public String NZ_LELVER = "0.0L";
    public String NZ_ZDYID;
    public String ORDER_ID;
    @Generated(hash = 132442583)
    public NZYData(Long id, String NZ_ID, String MACHINE_ID, String NZ_NAME,
            int NZ_TX1, String NZ_SW, String NZ_TIME, String NZ_MS, int NZ_ISOPEN,
            int NZ_START, int NZ_END, int NZ_LELVER_OPEN, String NZ_LELVER,
            String NZ_ZDYID, String ORDER_ID) {
        this.id = id;
        this.NZ_ID = NZ_ID;
        this.MACHINE_ID = MACHINE_ID;
        this.NZ_NAME = NZ_NAME;
        this.NZ_TX1 = NZ_TX1;
        this.NZ_SW = NZ_SW;
        this.NZ_TIME = NZ_TIME;
        this.NZ_MS = NZ_MS;
        this.NZ_ISOPEN = NZ_ISOPEN;
        this.NZ_START = NZ_START;
        this.NZ_END = NZ_END;
        this.NZ_LELVER_OPEN = NZ_LELVER_OPEN;
        this.NZ_LELVER = NZ_LELVER;
        this.NZ_ZDYID = NZ_ZDYID;
        this.ORDER_ID = ORDER_ID;
    }
    @Generated(hash = 1544359351)
    public NZYData() {
    }
    public String getNZ_ID() {
        return this.NZ_ID;
    }
    public void setNZ_ID(String NZ_ID) {
        this.NZ_ID = NZ_ID;
    }
    public String getMACHINE_ID() {
        return this.MACHINE_ID;
    }
    public void setMACHINE_ID(String MACHINE_ID) {
        this.MACHINE_ID = MACHINE_ID;
    }
    public String getNZ_NAME() {
        return this.NZ_NAME;
    }
    public void setNZ_NAME(String NZ_NAME) {
        this.NZ_NAME = NZ_NAME;
    }
    public int getNZ_TX1() {
        return this.NZ_TX1;
    }
    public void setNZ_TX1(int NZ_TX1) {
        this.NZ_TX1 = NZ_TX1;
    }
    public String getNZ_SW() {
        return this.NZ_SW;
    }
    public void setNZ_SW(String NZ_SW) {
        this.NZ_SW = NZ_SW;
    }
    public String getNZ_TIME() {
        return this.NZ_TIME;
    }
    public void setNZ_TIME(String NZ_TIME) {
        this.NZ_TIME = NZ_TIME;
    }
    public String getNZ_MS() {
        return this.NZ_MS;
    }
    public void setNZ_MS(String NZ_MS) {
        this.NZ_MS = NZ_MS;
    }
    public int getNZ_ISOPEN() {
        return this.NZ_ISOPEN;
    }
    public void setNZ_ISOPEN(int NZ_ISOPEN) {
        this.NZ_ISOPEN = NZ_ISOPEN;
    }
    public int getNZ_START() {
        return this.NZ_START;
    }
    public void setNZ_START(int NZ_START) {
        this.NZ_START = NZ_START;
    }
    public int getNZ_END() {
        return this.NZ_END;
    }
    public void setNZ_END(int NZ_END) {
        this.NZ_END = NZ_END;
    }
    public int getNZ_LELVER_OPEN() {
        return this.NZ_LELVER_OPEN;
    }
    public void setNZ_LELVER_OPEN(int NZ_LELVER_OPEN) {
        this.NZ_LELVER_OPEN = NZ_LELVER_OPEN;
    }
    public String getNZ_LELVER() {
        return this.NZ_LELVER;
    }
    public void setNZ_LELVER(String NZ_LELVER) {
        this.NZ_LELVER = NZ_LELVER;
    }
    public String getNZ_ZDYID() {
        return this.NZ_ZDYID;
    }
    public void setNZ_ZDYID(String NZ_ZDYID) {
        this.NZ_ZDYID = NZ_ZDYID;
    }
    public String getORDER_ID() {
        return this.ORDER_ID;
    }
    public void setORDER_ID(String ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
