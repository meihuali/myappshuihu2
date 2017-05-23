package com.example.xiao.myappshuihu.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.entity
 * 文件名：ShangChenLiBiaoBean
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 上午 10:38
 * 描述：TODO
 */
@Entity
public class ShangChenLiBiaoBean implements Serializable{
    @Id
    private Long id;
    String titale;
    String address;
    String Freeshipping;
    String money;
    String fukuanrenshu;

    /** 组是否被选中 */
    private boolean isGroupSelected;
    /** 是否处于编辑状态 */
    private boolean isEditing;
    /** 是否被选中 */
    private boolean isChildSelected;
    /** 价格，当前价格 */
    private String price;
    /** 数量 */
    private String number = "1";
    /** 商品规格 */
    private String pdtDesc;

    public ShangChenLiBiaoBean(Long id, String titale, String address, String freeshipping, String money, String fukuanrenshu) {
        this.id = id;
        this.titale = titale;
        this.address = address;
        Freeshipping = freeshipping;
        this.money = money;
        this.fukuanrenshu = fukuanrenshu;
    }

    @Generated(hash = 72813211)
    public ShangChenLiBiaoBean(Long id, String titale, String address,
            String Freeshipping, String money, String fukuanrenshu,
            boolean isGroupSelected, boolean isEditing, boolean isChildSelected,
            String price, String number, String pdtDesc) {

        this.id = id;
        this.titale = titale;
        this.address = address;
        this.Freeshipping = Freeshipping;
        this.money = money;
        this.fukuanrenshu = fukuanrenshu;
        this.isGroupSelected = isGroupSelected;
        this.isEditing = isEditing;
        this.isChildSelected = isChildSelected;
        this.price = price;
        this.number = number;
        this.pdtDesc = pdtDesc;
    }

    @Generated(hash = 504925721)
    public ShangChenLiBiaoBean() {
    }

  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitale() {
        return titale;
    }

    public void setTitale(String titale) {
        this.titale = titale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFreeshipping() {
        return Freeshipping;
    }

    public void setFreeshipping(String freeshipping) {
        Freeshipping = freeshipping;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getFukuanrenshu() {
        return fukuanrenshu;
    }

    public void setFukuanrenshu(String fukuanrenshu) {
        this.fukuanrenshu = fukuanrenshu;
    }

    public boolean isGroupSelected() {
        return isGroupSelected;
    }

    public void setGroupSelected(boolean groupSelected) {
        isGroupSelected = groupSelected;
    }

    public boolean isEditing() {
        return isEditing;
    }

    public void setEditing(boolean editing) {
        isEditing = editing;
    }

    public boolean isChildSelected() {
        return isChildSelected;
    }

    public void setChildSelected(boolean childSelected) {
        isChildSelected = childSelected;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPdtDesc() {
        return pdtDesc;
    }

    public void setPdtDesc(String pdtDesc) {
        this.pdtDesc = pdtDesc;
    }

    @Override
    public String toString() {
        return "ShangChenLiBiaoBean{" +
                "id=" + id +
                ", titale='" + titale + '\'' +
                ", address='" + address + '\'' +
                ", Freeshipping='" + Freeshipping + '\'' +
                ", money='" + money + '\'' +
                ", fukuanrenshu='" + fukuanrenshu + '\'' +
                '}';
    }

    public boolean getIsChildSelected() {
        return this.isChildSelected;
    }

    public void setIsChildSelected(boolean isChildSelected) {
        this.isChildSelected = isChildSelected;
    }

    public boolean getIsEditing() {
        return this.isEditing;
    }

    public void setIsEditing(boolean isEditing) {
        this.isEditing = isEditing;
    }

    public boolean getIsGroupSelected() {
        return this.isGroupSelected;
    }

    public void setIsGroupSelected(boolean isGroupSelected) {
        this.isGroupSelected = isGroupSelected;
    }
}
