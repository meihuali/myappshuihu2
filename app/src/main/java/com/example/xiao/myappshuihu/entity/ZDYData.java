package com.example.xiao.myappshuihu.entity;

import com.example.xiao.myappshuihu.R;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;


@Entity
public class ZDYData {
    @Id
    public Long id;
    /******自定义ID******/
    public String MACHINE_ID ;
    /******自定义ID******/
    public String ZDY_ID  ;
    /******自定义名字******/
    public String ZDY_NAME="label";
    /******自定义是否可以删除******/
    public int ZDY_ISDE = 0;
    /******自定义温度******/
    public String ZDY_SW="80°c";
    /******自定义保温时间******/
    public String ZDY_TIME="0";
    /******自定义净化******/
    public String ZDY_TIME1="0";
    /******自定是否打开******/
    public int ZDY_ISOPEN;
    /******自定义是否开启煮沸模式******/
    public String ZDY_ISZF="0";
    /*==================加入多一张图片字段 弱水三千==========*/
    public String ZDY_IMAGE = String.valueOf(R.drawable.shuiguicha2);
    //扫描二维码号码
    public String ZDY_ErWeiMa="a";
    //是否煮沸
    public String ZDY_IS_BOIL="a";
    //是否净化
    public String ZDY_IS_PURIFY="a";
    //净化时间
    public String ZDY_PURIFY_TIME="a";
    //图片地址
//        public String ZDY_IMAGEURL="a";
    //料包保温时间
    public String ZDY_KEEPWARN_TIME="a";
    @Generated(hash = 812601547)
    public ZDYData(Long id, String MACHINE_ID, String ZDY_ID, String ZDY_NAME,
            int ZDY_ISDE, String ZDY_SW, String ZDY_TIME, String ZDY_TIME1,
            int ZDY_ISOPEN, String ZDY_ISZF, String ZDY_IMAGE, String ZDY_ErWeiMa,
            String ZDY_IS_BOIL, String ZDY_IS_PURIFY, String ZDY_PURIFY_TIME,
            String ZDY_KEEPWARN_TIME) {
        this.id = id;
        this.MACHINE_ID = MACHINE_ID;
        this.ZDY_ID = ZDY_ID;
        this.ZDY_NAME = ZDY_NAME;
        this.ZDY_ISDE = ZDY_ISDE;
        this.ZDY_SW = ZDY_SW;
        this.ZDY_TIME = ZDY_TIME;
        this.ZDY_TIME1 = ZDY_TIME1;
        this.ZDY_ISOPEN = ZDY_ISOPEN;
        this.ZDY_ISZF = ZDY_ISZF;
        this.ZDY_IMAGE = ZDY_IMAGE;
        this.ZDY_ErWeiMa = ZDY_ErWeiMa;
        this.ZDY_IS_BOIL = ZDY_IS_BOIL;
        this.ZDY_IS_PURIFY = ZDY_IS_PURIFY;
        this.ZDY_PURIFY_TIME = ZDY_PURIFY_TIME;
        this.ZDY_KEEPWARN_TIME = ZDY_KEEPWARN_TIME;
    }
    @Generated(hash = 92433122)
    public ZDYData() {
    }
    public String getMACHINE_ID() {
        return this.MACHINE_ID;
    }
    public void setMACHINE_ID(String MACHINE_ID) {
        this.MACHINE_ID = MACHINE_ID;
    }
    public String getZDY_ID() {
        return this.ZDY_ID;
    }
    public void setZDY_ID(String ZDY_ID) {
        this.ZDY_ID = ZDY_ID;
    }
    public String getZDY_NAME() {
        return this.ZDY_NAME;
    }
    public void setZDY_NAME(String ZDY_NAME) {
        this.ZDY_NAME = ZDY_NAME;
    }
    public int getZDY_ISDE() {
        return this.ZDY_ISDE;
    }
    public void setZDY_ISDE(int ZDY_ISDE) {
        this.ZDY_ISDE = ZDY_ISDE;
    }
    public String getZDY_SW() {
        return this.ZDY_SW;
    }
    public void setZDY_SW(String ZDY_SW) {
        this.ZDY_SW = ZDY_SW;
    }
    public String getZDY_TIME() {
        return this.ZDY_TIME;
    }
    public void setZDY_TIME(String ZDY_TIME) {
        this.ZDY_TIME = ZDY_TIME;
    }
    public String getZDY_TIME1() {
        return this.ZDY_TIME1;
    }
    public void setZDY_TIME1(String ZDY_TIME1) {
        this.ZDY_TIME1 = ZDY_TIME1;
    }
    public int getZDY_ISOPEN() {
        return this.ZDY_ISOPEN;
    }
    public void setZDY_ISOPEN(int ZDY_ISOPEN) {
        this.ZDY_ISOPEN = ZDY_ISOPEN;
    }
    public String getZDY_ISZF() {
        return this.ZDY_ISZF;
    }
    public void setZDY_ISZF(String ZDY_ISZF) {
        this.ZDY_ISZF = ZDY_ISZF;
    }
    public String getZDY_IMAGE() {
        return this.ZDY_IMAGE;
    }
    public void setZDY_IMAGE(String ZDY_IMAGE) {
        this.ZDY_IMAGE = ZDY_IMAGE;
    }
    public String getZDY_ErWeiMa() {
        return this.ZDY_ErWeiMa;
    }
    public void setZDY_ErWeiMa(String ZDY_ErWeiMa) {
        this.ZDY_ErWeiMa = ZDY_ErWeiMa;
    }
    public String getZDY_IS_BOIL() {
        return this.ZDY_IS_BOIL;
    }
    public void setZDY_IS_BOIL(String ZDY_IS_BOIL) {
        this.ZDY_IS_BOIL = ZDY_IS_BOIL;
    }
    public String getZDY_IS_PURIFY() {
        return this.ZDY_IS_PURIFY;
    }
    public void setZDY_IS_PURIFY(String ZDY_IS_PURIFY) {
        this.ZDY_IS_PURIFY = ZDY_IS_PURIFY;
    }
    public String getZDY_PURIFY_TIME() {
        return this.ZDY_PURIFY_TIME;
    }
    public void setZDY_PURIFY_TIME(String ZDY_PURIFY_TIME) {
        this.ZDY_PURIFY_TIME = ZDY_PURIFY_TIME;
    }
    public String getZDY_KEEPWARN_TIME() {
        return this.ZDY_KEEPWARN_TIME;
    }
    public void setZDY_KEEPWARN_TIME(String ZDY_KEEPWARN_TIME) {
        this.ZDY_KEEPWARN_TIME = ZDY_KEEPWARN_TIME;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
