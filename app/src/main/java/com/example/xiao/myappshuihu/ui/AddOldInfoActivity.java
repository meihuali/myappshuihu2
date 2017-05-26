package com.example.xiao.myappshuihu.ui;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AddOldInfoAdpter;
import com.example.xiao.myappshuihu.connerler.ConnHandler;
import com.example.xiao.myappshuihu.data.OldData;
import com.example.xiao.myappshuihu.entity.LiShiJiLuBean;
import com.example.xiao.myappshuihu.entity.ShuiHuLieBiao;
import com.example.xiao.myappshuihu.httpsocket.HttpConnectionUtils;
import com.example.xiao.myappshuihu.httpsocket.HttpHandler;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.json.JsonParser;
import com.example.xiao.myappshuihu.sqlite.Machinenu_DBhelperManager;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.xlwtech.util.XlwDevice;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 添加已有设备界面
 * @author Administrator
 *
 */
public class AddOldInfoActivity extends AppCompatActivity implements OnClickListener {
	private GridView gridView;
	private Button jumpbtn;
	private AddOldInfoAdpter adpter;
	private List<String> list = new ArrayList<>();
	Button getJumpbtn;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addshebei);
		initView();
		initData();
	}

	private void initView() {
		getJumpbtn = (Button) findViewById(R.id.jump_to_new);
		getJumpbtn.setOnClickListener(this);
		/*初始化控件*/
		gridView = (GridView) findViewById(R.id.gidview);
		adpter = new AddOldInfoAdpter(getApplicationContext(),list);
		gridView.setAdapter(adpter);
		//获取PAAID
		String appId = ToolsGetAppId.getinitAppId(this);

		String  urlsss = ConfigUtils.SHUIHUSHOU+appId+ConfigUtils.SHUIHUHOUZHUI;

		// 网络 请求
		new RxVolley.Builder().callback(new HttpCallback() {
			@Override
			public void onSuccess(String t) {
				parsers(t);

				L.e("ttttttt "+t);
			}
		})
				.url(ConfigUtils.SHUIHUSHOU+appId+ConfigUtils.SHUIHUHOUZHUI) //接口地址
				//设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
				.cacheTime(0)
				//内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
				.contentType(RxVolley.ContentType.FORM)
				//是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
				.shouldCache(false)
				.encoding("UTF-8") //编码格式，默认为utf-8
				.doTask();  //执行请求操作
	}
	/*解析数据*/
	private void parsers(String t) {
		Gson gson = new Gson();
		ShuiHuLieBiao shb = gson.fromJson(t, ShuiHuLieBiao.class);
		String status = shb.getStatus();
		String total = shb.getTotal();
		List<String> lis2= shb.getData();
		list.addAll(lis2);
		adpter.notifyDataSetChanged();
	}

	/*item的点击事件*/
	private void initData() {
		gridView.setOnItemClickListener(adpter);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.jump_to_new:
				Intent intent = new Intent(AddOldInfoActivity.this,AddNewInfoActivity.class);
				startActivity(intent);
				finish();
				break;
		}
	}
}
