package com.example.xiao.myappshuihu.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Entity
public class SettingsDataSqlBean {
    @Id
    private Long id;
    String name;
    String wendu;
    boolean status;

    @Generated(hash = 1438513196)
    public SettingsDataSqlBean(Long id, String name, String wendu, boolean status) {
        this.id = id;
        this.name = name;
        this.wendu = wendu;
        this.status = status;
    }

    @Generated(hash = 252137033)
    public SettingsDataSqlBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "SettingsDataSqlBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", wendu='" + wendu + '\'' +
                ", status=" + status +
                '}';
    }

    public boolean getStatus() {
        return this.status;
    }
}
