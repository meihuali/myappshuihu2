package com.example.xiao.myappshuihu.entity;

/**
 * Created by Administrator on 2017/7/3 0003.
 * 料包的 实体类\
 *
 */

public class LiaoBaoBean {

    /**
     * status : 1
     * data : {"id":"1","tea_name":"玫瑰茶","tea_id":"12345678","temperature":"90","is_boil":"1","is_purify":"1","purify_time":"5","tea_img":"http://img4.imgtn.bdimg.com/it/u=3961938396,3575484530&fm=26&gp=0.jpg","keepwarn_time":"30","is_open":"1","c_time":"0","u_time":"0"}
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
         * id : 1
         * tea_name : 玫瑰茶
         * tea_id : 12345678
         * temperature : 90
         * is_boil : 1
         * is_purify : 1
         * purify_time : 5
         * tea_img : http://img4.imgtn.bdimg.com/it/u=3961938396,3575484530&fm=26&gp=0.jpg
         * keepwarn_time : 30
         * is_open : 1
         * c_time : 0
         * u_time : 0
         */

        private String id;
        private String tea_name;
        private String tea_id;
        private String temperature;
        private String is_boil;
        private String is_purify;
        private String purify_time;
        private String tea_img;
        private String keepwarn_time;
        private String is_open;
        private String c_time;
        private String u_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTea_name() {
            return tea_name;
        }

        public void setTea_name(String tea_name) {
            this.tea_name = tea_name;
        }

        public String getTea_id() {
            return tea_id;
        }

        public void setTea_id(String tea_id) {
            this.tea_id = tea_id;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getIs_boil() {
            return is_boil;
        }

        public void setIs_boil(String is_boil) {
            this.is_boil = is_boil;
        }

        public String getIs_purify() {
            return is_purify;
        }

        public void setIs_purify(String is_purify) {
            this.is_purify = is_purify;
        }

        public String getPurify_time() {
            return purify_time;
        }

        public void setPurify_time(String purify_time) {
            this.purify_time = purify_time;
        }

        public String getTea_img() {
            return tea_img;
        }

        public void setTea_img(String tea_img) {
            this.tea_img = tea_img;
        }

        public String getKeepwarn_time() {
            return keepwarn_time;
        }

        public void setKeepwarn_time(String keepwarn_time) {
            this.keepwarn_time = keepwarn_time;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getU_time() {
            return u_time;
        }

        public void setU_time(String u_time) {
            this.u_time = u_time;
        }
    }
}
