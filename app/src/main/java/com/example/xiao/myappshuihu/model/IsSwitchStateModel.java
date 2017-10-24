package com.example.xiao.myappshuihu.model;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.model
 * 文件名：IsSwitchStateModel
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/10/9 0009 17:43
 * 描述：主界面圆弧 上的开关按钮 状态
 */
public interface IsSwitchStateModel {
    void switchState(onSwitchStateLinerser linerser,String machineID,String appid);

    interface onSwitchStateLinerser{
        void onComptle(Object object);
    }
}
