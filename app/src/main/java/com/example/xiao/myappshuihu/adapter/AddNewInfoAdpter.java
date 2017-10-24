package com.example.xiao.myappshuihu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.ui.AddNewInfoActivity;
import com.example.xiao.myappshuihu.ui.MachineActivity;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.PicassoUtils;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.StringUtil;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import java.util.HashMap;
import java.util.List;

public class AddNewInfoAdpter extends BaseAdapter implements
		OnItemClickListener {

	private List<HashMap<String, Object>> mDataList;

	private Activity context;

	private LayoutInflater inf;

	DisplayImageOptions options;

	private int state;
	private String imageframe;
	private String machineid;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public AddNewInfoAdpter(Activity context,
							List<HashMap<String, Object>> dataList) {
		options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		mDataList = dataList;
		this.context = context;
		inf = LayoutInflater.from(context);
	}

	public void addData(HashMap<String, Object> bean) {
		mDataList.add(bean);
	}

	public void cleanArray() {
		for (int i = 0; i < mDataList.size(); i++) {
			mDataList.clear();
		}
	}

	@Override
	public int getCount() {
		if (mDataList != null) {
			return mDataList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		if (mDataList != null) {
			return mDataList.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}


	public View getView(int index, View contentView, ViewGroup parent) {
		//Holder holder  = null;
		if (contentView == null) {
			//holder = new Holder();
			contentView = inf.inflate(R.layout.new_info_item, null);
			// holder.tx01 = (TextView)view.findViewById(R.id.plan_item_txt2);
			// holder.tx02 = (TextView)view.findViewById(R.id.plan_item_txt3);
			// holder.tx03 = (TextView)view.findViewById(R.id.plan_item_txt);
			//holder.imageView_ItemImage = (ImageView) contentView.findViewById(R.id.imageView_ItemImage);
			//contentView.setTag(holder);
		}

		ImageView imageView_ItemImage = (ImageView) contentView
				.findViewById(R.id.imageView_ItemImage);
		TextView main_item_txt = (TextView) contentView
				.findViewById(R.id.main_item_txt);
		// main_item_txt.setVisibility(View.GONE);
		// StateViewHolder holder = (StateViewHolder) view.getTag();
		HashMap<String, Object> bean = mDataList.get(index);

		machineid = null;
		if (bean.get("machineid") != null) {
			machineid = bean.get("machineid").toString();
		} else {
			machineid = bean.get("MACHINEID").toString();
		}

		imageframe = machineid.substring(0, 6);
		L.e("imageframe  "+imageframe);
		String url = "HttpUrl.IMGURL + imageframe + \".png\"";
		if (!TextUtils.isEmpty(url)) {
			PicassoUtils.loadImageViewSize(context, url, 140, 140, imageView_ItemImage);
		} else {
			Toasts.makeTexts(context,"图片路径为空");
		}

		main_item_txt.setText(StringUtil.getMachineName(context, machineid));
		try {
			// holder.tx01.setText(bean.getMuscle());
			// holder.tx02.setText(bean.getFat());
			// holder.tx03.setText(bean.getRecordDate());
			// holder.idString = bean.getCustomerStateId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentView;
	}

	public static class Holder {
		ImageView imageView_ItemImage;
		TextView tx01;
		TextView tx02;
		TextView tx03;
		public String idString;

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
							long id) {
		Toasts.makeTexts(context,"跳转界面");
//		HashMap<String, Object> bean = mDataList.get(position);
		//保存下 mic 设备号的 ID
		ShareUtils.putString(context,"micID",machineid);
//		Activity activity = ((Activity) context); //强转
		Intent intent = new Intent(context, MachineActivity.class);//运行看看
		intent.putExtra("MACHINEID", machineid); //  用Intent 传值 必须在 另外一Activity 接受这个值才可以跳
		context.startActivity(intent);
		context.finish();
	}


}

