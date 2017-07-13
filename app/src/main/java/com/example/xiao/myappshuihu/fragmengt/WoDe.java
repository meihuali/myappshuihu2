package com.example.xiao.myappshuihu.fragmengt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.ui.LoginActivity;
import com.example.xiao.myappshuihu.ui.PersonalInformationActivity;
import com.example.xiao.myappshuihu.ui.RegisterActivity;
import com.example.xiao.myappshuihu.ui.ShoppingsActivity;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.gyf.barlibrary.ImmersionBar;


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
    private TextView tv_geren;
    private TextView tv_exit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wode, container, false);
        initView(view);
        return view;

    }
    /* 初始化控件*/
    private void initView(View view) {
        tv_geren = (TextView) view.findViewById(R.id.tv_geren);
        tv_geren.setOnClickListener(this);
        tv_shpoing = (TextView) view.findViewById(R.id.tv_shpoing);
        tv_shpoing.setOnClickListener(this);
        tv_exit = (TextView)view.findViewById(R.id.tv_exit);
        tv_exit.setOnClickListener(this);

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
            //个人资料
            case R.id.tv_geren:
                String zh = ShareUtils.getString(getActivity(),"zhanghao","");
                if (!TextUtils.isEmpty(zh)) { //不等于空才跳转
                    startActivity(new Intent(getActivity(), PersonalInformationActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.tv_exit:
                ShareUtils.deleShare(getActivity(),"zhanghao");
                ToastUtil.Short(getActivity(),"退出成功");
                break;
        }
    }
}
