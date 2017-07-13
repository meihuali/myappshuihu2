package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiao.myappshuihu.R;

/**
 * Created by Administrator on 2017/7/7 0007.
 * //收货地址·界面
 */

public class AddShouHuoDiZhiActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayout2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadders);
        initView();
    }

    private void initView() {
        linearLayout2 = (LinearLayout)findViewById(R.id.linearLayout2);
        linearLayout2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*跳转到选择地址界面*/
            case R.id.linearLayout2:
                startActivity(new Intent(this,DiZhiXuanZheActivity.class));
                break;
        }
    }
}
