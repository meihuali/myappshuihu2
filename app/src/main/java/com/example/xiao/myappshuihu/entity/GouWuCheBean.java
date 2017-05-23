package com.example.xiao.myappshuihu.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.entity
 * 文件名：GouWuCheBean
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/10 0010 下午 4:59
 * 描述：TODO
 */

public class GouWuCheBean {

    double money;
    int counts;

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "GouWuCheBean{" +
                "money=" + money +
                ", counts=" + counts +
                '}';
    }
}
