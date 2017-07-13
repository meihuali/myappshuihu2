package com.example.xiao.myappshuihu.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/7 0007.
 * / 用户登录实体
 */

public class LoginBens {

    /**
     * status : 1
     * data : {"id":"22","member_id":"114035707","name":"啊啊啊","sex":"0","phone":"17620193389","password":"","img":"user.jpg","c_time":"1499392267","do":"1","level":null,"is_delete":"2","u_time":null}
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
         * id : 22
         * member_id : 114035707
         * name : 啊啊啊
         * sex : 0
         * phone : 17620193389
         * password :
         * img : user.jpg
         * c_time : 1499392267
         * do : 1
         * level : null
         * is_delete : 2
         * u_time : null
         */

        private String id;
        private String member_id;
        private String name;
        private String sex;
        private String phone;
        private String password;
        private String img;
        private String c_time;
        @SerializedName("do")
        private String doX;
        private Object level;
        private String is_delete;
        private Object u_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getC_time() {
            return c_time;
        }

        public void setC_time(String c_time) {
            this.c_time = c_time;
        }

        public String getDoX() {
            return doX;
        }

        public void setDoX(String doX) {
            this.doX = doX;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public Object getU_time() {
            return u_time;
        }

        public void setU_time(Object u_time) {
            this.u_time = u_time;
        }
    }
}
