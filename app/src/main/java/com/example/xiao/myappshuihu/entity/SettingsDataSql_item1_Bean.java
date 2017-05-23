package com.example.xiao.myappshuihu.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
@Entity
public class SettingsDataSql_item1_Bean {
    @Id
    private Long id;
    String name;
    String wendu;
    boolean status;

    @Generated(hash = 1314369299)
    public SettingsDataSql_item1_Bean(Long id, String name, String wendu,
            boolean status) {
        this.id = id;
        this.name = name;
        this.wendu = wendu;
        this.status = status;
    }

    @Generated(hash = 1377702244)
    public SettingsDataSql_item1_Bean() {
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
        return "SettingsDataSql_item1_Bean{" +
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
