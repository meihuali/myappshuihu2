package com.example.xiao.myappshuihu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 */

public class ShangPinLieBiaoBean implements Serializable{

    /**
     * status : 1
     * data : [{"id":"17","img":"20170515/908be2ccf47f9e4de1b21b007188009a.jpg","name":"养生壶","address":"佛山","price":"100","rurchase_number":"1000台","url":"http://www.baidu.com","color_weight":"2","postage":"10","color":"白色","weight":"3.2kg"}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 17
         * img : 20170515/908be2ccf47f9e4de1b21b007188009a.jpg
         * name : 养生壶
         * address : 佛山
         * price : 100
         * rurchase_number : 1000台
         * url : http://www.baidu.com
         * color_weight : 2
         * postage : 10
         * color : 白色
         * weight : 3.2kg
         */

        private String id;
        private String img;
        private String name;
        private String address;
        private String price;
        private String rurchase_number;
        private String url;
        private String color_weight;
        private String postage;
        private String color;
        private String weight;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRurchase_number() {
            return rurchase_number;
        }

        public void setRurchase_number(String rurchase_number) {
            this.rurchase_number = rurchase_number;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getColor_weight() {
            return color_weight;
        }

        public void setColor_weight(String color_weight) {
            this.color_weight = color_weight;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }
    }
}
