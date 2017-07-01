package com.example.xiao.myappshuihu.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.saoyisao.ScannerActivity;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.lzy.okgo.OkGo;
import com.mylhyl.zxing.scanner.common.Intents;


/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class SaoYiSaoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0;
    // 扫一扫相关 颜色  如果不赋值的话· 扫描上下滚动的就是绿色这里默认 赋值为 支付宝 那种网格的
    private int laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_saoyisao);
        //放入队列就可以销毁·
        BaseAPPliction.addDestoryActivity(SaoYiSaoActivity.this,"SaoYiSaoActivity");
        initView();
    }

    /*启动扫描二维码*/
    private void initView() {
        //打开扫描界面扫描条形码或二维码
        if (ContextCompat.checkSelfPermission(SaoYiSaoActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(SaoYiSaoActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 60);
        } else {
            //权限已经被授予，在这里直接写要执行的相应方法即可
            ScannerActivity.gotoActivity(SaoYiSaoActivity.this,
                    true, laserMode);//
        }
    }
/*
* 扫描后的结果回调  你跳转呢？跳转到 ScannerAcitivit 那个操作在哪里？
* */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {
            if (requestCode == ScannerActivity.REQUEST_CODE_SCANNER) {
                if (data != null) {
                    String stringExtra = data.getStringExtra(Intents.Scan.RESULT);
                    Log.e("扫描结果 ", "" + stringExtra);
                    Toast.makeText(getApplicationContext(), "扫描结果" + stringExtra, Toast.LENGTH_SHORT).show();
                    // 拿到结果 去请求网络
//                    OkGo.post(url)
//                            .params("")
                    finish();
                }
            }
        }
    }






}
