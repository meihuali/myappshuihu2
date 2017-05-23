package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.XQAdpter;


public class XQActivity extends Base2Activity {
	private ListView listview;

	private XQAdpter adpter;

	String NZ_SW = "0000000";
	private String txtString;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zdy1);

		listview = (ListView) findViewById(R.id.girdview_id);

		if (!TextUtils.isEmpty(getIntent().getStringExtra("NZ_SW"))) {
			NZ_SW = getIntent().getStringExtra("NZ_SW");

		} else {
			NZ_SW = "0000000";
		}
		txtString=NZ_SW;


		adpter = new XQAdpter(this, handler, NZ_SW);
		listview.setAdapter(adpter);
		listview.setOnItemClickListener(adpter);
	}

	@Override
	public void setActionBar() {
		setActivityTitle(this.getString(R.string.weekly_repeat));
		setRight(this.getString(R.string.finished), new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(XQActivity.this, NZEditActivity.class);
				intent.putExtra("txt", txtString);
				setResult(11, intent);
				finish();
			}
		});
	}

	@Override
	public void findView() {

	}

	@Override
	public void initView() {

	}


	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			txtString = msg.obj.toString();
		};
	};

}