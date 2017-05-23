package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.xys.libzxing.zxing.activity.CaptureActivity;


/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class SaoYiSaoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_saoyisao);
        initView();
    }

    /*启动扫描二维码*/
    private void initView() {
        //打开扫描界面扫描条形码或二维码
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描后 回调结果
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toasts.makeTexts(getApplicationContext(),scanResult);
            finish();
        }
    }
    //返回键的方法


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();

    }
}
