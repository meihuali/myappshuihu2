package com.example.xiao.myappshuihu.saoyisao;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.ui.SaoYiSaoActivity;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.google.zxing.Result;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.common.Intents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扫描
 */
public class ScannerActivity extends DeCodeActivity {

    public static final String EXTRA_LASER_LINE_MODE = "laser_line_mode";
    public static final int EXTRA_LASER_LINE_MODE_0 = 0;
    public static final int EXTRA_LASER_LINE_MODE_1 = 1;
    public static final int EXTRA_LASER_LINE_MODE_2 = 2;
    public static final int APPLY_READ_EXTERNAL_STORAGE = 0x111;

    private ScannerView mScannerView;
    private Result mLastResult;
    private int laserMode;
    public static Map<String, Activity> destoryMap = new HashMap<String, Activity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        mScannerView = (ScannerView) findViewById(R.id.scanner_view);
        mScannerView.setOnScannerCompletionListener(this);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mScannerView.toggleLight(isChecked);
            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(ScannerActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //权限还没有授予，需要在这里写申请权限的代码
                    ActivityCompat.requestPermissions(ScannerActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, APPLY_READ_EXTERNAL_STORAGE);
                }
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            laserMode = extras.getInt(EXTRA_LASER_LINE_MODE);
        }
        mScannerView.setMediaResId(R.raw.baidu_beep);//设置扫描成功的声音
        mScannerView.setDrawText("将二维码放入框内",true);
        mScannerView.setDrawTextColor(Color.RED);

//        mScannerView.setLaserFrameTopMargin(100);//扫描框与屏幕上方距离
//        mScannerView.setLaserFrameSize(200, 200);//扫描框大小
//        mScannerView.setLaserFrameCornerLength(25);//设置4角长度
//        mScannerView.setLaserLineHeight(5);//设置扫描线高度

        switch (laserMode) {
            case EXTRA_LASER_LINE_MODE_0:
                mScannerView.setLaserLineResId(R.drawable.wx_scan_line);//线图
                break;
            case EXTRA_LASER_LINE_MODE_1:
                mScannerView.setLaserGridLineResId(R.drawable.zfb_grid_scan_line);//网格图
                mScannerView.setLaserFrameBoundColor(0xFF26CEFF);//支付宝颜色
                break;
            case EXTRA_LASER_LINE_MODE_2:
                mScannerView.setLaserColor(Color.RED);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == APPLY_READ_EXTERNAL_STORAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(ScannerActivity.this, "请给予权限", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume() {
        mScannerView.onResume();
        resetStatusView();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (mLastResult != null) {
                    restartPreviewAfterDelay(0L);
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void restartPreviewAfterDelay(long delayMS) {
        mScannerView.restartPreviewAfterDelay(delayMS);
        resetStatusView();
    }

    private void resetStatusView() {
        mLastResult = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_CANCELED && resultCode == Activity.RESULT_OK) {

        }
    }

    public static void gotoActivity(Activity activity, boolean isBackResult, int laserMode) {
        activity.startActivityForResult(new Intent(Intents.Scan.ACTION)
                        .putExtra(ScannerActivity.EXTRA_RETURN_SCANNER_RESULT, isBackResult)
                        .putExtra(EXTRA_LASER_LINE_MODE, laserMode)
                , ScannerActivity.REQUEST_CODE_SCANNER);
    }

    @Override
    public void onBackPressed() {
        ToastUtil.Short(getApplicationContext(),"监听返回键");
        //删除指定的activity
        BaseAPPliction.destoryActivity("SaoYiSaoActivity");
        super.onBackPressed();
        //删除当前扫描的界面
        finish();
    }

}
