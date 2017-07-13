package com.example.xiao.myappshuihu.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.entity.ResgertsBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.EditTextWithDelete;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    //显示 隐藏密码的按钮
    private ImageView img_eye;
    private boolean isSeen = false,isRead = true;
    private TextView remyback;
    //手机号码
    private EditTextWithDelete edit_phone;
    //用户设置都密码
    private EditText edit_pwd;
    //用户添加的验证码
    private EditText edit_verifycode;
    //用户姓名
    private  EditTextWithDelete ed_names;
    //注册按钮
    private Button btn_register;
    //注册下面按钮的 那个关于详细阅读说明 选择框
    private ImageView ck;
    //点击按钮获取短信验证码
    private TextView tv_hqyzm;
    private PromptDialog pd;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regster);

        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .statusBarDarkFont(true,0.2f)    //如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();
        initView();
    }
    /*初始化控件*/
    private void initView() {
        //获取验证码
        edit_verifycode = (EditText) findViewById(R.id.edit_verifycode);
        tv_hqyzm = (TextView) findViewById(R.id.tv_hqyzm);
        tv_hqyzm.setOnClickListener(this);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        edit_phone = (EditTextWithDelete) findViewById(R.id.edit_phone);
        edit_phone.setOnClickListener(this);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);
        edit_pwd.setOnClickListener(this);
        edit_verifycode = (EditText) findViewById(R.id.edit_verifycode);
        edit_verifycode.setOnClickListener(this);
        ed_names = (EditTextWithDelete) findViewById(R.id.ed_names);
        ed_names.setOnClickListener(this);
        remyback = (TextView) findViewById(R.id.remyback);
        remyback.setOnClickListener(this);
        img_eye = (ImageView) findViewById(R.id.img_eye);
        img_eye.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remyback:
                finish();
                break;
            //显示隐藏账号密码
            case R.id.img_eye:
                if (isSeen == false) {
                    isSeen = true;
                    edit_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    img_eye.setImageResource(R.drawable.eye_right);
                } else {
                    isSeen = false;
                    edit_pwd.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    img_eye.setImageResource(R.drawable.eye);
                }
                break;
            //点击注册
            case R.id.btn_register:
                String ed_phone = edit_phone.getText().toString().trim();
                String ed_pwd = edit_pwd.getText().toString().trim();
                String ed_verifycode = edit_verifycode.getText().toString().trim();
                String ed_name = ed_names.getText().toString().trim();

                if (!TextUtils.isEmpty(ed_phone) & !TextUtils.isEmpty(ed_pwd) &
                        !TextUtils.isEmpty(ed_verifycode) & !TextUtils.isEmpty(ed_name)) {
                    init(); //跟sharSDK 服务器校验 短信验证码

                }
                // 注册的 方法
                regsetRquest(ed_phone,ed_pwd,ed_name);

                break;

            //点击获取短信验证码
            case R.id.tv_hqyzm:
                getSMS();
                break;
        }
    }

    private void regsetRquest(String ed_phone,String ed_pwd,String ed_name) {
        String url = ConfigUtils.ZhuYuMing+ConfigUtils.USER_RESETR;
        OkGo.post(url)
                .params("phone",ed_phone)
                .params("name",ed_name)
                .params("password",ed_pwd)
                .params("okpassword",ed_pwd)
                .params("sex","0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("注册结果 "+s);
                        Gson gson = new Gson();
                        ResgertsBean rb = gson.fromJson(s, ResgertsBean.class);
                        int status =  rb.getStatus();
                        if (status == 1) {
                            String ddd = rb.getData();
                            ToastUtil.Short(getApplicationContext(),ddd);
                            //这里跳转到登录界面
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        } else {
                            ToastUtil.Short(getApplicationContext(),rb.getData());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(),"注册接口请求失败");
                        L.e("失败原因 "+response.message());
                    }
                });


    }

    private void init() {
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        //goMainActivity();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }

                } else {
                    ((Throwable) data).printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplication(), "验证失败", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        };
        //注册信息回调
        SMSSDK.registerEventHandler(eventHandler);
    }

    private void getSMS() {
        String ed_phone = edit_phone.getText().toString().trim();
        if (TextUtils.isEmpty(ed_phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (ed_phone.length() != 11) {
            Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "验证码已发送，请注意查看！", Toast.LENGTH_SHORT).show();
        SMSSDK.getVerificationCode("86", ed_phone);
    }


}
