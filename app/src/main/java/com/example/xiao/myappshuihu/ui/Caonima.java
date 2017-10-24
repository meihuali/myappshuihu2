package com.example.xiao.myappshuihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anonymous.greendao.model.NZYDataModel;
import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.dialog
 * 文件名：Caonima
 * 创建者 ：$梅华黎
 * 创建时间： 2017/8/15 17:56
 * 描述：自己寫的添加水壺界面·並不是搜索后跳轉的 那個添加
 */
public class Caonima extends AppCompatActivity implements View.OnClickListener {
    private TextView wifi_name_id;
    private String machineid;
    private Button wifi_conn_bt;
    private String micid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmachine);
        initView();

    }

    private void initView() {
        wifi_conn_bt = (Button) findViewById(R.id.wifi_conn_bt);
        wifi_conn_bt.setOnClickListener(this);

        wifi_name_id = (TextView) findViewById(R.id.wifi_name_id);
        //这里获取设备号
        machineid = ShareUtils.getString(getApplicationContext(),"micID","");
        //这里设置设备号
        if (!TextUtils.isEmpty(machineid)) {
            wifi_name_id.setText(machineid);
        } else {
            wifi_name_id.setText("11111111");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.wifi_conn_bt:
                bangdingshebei();
                break;
        }
    }
    /*
    * 绑定设备
    * */
    private void bangdingshebei() {
        String appid =  ToolsGetAppId.getinitAppId(Caonima.this);
        micid =  ShareUtils.getString(getApplicationContext(),"micID","");
        // String url = ConfigUtils.SHUIHU_ZONG_JIEKOU+"/app/bind/" +"/"+micid+"/appid/"+appid;
        String url = ConfigUtils.SHUIHU_ZONG_JIEKOU+"/app/bind/machineid/"+micid+"/appid/"+appid;

        OkGo.get(url)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        L.e("绑定手机 "+s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            String statsu = jsonObject.optString("status");
                            String data =  jsonObject.optString("data");
                            if (statsu.equals("1")) {
                                //這裡網數據庫裡面添加 數據
                                addSqliteDb();
                                ToastUtil.Short(getApplicationContext(), "绑定水壶成功");
                                BaseAPPliction.destoryActivity("AddOldInfoActivity");
                                finish();
                            } else {
                                ToastUtil.Short(getApplicationContext(), "您已经绑定过了！");
                            }
                        } catch (Exception e) {
                            ToastUtil.Short(getApplicationContext(),"绑定水壶接口解析失败");
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        L.e("绑定手机 "+"失败");
                    }
                });
    }
    /*
    * 網數據庫裡面添加數據
    *
    * */
    private void addSqliteDb() {

        ZDYDataModel.getInstance().addDefault(micid);
//        mList = ZDYDataModel.getInstance().getAlermList(1, machineid);
//        NZYDataModel.getInstance().addDefault(mList, machineid);
    }



}
