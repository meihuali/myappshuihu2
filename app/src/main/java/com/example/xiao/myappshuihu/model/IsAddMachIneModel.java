package com.example.xiao.myappshuihu.model;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.model
 * 文件名：IsAddMachIneModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/27 0027 17:45
 * 描述：该接口是搜索到设备后添加设备
 */
public interface IsAddMachIneModel {
    void addMachine(onAddmachineLinersest linersest,String appid,String Machine);

    interface onAddmachineLinersest{
        void onComplte(Object object);
    }
}
