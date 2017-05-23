package com.example.xiao.myappshuihu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.ShuiHuLieBiao;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.utils.StringUtil;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.HashMap;
import java.util.List;

public class AddOldInfoAdpter extends BaseAdapter implements OnItemClickListener {
	private Context context;
	private List<String> dataList;
	private LayoutInflater inflater;

	public AddOldInfoAdpter(Context context, List<String> dataList) {
		this.dataList = dataList;
		this.context = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {

		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int index, View contentView, ViewGroup parent) {
		Holder vh = null;
		//第一次加载
		if (contentView == null) {
			vh = new Holder();
			contentView = inflater.inflate(R.layout.main_item, null);
			vh.img_ysh = (ImageView) contentView.findViewById(R.id.img_ysh);
			vh.tv_shuihu = (TextView) contentView.findViewById(R.id.tv_shuihu);
			//设置缓存
			contentView.setTag(vh);
		} else {
			vh = (Holder) contentView.getTag();
		}
		//下面开始设置数据到控件上·
		vh.tv_shuihu.setText("水壶");

		return contentView;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

	}

	public  class Holder {
		ImageView img_ysh;
		TextView tv_shuihu;
	}

}
