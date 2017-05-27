package com.example.xiao.myappshuihu.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/27 0027.
 * 购物车清单 的实体类
 */

public class ShoppingcartlistBean {

    /**
     * status : 1
     * data : [{"undercarriage":0,"commodity":"14","name":"水壶1","orderid":"10","price":"100","total":"6400","color":"红色","not_color":2,"weight":"1.5kg","number":"64","img":"20170522/13c41ccd83c5197884ce5ac4cae218a7.jpg","color_weight":[{"color":"红色","weight":"1.5kg"}]},{"undercarriage":0,"commodity":"20","name":"水壶2","orderid":"11","price":"101","total":"8080","color":"红色","not_color":2,"weight":"-1","number":"80","img":"20170522/875391e7697add42b305c7626606b9d1.jpg","color_weight":[{"color":"红色","weight":"1.5kg"}]}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
         * undercarriage : 0
         * commodity : 14
         * name : 水壶1
         * orderid : 10
         * price : 100
         * total : 6400
         * color : 红色
         * not_color : 2
         * weight : 1.5kg
         * number : 64
         * img : 20170522/13c41ccd83c5197884ce5ac4cae218a7.jpg
         * color_weight : [{"color":"红色","weight":"1.5kg"}]
         */

        private int undercarriage;
        private String commodity;
        private String name;
        private String orderid;
        private String price;
        private String total;
        private String color;
        private int not_color;
        private String weight;
        private String number;
        private String img;
        private List<ColorWeightBean> color_weight;
/*====================== 下面这些标识符都是自己加上去的=======================================================*/
        /** 是否被选中 */
        private boolean isChildSelected;

        public boolean isChildSelected() {
            return isChildSelected;
        }

        public void setChildSelected(boolean childSelected) {
            isChildSelected = childSelected;
        }

        public boolean getIsChildSelected() {
            return this.isChildSelected;
        }

        public void setIsChildSelected(boolean isChildSelected) {
            this.isChildSelected = isChildSelected;
        }


        public int getUndercarriage() {
            return undercarriage;
        }

        public void setUndercarriage(int undercarriage) {
            this.undercarriage = undercarriage;
        }

        public String getCommodity() {
            return commodity;
        }

        public void setCommodity(String commodity) {
            this.commodity = commodity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getNot_color() {
            return not_color;
        }

        public void setNot_color(int not_color) {
            this.not_color = not_color;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<ColorWeightBean> getColor_weight() {
            return color_weight;
        }

        public void setColor_weight(List<ColorWeightBean> color_weight) {
            this.color_weight = color_weight;
        }

        public static class ColorWeightBean {
            /**
             * color : 红色
             * weight : 1.5kg
             */

            private String color;
            private String weight;

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
}
