package com.example.xiao.myappshuihu.entity;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.entity
 * 文件名：SwitchStateBean
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/10/9 0009 18:04
 * 描述：TODO
 */
public class SwitchStateBean {

    /**
     * status : 1
     * data : {"level":"0.0L","temp":"0C","hub":"0","state":"0","lasttime":"20171009165313","addwater":"2","isonline":"online","appid":"8699630239028350","orderid":"2017100916531242"}
     */

    private int status;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * level : 0.0L
         * temp : 0C
         * hub : 0
         * state : 0
         * lasttime : 20171009165313
         * addwater : 2
         * isonline : online
         * appid : 8699630239028350
         * orderid : 2017100916531242
         */

        private String level;
        private String temp;
        private String hub;
        private String state;
        private String lasttime;
        private String addwater;
        private String isonline;
        private String appid;
        private String orderid;

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

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getAddwater() {
            return addwater;
        }

        public void setAddwater(String addwater) {
            this.addwater = addwater;
        }

        public String getIsonline() {
            return isonline;
        }

        public void setIsonline(String isonline) {
            this.isonline = isonline;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }
    }
}
