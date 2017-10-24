package com.example.xiao.myappshuihu.Impl;

import com.example.xiao.myappshuihu.entity.AddMachineBean;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.model.IsAddMachIneModel;
import com.example.xiao.myappshuihu.utils.L;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.Impl
 * 文件名：AddMachineImpl
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/27 0027 17:47
 * 描述：搜索到设备后 添加到 服务器接口
 */
public class AddMachineImpl implements IsAddMachIneModel {

    @Override
    public void addMachine(final onAddmachineLinersest linersest, String appid, String Machine) {

        String url = HttpUrl.BIND;
        OkGo.get(url)
                .params("appid",appid)
                .params("machineid",Machine)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("添加设备 "+s);
                        Gson gson = new Gson();
                        AddMachineBean addmachine = gson.fromJson(s, AddMachineBean.class);
                        linersest.onComplte(addmachine);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("添加设备 "+response.message());
                    }
                });

    }
}
