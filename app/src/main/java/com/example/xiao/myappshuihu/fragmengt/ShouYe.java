package com.example.xiao.myappshuihu.fragmengt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.dialog.PopupWindowRigth;

import razerdp.basepopup.BasePopupWindow;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：ShouYe
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 4:36
 * 描述：TODO
 */
public class ShouYe extends Fragment implements View.OnClickListener {
    private Button btn_add;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shouye,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                PopupWindowRigth pwr = new PopupWindowRigth(getActivity());
                pwr.showPopupWindow(R.id.btn_add);

            break;
        }
    }
}
