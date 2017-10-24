package com.example.xiao.myappshuihu.Impl;

import com.example.xiao.myappshuihu.entity.SwitchStateBean;
import com.example.xiao.myappshuihu.model.IsSwitchStateModel;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.Impl
 * 文件名：SwitchStateImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/10/9 0009 17:46
 * 描述：获取开关状态的 实现类
 */
public class SwitchStateImpl implements IsSwitchStateModel {
    @Override
    public void switchState(final onSwitchStateLinerser linerser, String machineID, String appid) {
        String ziduan = "/teapot/getstate/machineid/"+machineID+"/appid/"+appid;
        String url = ConfigUtils.SHUIHUZHUYUMING+ziduan;
        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("测试滑动开关状态 "+s);
                        try {
                            JSONObject obj = new JSONObject(s);
                            int status = obj.optInt("status");
                            if (status == 1) {
                                Gson gson = new Gson();
                                SwitchStateBean stateBean = gson.fromJson(s, SwitchStateBean.class);
                                linerser.onComptle(stateBean);
                            }
                        } catch (Exception e) {

                        }
                    }
                });
    }
}
