package com.example.xiao.myappshuihu.fragmengt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.ui.ShoppingsActivity;



/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：WoDe
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 5:05
 * 描述：TODO
 */
public class WoDe extends Fragment implements View.OnClickListener {
    private TextView tv_shpoing;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wode, container, false);
        initView(view);
        return view;

    }
        /* 初始化控件*/
    private void initView(View view) {
        tv_shpoing = (TextView) view.findViewById(R.id.tv_shpoing);
        tv_shpoing.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_shpoing:
                String styls = "";
                Intent intent = new Intent(getActivity(), ShoppingsActivity.class);
                intent.putExtra("styls",styls);
                startActivity(intent);

//                String styls = "";
//                Intent intent = new Intent(getActivity(), ShoppingCartActivity.class);
//                intent.putExtra("styls",styls);
//                startActivity(intent);
            break;
        }
    }
}
