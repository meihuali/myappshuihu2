package com.example.xiao.myappshuihu.entity;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class LoginBean {

    /**
     * status : 1
     * data : {"member_id":"174240118","name":"隔壁老王","sex":"3","img":"user.jpg"}
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
         * member_id : 174240118
         * name : 隔壁老王
         * sex : 3
         * img : user.jpg
         */

        private String member_id;
        private String name;
        private String sex;
        private String img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
