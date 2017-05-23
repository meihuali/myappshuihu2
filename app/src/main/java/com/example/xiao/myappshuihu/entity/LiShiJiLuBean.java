package com.example.xiao.myappshuihu.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class LiShiJiLuBean {


    private int status;
    private String total;
    private int page;
    private String pagesize;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * machineid : 01020601411608666666
         * operation : 1
         * starttime : 081314
         * endtime : 081351
         * level : 0.0L
         * temp : 45C
         * boil : 0
         * purify : 0
         * keepwarm : 0
         * createtime : 1492676033
         * energy : 5W
         */

        private String machineid;
        private String operation;
        private String starttime;
        private String endtime;
        private String level;
        private String temp;
        private String boil;
        private String purify;
        private String keepwarm;
        private String createtime;
        private String energy;

        public String getMachineid() {
            return machineid;
        }

        public void setMachineid(String machineid) {
            this.machineid = machineid;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getBoil() {
            return boil;
        }

        public void setBoil(String boil) {
            this.boil = boil;
        }

        public String getPurify() {
            return purify;
        }

        public void setPurify(String purify) {
            this.purify = purify;
        }

        public String getKeepwarm() {
            return keepwarm;
        }

        public void setKeepwarm(String keepwarm) {
            this.keepwarm = keepwarm;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }
    }

/*
* machined:机器识别符
operation：操作模式（0为预约，1为手动，2为App）
starttime：开始时间
endtime：结束时间
level：水位
temp：温度
boil：是否先煮沸（0为否，1为是）
purify：是否净化（0为否，1为是）
keepwarm：是否保温（0为否，1为是）
createtime：开始时间
* */


}
