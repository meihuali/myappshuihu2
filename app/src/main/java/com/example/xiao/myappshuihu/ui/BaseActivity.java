package com.example.xiao.myappshuihu.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.ui
 * 文件名：BaseActivity
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 下午 6:33
 * 描述：TODO
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setElevation(0);
        //显示返回键
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    //菜单栏操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
