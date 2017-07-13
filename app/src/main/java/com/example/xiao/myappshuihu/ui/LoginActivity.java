package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.LoginBean;
import com.example.xiao.myappshuihu.entity.LoginBens;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.EditTextWithDelete;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.NetWorkUtils;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import me.leefeng.promptlibrary.PromptDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/7/7 0007.
 *  登录界面
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_register;
    private Button btn_login;
    private EditTextWithDelete edit_phone;
    private EditText edit_pwd;
    private PromptDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        pd = new PromptDialog(this);
        //沉浸式
        ImmersionBar.with(this)
                .statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
                .statusBarDarkFont(true,0.2f)    //如果是白色或者透明状态的时候就加上他
                .fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
                .init();

        initView();
    }
    /*
    * 初始化
    * */
    private void initView() {
        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_register.setOnClickListener(this);

        txt_register = (TextView) findViewById(R.id.txt_register);
        txt_register.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
        edit_phone = (EditTextWithDelete) findViewById(R.id.edit_phone);
        edit_pwd = (EditText) findViewById(R.id.edit_pwd);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_register:
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                break;
            case R.id.btn_login:
                //获取用户输入的 用户名 和密码
                String phone = edit_phone.getText().toString().trim();
                String pwd = edit_pwd.getText().toString().trim();
                boolean networkstatus  = NetWorkUtils.isNetworkConnected(getApplicationContext());
                if (networkstatus) {
                    // 用户登录请求
                    loginQuest(phone, pwd);
                } else {
                    pd.showError("请检测网络");
                }
                break;
        }
    }

    private void loginQuest(String phone, String pwd) {
        String url = ConfigUtils.ZhuYuMing +ConfigUtils.DENGLUJIEKOU;
        OkGo.post(url)
                .params("phone",phone)
                .params("password",pwd)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("登录 "+s);
                        //登录解析
                        Gson gson = new Gson();
                        LoginBens lb =  gson.fromJson(s, LoginBens.class);
                        int status = lb.getStatus();
                        if (status == 1) {
                            LoginBens.DataBean lbdb = lb.getData();
                            //这个字段用来做 收货地址
                            String ids =  lbdb.getId();
                            //获取用户登录的 用户名
                            String name = lbdb.getName();
                            //获取用户登录后的 手机号码
                            String phone = lbdb.getPhone();
                            // 获取性别
                            String xingbie = lbdb.getSex();
                            //获取用户登录的头像
                            String touxiang = lbdb.getImg();
                            //获取用户登录成功过后 的 member_id
                            String members = lbdb.getMember_id();

                            //保存用户昵称
                            ShareUtils.putString(getApplicationContext(),"name",name);
                            //保存用户手机号码
                            ShareUtils.putString(getApplicationContext(),"zhanghao",phone);
                            //保存meberiD 在做支付接口的时候 要用到
                            ShareUtils.putString(getApplicationContext(), "member", members);
                            //保存ID 在做收货地址的时候会用到
                            ShareUtils.putString(getApplicationContext(),"ids",ids);
                            finish();

                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        ToastUtil.Short(getApplicationContext(),"登录请求失败");

                    }
                });
    }
}
