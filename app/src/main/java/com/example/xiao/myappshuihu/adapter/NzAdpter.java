package com.example.xiao.myappshuihu.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.httpsocket.HttpConnectionUtils;
import com.example.xiao.myappshuihu.httpsocket.HttpHandler;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.sqlite.NZ_DBhelperManager;
import com.example.xiao.myappshuihu.ui.NZEditActivity;
import com.example.xiao.myappshuihu.view.MessageItem;
import com.example.xiao.myappshuihu.view.SlideView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class NzAdpter extends BaseAdapter implements OnItemClickListener,SlideView.OnSlideListener {

	public class Items extends MessageItem {
		NZ_DBhelperManager.NZYData item;
	}
	private List<Items> listData = new ArrayList<Items>();
	private String MACHINEID;//这两个的添加只是为了在删除闹钟的时候，能够一起取消预约
	private HttpHandler jrhandler;
	private SlideView mLastSlideViewWithStatusOn;

	private List<NZ_DBhelperManager.NZYData> mDataList;

	private Context context;

	private LayoutInflater inf;
	public int onClickIte=0;//记录最后被点击的是那个item，到时候需要关闭该item
	private String[] numberStrings;
	private Handler handler;

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public NzAdpter(Context context, List<NZ_DBhelperManager.NZYData> dataList, HttpHandler jrhandler, String machineid) {
		mDataList = dataList;
		this.jrhandler=jrhandler;
		this.MACHINEID=machineid;
		this.context = context;
		inf = LayoutInflater.from(context);
		for (int i = 0; i < mDataList.size(); i++) {
			Items itemts = new Items();
			itemts.item = dataList.get(i);
			listData.add(itemts);
		}
		numberStrings=new String[]{ context.getString(R.string.monday),context.getString(R.string.tuesday),context.getString(R.string.wednesday),
				context.getString(R.string.thursday),context.getString(R.string.friday),context.getString(R.string.saturday),context.getString(R.string.sunday)};

	}
	
	public void addItemData(NZ_DBhelperManager.NZYData data){
		mDataList.add(data);
		Items itemts = new Items();
		itemts.item = data;
		listData.add(itemts);
		this.notifyDataSetInvalidated();
	}

	public void cleanArray() {
		mDataList.clear();
		listData.clear();
	}

	@Override
	public int getCount() {
		if (listData != null) {
			return listData.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		if (listData != null) {
			return listData.get(arg0);
		}
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(final int index, View contentView, ViewGroup parent) {
		
		SlideView sview = (SlideView)contentView;
		ZDYHolder holder;

		if (contentView == null) {
			holder = new ZDYHolder();
			sview = new SlideView(context);
			View view = LayoutInflater.from(context).inflate(
					R.layout.nz_layout_item, parent, false);
			
			holder.img = (ImageView)view.findViewById(R.id.toggleButton1);
			holder.tx01 = (TextView)view.findViewById(R.id.nz_time_id);
			holder.tx02 = (TextView)view.findViewById(R.id.nz_name_id);
			holder.tx03 = (TextView)view.findViewById(R.id.nz_xq_id);
			holder.tx04 = (TextView)view.findViewById(R.id.nz_txt_id);
			sview.setContentView(view);
			sview.setOnSlideListener(this);
			sview.setTag(holder);
		} else {
			holder = (ZDYHolder)sview.getTag();;
		}

		
		final Items item = listData.get(index);
		item.slideView = sview;
		item.slideView.reset();
		final NZ_DBhelperManager.NZYData bean = listData.get(index).item;
		try {
			sview.setButtonOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO 自动生成的方法存根
					NZ_DBhelperManager.getInstance(context).delete(bean.NZ_ID);
					listData.remove(item);
					notifyDataSetInvalidated();
					if(item.item.NZ_ISOPEN==0){//直接删除

					}else{
						List<NameValuePair> jr_list = new ArrayList<NameValuePair>();
						jr_list.add(new BasicNameValuePair("appid", HttpUrl.APP_ID));
						jr_list.add(new BasicNameValuePair("machineid",MACHINEID ));
						jr_list.add(new BasicNameValuePair("orderid",item.item.ORDER_ID));
						// Toast.makeText(NzActivity.this, data.ORDER_ID, 1).show();
						HttpConnectionUtils httpUtil = new HttpConnectionUtils(
								jrhandler);
						httpUtil.create(1, HttpUrl.CANCELHEAT, jr_list);
						httpUtil.setState(102);//更新预约，和删除预约的功能一样，稍微修改一下提示而已
					}

				}
			});
			
			
			String stime[] = bean.NZ_TIME.split(":");
			String time = "";
			
			if (stime.length>1) {
				int time1 = Integer.parseInt(stime[0]);
				int time2 = Integer.parseInt(stime[1]);
				
				if (time1<10) {
					time = time + "0" + time1;
				}else{
					time = time + time1;
				}
				
				time = time + ":";
				
				if (time2<10) {
					time = time + "0" + time2;
				}else{
					time = time + time2;
				}
			}
			
			
			
			
			holder.tx01.setText(time);
			holder.tx02.setText(bean.NZ_MS);
			holder.tx03.setText(getWeekly(bean.NZ_SW));
			holder.tx04.setText(bean.NZ_NAME);
			
			if(bean.NZ_ISOPEN == 1){
				holder.img.setBackgroundResource(R.drawable.r1);
			}else{
				holder.img.setBackgroundResource(R.drawable.r2);
			}
			holder.img.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(bean.NZ_ISOPEN == 1){
						v.setBackgroundResource(R.drawable.r1);
						if(handler != null){
							Message msg = new Message();
							msg.what = 12;
							msg.obj = bean;
							msg.arg1 = 0;
							handler.sendMessage(msg);
						}
					}else{
						if(handler != null){
							Message msg = new Message();
							msg.what = 11;
							msg.obj = bean;
							msg.arg1 = 1;
							handler.sendMessage(msg);
						}
						v.setBackgroundResource(R.drawable.r2);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sview;
	}





	private String getWeekly(String strings) {
		StringBuffer stringBuffer = new StringBuffer();
		int isqb = 0;
		for (int i = 0; i < 7; i++) {
			char c = strings.charAt(i);
			StringBuffer B = new StringBuffer();
			B.append(c);
			if (B.toString().equals("1")) {
				isqb++;
				if(isqb!=1){
					stringBuffer.append(",");
				}
				stringBuffer.append(numberStrings[i]);
			}
		}
		if (isqb >= 7) {
			stringBuffer = new StringBuffer();
			stringBuffer.append(context.getString(R.string.everyday));
		}
		if (isqb == 0) {
			stringBuffer = new StringBuffer();
			stringBuffer.append(context.getString(R.string.never));
		}
		return stringBuffer.toString();
	}
	
	public static class ZDYHolder {
		ImageView img;
		TextView tx01;
		TextView tx02;
		TextView tx03;
		TextView tx04;

	}
	
	
	
	@Override
	public void onSlide(View view, int status) {
		// TODO 自动生成的方法存根
		if (mLastSlideViewWithStatusOn != null
				&& mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}
		
		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		SlideView sview = (SlideView)view;
		if(sview.equals(mLastSlideViewWithStatusOn) && mLastSlideViewWithStatusOn.getScrollX() != 0){
			mLastSlideViewWithStatusOn.shrink();

		}else{

			onClickIte=position;
			Intent intent = new Intent(context,NZEditActivity.class);
			Bundle bundle = new Bundle();
			NZ_DBhelperManager.NZYData data  = mDataList.get(position);
			bundle.putSerializable("data", data);
			intent.putExtras(bundle);
			//context.startActivity(intent);
			((Activity)context).startActivityForResult(intent,1);//1stand for update

		}
	}

}
