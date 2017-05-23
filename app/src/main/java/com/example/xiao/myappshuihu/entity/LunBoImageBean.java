package com.example.xiao.myappshuihu.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class LunBoImageBean {

    /**
     * status : 1
     * data : [{"img":"20170515/635f0fa537fde1cc73bdb5d036fc2f83.jpg"},{"img":"20170515/1a3046ecc766f97c08f6ed7eb6ebc30b.jpg"},{"img":"20170515/259e36a66e24b18c8251e1ba2d63ce99.jpg"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * img : 20170515/635f0fa537fde1cc73bdb5d036fc2f83.jpg
         */

        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
