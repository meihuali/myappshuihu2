package com.example.xiao.myappshuihu.data;

import java.io.Serializable;

/**
 * Created by wx091 on 2016/2/29.
 */
public class User implements Serializable{

    public String id="";
    public String appid="";
    public String parent_id="";
    public String username="";
    public String phonenumber="";
    public String email="";
    public String is_online;
    public String is_admin;
    public String is_accept;
    public String headurl="";
    public String leaf;

    public User(String id, String username, String headurl){
        this.username=username;
        this.id=id;
        this.headurl=headurl;
    }
    public User(String s){
        String[] sList=s.split(";");
        if(sList.length==6){
            this.id=sList[0];
            this.appid=sList[1];
            this.username=sList[2];
            this.phonenumber=sList[3];
            this.email=sList[4];
            this.headurl=sList[5];
        }
    }
    public String toString(){
        return id+";"+appid+";"+username+";"+phonenumber+";"+email+";"+headurl;
    }

}
