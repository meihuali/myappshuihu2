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
 *   地址选择器界面
 */

public class DiZhiXuanZheActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout llay_diquxuanze;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dizhixuanze);
        initView();
    }

    private void initView() {
        llay_diquxuanze = (LinearLayout)findViewById(R.id.llay_diquxuanze);
        llay_diquxuanze.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llay_diquxuanze:
                Intent intent = new Intent(this, CitySelect1Activity.class);

                break;
        }
    }
}
