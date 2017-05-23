package com.example.xiao.myappshuihu.entity;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class SettingsBean {
    String name;
    String wendu;
    private boolean isOpen;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
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

    @Override
    public String toString() {
        return "SettingsBean{" +
                "name='" + name + '\'' +
                ", wendu='" + wendu + '\'' +
                '}';
    }
}
