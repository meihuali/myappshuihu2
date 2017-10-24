package com.example.xiao.myappshuihu.ui;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiao.myappshuihu.MainActivity;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.httpsocket.HttpConnectionUtils;
import com.example.xiao.myappshuihu.httpsocket.HttpHandler;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.sqlite.DBNullException;
import com.example.xiao.myappshuihu.sqlite.Machinenu_DBhelperManager;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.GlideUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.StringUtil;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.utils.Utils;
import com.example.xiao.myappshuihu.utils.WifiTools;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 刪除設備的界面
 * 
 * @author Administrator
 * 
 */
public class DelteMachineActivity extends Base2Activity implements OnClickListener {

	private Button connBt,groupBtn;
	private TextView deviceIDtv;

	private EditText deviceNametv;

	DisplayImageOptions options;
	private  String  machineid;
	private String micid;
	private  Button wifi_conn_bt;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delemachine);

		//取出設備號：

		micid = ShareUtils.getString(getApplicationContext(),"micID","");
		micid = ShareUtils.getString(getApplicationContext(),"typeMicid","");
		TextView tv_micd = (TextView) findViewById(R.id.wifi_name_id);
		if (!TextUtils.isEmpty(micid)) {
			tv_micd.setText(micid);
		} else {
			ToastUtil.Short(getApplicationContext(),"没有获取到设备号码：");
		}

		//初始化刪除按鈕
		wifi_conn_bt = (Button) findViewById(R.id.wifi_conn_bt);
		wifi_conn_bt.setOnClickListener(this);

	}

	@Override
	public void setActionBar() {
		setActivityTitle(this.getString(R.string.device_detail));

	}

	@Override
	public void findView() {



	}

	@Override
	public void initView() {


	}


	protected void onDestroy() {
		super.onDestroy();
	};

	private String MACHINEID, IP;
	private boolean isDele;

	private void getIntentData() {
		if (getIntent() != null) {
			MACHINEID = getIntent().getStringExtra("MACHINEID");

			isDele = getIntent().getBooleanExtra("isDele", false);
			deviceIDtv.setText(MACHINEID);
			if (isDele) {
				connBt.setText(this.getString(R.string.delete_device));
				connBt.setTextColor(getResources().getColor(R.color.white));
				connBt.setBackgroundResource(R.drawable.deldev);
				if(MACHINEID.startsWith("03") || MACHINEID.startsWith("05")){
					groupBtn.setVisibility(View.VISIBLE);
				}

			} else {
				IP = getIntent().getStringExtra("IP");
			}

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.wifi_conn_bt:
				delteshebei();
				break;
		}

	}

	private void delteshebei() {
		String appid = ToolsGetAppId.getinitAppId(DelteMachineActivity.this);

		String url = ConfigUtils.SHUIHU_ZONG_JIEKOU+"/app/unbind/machineid/"+micid+"/appid/"+appid;
		OkGo.get(url)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(String s, Call call, Response response) {
						L.e("删除水壶接口 "+s);
						try {
							JSONObject object = new JSONObject(s);
							String status = object.optString("status");
							String result= object.optString("data");
							if (status.equals("1")) {
								ToastUtil.Short(getApplicationContext(), "删除" + result);
								finish();
							} else {
								ToastUtil.Short(getApplicationContext(), "删除失败，从新打开APP刷新" + result);
							}

						} catch (Exception e) {

						}

					}

					@Override
					public void onError(Call call, Response response, Exception e) {
						super.onError(call, response, e);
						L.e("删除水壶接口 "+"失败");
					}
				});


	}

	/**
	 * 更改或者添加设备信息
	 * 
	 * @param state
	 */
	private void addDBData(int state) {


	}



}
