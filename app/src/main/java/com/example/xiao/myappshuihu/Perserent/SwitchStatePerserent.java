package com.example.xiao.myappshuihu.Perserent;

import com.example.xiao.myappshuihu.Impl.SwitchStateImpl;
import com.example.xiao.myappshuihu.model.IsSwitchStateModel;
import com.example.xiao.myappshuihu.view.IsSwitchStateView;

import org.greenrobot.greendao.annotation.Id;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.Perserent
 * 文件名：SwitchStatePerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/10/9 0009 17:48
 * 描述：TODO
 */
public class SwitchStatePerserent {
    IsSwitchStateModel switchStateModel;
    IsSwitchStateView switchStateView;

    public SwitchStatePerserent(IsSwitchStateView switchStateView) {
        this.switchStateView = switchStateView;
        switchStateModel = new SwitchStateImpl();
    }

    public void getSwitchState(String machineId,String appid) {
        if (switchStateModel != null) {
            switchStateModel.switchState(new IsSwitchStateModel.onSwitchStateLinerser() {
                @Override
                public void onComptle(Object object) {
                    switchStateView.showSwitchStateRelust(object);
                }
            },machineId,appid);
        }
    }
}
