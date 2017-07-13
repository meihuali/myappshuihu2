package com.example.xiao.myappshuihu.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5 0005.
 */

public class HuoQuSheBeiBean {

    /**
     * status : 1
     * total : 2
     * page : 1
     * pagesize : 500
     * data : [{"machineid":"08010101611607000038","level":"","temp":"","hub":"0","state":"0","isonline":"offline","longitude":"113.286476","latitude":"23.145246"},{"machineid":"01020101411608666666","level":"1.0L","temp":"10C","hub":"1","state":"0","isonline":"online","longitude":"","latitude":""}]
     */

    private int status;
    private int total;
    private int page;
    private int pagesize;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
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
         * machineid : 08010101611607000038
         * level :
         * temp :
         * hub : 0
         * state : 0
         * isonline : offline
         * longitude : 113.286476
         * latitude : 23.145246
         */

        private String machineid;
        private String level;
        private String temp;
        private String hub;
        private String state;
        private String isonline;
        private String longitude;
        private String latitude;

        public String getMachineid() {
            return machineid;
        }

        public void setMachineid(String machineid) {
            this.machineid = machineid;
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

        public String getHub() {
            return hub;
        }

        public void setHub(String hub) {
            this.hub = hub;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getIsonline() {
            return isonline;
        }

        public void setIsonline(String isonline) {
            this.isonline = isonline;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
