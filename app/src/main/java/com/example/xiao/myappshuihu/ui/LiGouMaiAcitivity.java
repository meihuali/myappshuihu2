package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.nfc.NdefRecord;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoBean;
import com.example.xiao.myappshuihu.entity.ZfbBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.GlideUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/6 0006.
 *   立即购买
 */

public class LiGouMaiAcitivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout ll_layout_weixin; //微信父控件
    private ImageView img_weixin_pay; //微信子控件
    private LinearLayout ll_layout_zfb; //支付宝 父控件
    private ImageView img_zfb_pay; //支付宝 父控件
    private ImageView image_back;
    public String danjia,counts;
    public EditText et_counts;
    private String shangpingName;
    private Button ll_layout_comfimr;
    private double sumMoneys;
    private String member;
    private int type = 1;
    public static final int SDK_PAY_FLAG = 0;
    public TextView tv_chanpingmingche;
    private TextView tv_countss;
    private TextView tv_moneyes;
    private ImageView img_shangping;
    private LinearLayout ll_layout_shouhuodizhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lijigoumai);
        initGetData();
        initViews();
    }
    /*
    *      //获取从 webview 传递过来的商品 对象
    * */
    private void initGetData() {
        Intent intent = getIntent();
        //获取商品图片
        String imges = intent.getStringExtra("imges");
        String images = ConfigUtils.lijiegoumaijiegou+imges;
        img_shangping = (ImageView)findViewById(R.id.img_shangping);
        GlideUtils.loadImageView(getApplicationContext(),images,img_shangping);
        ShangPinLieBiaoBean.DataBean splb1  = (ShangPinLieBiaoBean.DataBean) intent.getSerializableExtra("shangpingduixiangWeb");
        try {
            //获取当前商品单价金额
            danjia =  splb1.getPrice();
            //获取当前库存个数
            counts =  splb1.getRurchase_number();
            shangpingName = splb1.getName();

        } catch (Exception e) {
            ToastUtil.Short(getApplicationContext(),"对象为空");
        }
    }

    /*
    * 控件初始化
    * */
    private void initViews() {
        //微信
        ll_layout_weixin = (LinearLayout) findViewById(R.id.ll_layout_weixin);
        ll_layout_weixin.setOnClickListener(this);
        img_weixin_pay = (ImageView) findViewById(R.id.img_weixin_pay);
        //支付宝
        ll_layout_zfb = (LinearLayout) findViewById(R.id.ll_layout_zfb);
        ll_layout_zfb.setOnClickListener(this);
        img_zfb_pay = (ImageView) findViewById(R.id.img_zfb_pay);
        //左上角返回箭头
        image_back = (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(this);
        et_counts = (EditText)findViewById(R.id.et_counts);
        ll_layout_comfimr = (Button)findViewById(R.id.ll_layout_comfimr);
        ll_layout_comfimr.setOnClickListener(this);
        tv_chanpingmingche = (TextView)findViewById(R.id.tv_chanpingmingche);
        tv_chanpingmingche.setText(shangpingName);
        tv_countss = (TextView)findViewById(R.id.tv_countss);
        tv_countss.setText(counts);
        tv_moneyes = (TextView) findViewById(R.id.tv_moneyes);
        tv_moneyes.setText(danjia);
        //收货地址
        ll_layout_shouhuodizhi = (LinearLayout) findViewById(R.id.ll_layout_shouhuodizhi);
        ll_layout_shouhuodizhi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回事件
            case R.id.image_back:
                finish();
                break;
            /*
            * 微信支付
            * */
            case R.id.ll_layout_weixin:
                //设置字段 type为 1 代表是 微信支付
                type = 1;
                img_weixin_pay.setBackground(getDrawable(R.drawable.dagou));
                img_zfb_pay.setBackground(getDrawable(R.drawable.budagou));


                break;
            /*
            * 支付宝支付
            * */
            case R.id.ll_layout_zfb:
                //设置字段type为 2 代表是支付宝
                type = 2;
                img_weixin_pay.setBackground(getDrawable(R.drawable.budagou));
                img_zfb_pay.setBackground(getDrawable(R.drawable.dagou));
                //获取登录返回的 member_id
                member = ShareUtils.getString(getApplicationContext(),"ids","");

                //获取用户选择某一件商品后 他输入的个数
                String  getcounts = et_counts.getText().toString().trim();
                //将用户输入的个数 转成 double类型
                double doubleCounts = Double.parseDouble(getcounts);
                // 将 单价 也转成 double 类型的
                double doubleDanJia = Double.parseDouble(danjia);
                //然后用 用户输入的个数 乘以 商品的单价 得到总计
                sumMoneys = doubleCounts * doubleDanJia;

                break;
            /*
            * 确认支付
            * */
            case R.id.ll_layout_comfimr:
                //判断用户选择的是支付宝还是 微信支付
                if (type == 1) {//表示微信支付

                } else if (type == 2) {  //表示是支付宝
                    // 获取服务器返回的订单接口
                    getDataJekou(sumMoneys,shangpingName,member);
                }
                break;
            //收货地址
            case R.id.ll_layout_shouhuodizhi:
                startActivity(new Intent(getApplicationContext(),AddShouHuoDiZhiActivity.class));
                break;


        }
    }

    private void getDataJekou(double sumMoneys, String shangpingName,String member_id) {
        String url = ConfigUtils.ZhuYuMing+ConfigUtils.LIJIGOUMAI;
        OkGo.post(url)
                .params("money",sumMoneys)
                .params("body",shangpingName)
                .params("app_type","2")
                .params("member_id",member_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("立即购买 "+s);
                        Gson gson = new Gson();
                        ZfbBean zb = gson.fromJson(s, ZfbBean.class);
                        int status =  zb.getStatus();
                        if (status == 1) {
                            String dingdanghao = zb.getData();
                            // 掉起支付宝
                            AlipayZhifu(dingdanghao);
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(),"立即购买订单号请求失败");
                    }
                });
    }

    /*该方法是掉起支付宝 支付界面的 */
    private void AlipayZhifu(String dingdanhao) {
        final String orderInfo = dingdanhao;   // 订单信息
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(LiGouMaiAcitivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /*
    * 支付宝结果回调
    * */
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG :
                {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    L.e("resyktInfo " +resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(LiGouMaiAcitivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(LiGouMaiAcitivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

            }
        }
    };
}
