package com.example.xiao.myappshuihu.Perserent;

import com.example.xiao.myappshuihu.Impl.AddMachineImpl;
import com.example.xiao.myappshuihu.model.IsAddMachIneModel;
import com.example.xiao.myappshuihu.view.IsAddMachIneView;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.Perserent
 * 文件名：AddMachInePerserent
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/9/27 0027 17:49
 * 描述：TODO
 */
public class AddMachInePerserent {
    IsAddMachIneModel addMachIneModel;
    IsAddMachIneView addMachIneView;

    public AddMachInePerserent(IsAddMachIneView addMachIneView) {
        this.addMachIneView = addMachIneView;
        addMachIneModel = new AddMachineImpl();
    }

    public void buind(String appid,String machine) {
        if (addMachIneModel != null) {
            addMachIneModel.addMachine(new IsAddMachIneModel.onAddmachineLinersest() {
                @Override
                public void onComplte(Object object) {
                    addMachIneView.showAddReluset(object);
                }
            },appid,machine);
        }
    }
}
