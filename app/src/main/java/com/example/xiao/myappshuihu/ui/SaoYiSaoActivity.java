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

import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.entity.LiaoBaoBean;
import com.example.xiao.myappshuihu.entity.ZDYData;
import com.example.xiao.myappshuihu.saoyisao.ScannerActivity;
import com.example.xiao.myappshuihu.sqlite.DBNullException;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.mylhyl.zxing.scanner.common.Intents;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class SaoYiSaoActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 0;
    // 扫一扫相关 颜色  如果不赋值的话· 扫描上下滚动的就是绿色这里默认 赋值为 支付宝 那种网格的
    private int laserMode = ScannerActivity.EXTRA_LASER_LINE_MODE_1;
    private String mciID;
    /******
     * ‘ 添加或者修改自定义的数据
     ******/
    public ZDYData datasql = new ZDYData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acivity_saoyisao);
        //放入队列就可以销毁·
        BaseAPPliction.addDestoryActivity(SaoYiSaoActivity.this, "SaoYiSaoActivity");
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
                    // 拿到结果 去请求网络
                    String url = ConfigUtils.ZhuYuMing + ConfigUtils.LIAOBAOSHUXING;
                    OkGo.post(url)
                            .params("tea_id", stringExtra)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(String s, Call call, Response response) {
                                    L.e("料包属性  " + s);
                                    Gson gson = new Gson();
                                    LiaoBaoBean liaoBaoBean = gson.fromJson(s, LiaoBaoBean.class);
                                    int status = liaoBaoBean.getStatus();
                                    if (status == 1) {
                                        LiaoBaoBean.DataBean lbbdb = liaoBaoBean.getData();
                                        //获取id
                                        String liaobaoID = lbbdb.getId();
                                        //获取名字
                                        String liaobaomingzi = lbbdb.getTea_name();
                                        //获取料包二维码编号
                                        String liaobaobianhao = lbbdb.getTea_id();
                                        //获取温度
                                        String liaobaowendu = lbbdb.getTemperature();
                                        //获取 是否先煮沸
                                        String liaobaozhufei = lbbdb.getIs_boil();
                                        //获取是否 净化
                                        String liaobaojinghua = lbbdb.getIs_purify();
                                        // 获取 净化时间
                                        String liaobaojinghuashijian = lbbdb.getPurify_time();
                                        //获取该料包对应的图片
                                        String liaobaoanniutupian = lbbdb.getTea_img();
                                        //获取保温时间
                                        String liaobaobaowenshijian = lbbdb.getKeepwarn_time();
                                        //获取 按钮状态是否打开
                                        String liaobaoanniuzhuangtai = lbbdb.getIs_open();

                                        //取出设备号
                                        mciID = ShareUtils.getString(getApplicationContext(), "micID", "");
                                        if (TextUtils.isEmpty(mciID)) {
                                            ToastUtil.Short(getApplicationContext(), "设备号为空");
                                        }
                                        //水壶设备号
                                        datasql.MACHINE_ID = mciID;
                                        //水壶温度
                                        datasql.ZDY_SW = liaobaowendu;
                                        //名字
                                        datasql.ZDY_NAME = liaobaomingzi;
                                        //二维码编号
                                        datasql.ZDY_ErWeiMa = liaobaobianhao;
                                        //是否煮沸
                                        datasql.ZDY_IS_BOIL = liaobaozhufei;
                                        //是否净化
                                        datasql.ZDY_IS_PURIFY = liaobaojinghua;
                                        //净化时间
                                        datasql.ZDY_PURIFY_TIME = liaobaojinghuashijian;
                                        //图片 url
                                        datasql.ZDY_IMAGE = liaobaoanniutupian;
                                        //料包保温时间
                                        datasql.ZDY_KEEPWARN_TIME = liaobaobaowenshijian;
                                        //自定是否打开
                                        datasql.ZDY_ISOPEN = Integer.parseInt(liaobaoanniuzhuangtai);
                                        //.这个字段在这里没什么卵用
                                        datasql.ZDY_ISDE = 0;
                                        //这里请求网络成功 在这里添加 到 集合里面·然后 插入 本地数据
                                        if (datasql.ZDY_ID == null) {
                                            datasql.ZDY_ID = System.currentTimeMillis() + "";
                                            ZDYDataModel.getInstance().insert(datasql);
                                        }
                                        finish();

                                    } else {
                                        ToastUtil.Short(getApplicationContext(), "status不等于1");
                                    }
                                }
                            });
                }
            }


        }
    }
}
