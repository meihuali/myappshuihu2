package com.example.xiao.myappshuihu.ui;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AddNewInfoAdpter;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.httpsocket.SocketCallback;
import com.example.xiao.myappshuihu.httpsocket.SocketConnect;
import com.example.xiao.myappshuihu.httpsocket.SocketData;
import com.example.xiao.myappshuihu.sqlite.UserSP;
import com.example.xiao.myappshuihu.utils.MachineStateData;
import com.example.xiao.myappshuihu.utils.Utils;
import com.example.xiao.myappshuihu.utils.WifiTools;
import com.example.xiao.myappshuihu.view.SearchView;
import com.xlwtech.util.XlwDevice;
import com.xlwtech.util.XlwDevice.XlwDeviceListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 添加设备信息输入界面
 * @author Administrator
 *
 */
public class AddNewInfoActivity extends AppCompatActivity implements OnClickListener, PromptDialog.DialogListener {
	private String ssidNow,strHead;
	
	private Button connBt;
	private TextView wifiName,tv_receive;
	
	private EditText wifiPass;
	
//	private ProgressBar bar;
	
	private SearchView searchView;
	
	private GridView gridView;
	
	private AddNewInfoAdpter adpter;
	
	TextView notivetxt;
	private ArrayList<HashMap<String, Object>>	m_listMac = new ArrayList<HashMap<String, Object>>();
	private ImageView images;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addinfo);
		findViews();
	}

	private void findViews() {
		wifiName = (TextView)findViewById(R.id.wifi_name_id);
		connBt = (Button)findViewById(R.id.wifi_conn_bt);
		wifiPass = (EditText)findViewById(R.id.wifi_pass_id);
		images = (ImageView) findViewById(R.id.images);
		images.setOnClickListener(this);
		searchView = (SearchView) findViewById(R.id.searchView);
		searchView.setVisibility(View.INVISIBLE);
		notivetxt = (TextView) findViewById(R.id.notivetxt);
		tv_receive = (TextView) findViewById(R.id.tv_receive);
//		bar = (ProgressBar)findViewById(R.id.progressBar1);
//		bar.setVisibility(View.GONE);


		gridView = (GridView)findViewById(R.id.gridview_id);
		initViews();
	}

	private void initViews() {
		connBt.setOnClickListener(this);
		wifiPass.setText("");
		initWifi();

		adpter = new AddNewInfoAdpter(AddNewInfoActivity.this,m_listMac);
		gridView.setAdapter(adpter);
		gridView.setOnItemClickListener(adpter);
		adpter.setState(2);
	}

//	@Override
//	public void setActionBar() {
////		setActivityTitle(this.getString(R.string.add_device));
////		setRight(this.getString(R.string.save),this);
//	}

//	@Override
//	public void findView() {
//
//
//	}

//	@Override
//	public void initView() {
//
//	}


	private void initWifi(){
		WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);  		
		if (wifiManager.isWifiEnabled() == false)
		{
			return;
		}
		
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();  
		ssidNow = wifiInfo.getSSID();
		strHead = ssidNow.substring(0, 1);
		if ((ssidNow == null) || (ssidNow.length() <= 0))
		{
			return;
		}
		if (strHead.equals("\"") == true)	
			ssidNow = ssidNow.substring(1, ssidNow.length()-1);
		
		wifiName.setText(ssidNow);
		wifiPass.setText(WifiTools.getwifipwd(ssidNow, this));

		init();
		
	}
	

	
	private void AddMac(String mac)
	{

	}
	
	
	private void addMacData(HashMap<String, Object> amap){
		adpter.addData(amap);
		handler.sendEmptyMessage(101);
	}
	
	private Thread tcpThread;
	SocketConnect connect;
	private String ip ;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 100){
//				bar.setVisibility(View.GONE);
				searchView.stopSearch();
				searchView.setVisibility(View.INVISIBLE);
				tv_receive.setVisibility(View.VISIBLE);

				isConn = false;
				UserSP.getInstance().setUserip(AddNewInfoActivity.this,msg.obj.toString());
				connect = new SocketConnect(new SocketCallback() {

		            @Override
		            public void receive(byte[] buffer) {
		               String strings = new String(buffer);
		               strings = Utils.replaceBlank(strings);
		               Log.e("aa","Server Message ：" + strings);
		               if(strings.indexOf("&&KEY2=5") != -1){

		            	   if (strings.indexOf("POST") != -1) {

		            		   strings = strings.substring(0, strings.indexOf("POST"));
		            		   Log.e("cc","Server Message ：" + strings);
		            	   }
		            	   HashMap<String, Object> map = SocketData.getString(strings);
						   //以前是说要求不能显示04路由器，后来改了
//						   if(map.get("MACHINEID").toString().startsWith("04")){
//							   //对路由器做特殊处理，不需要
//							   Message msg = new Message();
//							   msg.what = 102;//遇到了路由器，做特殊处理
//							   handler.sendMessage(msg);
//							   //showPromptDialog(AddNewInfoActivity.this.getString(R.string.alarm_set_confirm), AddNewInfoActivity.this, 100, false);
//						   }else{
//							   addMacData(map);
//						   }
						   addMacData(map);
		               }
		            }

		            @Override
		            public void disconnect() {

		            }

		            @Override
		            public void connected() {

		            }
		        });
		        connect.setRemoteAddress(msg.obj.toString(), 8080);
		        ip = msg.obj.toString();
		        tcpThread = new Thread(connect);
		        tcpThread.start();
		        connect.write(("&&KEY1=2&GET_HEAT_STATE&ID="+ HttpUrl.APP_ID+"\r\n").getBytes());
			}else if(msg.what == 101){
				adpter.notifyDataSetInvalidated();
			}else if(msg.what == 102){
//				showPromptDialog(AddNewInfoActivity.this.getString(R.string.route_success), AddNewInfoActivity.this, 100, false);
			}

		};
	};


	private Handler handler1 = new Handler(){
		public void handleMessage(Message msg) {
			MachineStateData.MachineStateItemData map = (MachineStateData.MachineStateItemData)msg.obj;
			
		};
	};
	protected void onDestroy() {
		super.onDestroy();
		searchView.stopSearch();
		XlwDevice.getInstance().SmartConfigStop();
		if(connect != null)
			connect.disconnect();
		m_iSmartconfigProgress = 1;
		if(myTimer != null){
			myTimer.cancel();
		}
	};
	
	private void init(){
		XlwDevice.getInstance().SetXlwDeviceListener(new XlwDeviceListener()
		{
			@Override
			public boolean onSmartFound(String mac, String ip, String version, String capability)
			{
				Log.e("aa", "onSmartFound ip:----------------"+ip);
//				AddMac(mac);
				XlwDevice.getInstance().SmartConfigStop();
				Message msg = new Message();
				msg.obj = ip;
				msg.what = 100;
				handler.sendMessage(msg);
//				HashMap<String, Object> amap = new HashMap<String, Object>();
//				amap.put("IP", ip);
//				addMacData(amap);
				return true;
			}
			@Override
			public boolean onSearchFound(String mac, String ip, String version, String capability, String ext) {
				Log.e("aa", "onSearchFound ip:----------------"+ip);
				return true;
			}

			@Override
			public void onStatusChange(String mac, int status)
			{
				Log.e("aa", "ip:----------------onStatusChange");
			}

			@Override
			public void onReceive(String mac, byte[] data, int length) 
			{
				String rsp = new String(data, 0, length);
				Log.e("aa", "ip:----------------onReceive");
			}
			
			@Override
			public void onSendError(String mac, int sn, int err) 
			{
				Log.e("aa", "ip:----------------onSendError");	
//				if (err == XlwDevice.ERR_BUSY)					
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, send busy", mac, sn));
//				else if (err == XlwDevice.ERR_TIMER_OUT)		
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, send time out", mac, sn));
//				else if (err == XlwDevice.ERR_MAC_INVALID)		
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, device mac invalid", mac, sn));
//				else if (err == XlwDevice.ERR_DEVICE_OFFLINE)	
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, device offline", mac, sn));
//				else if (err == XlwDevice.ERR_IP_NOT_EXIST)	    
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, device not in local network", mac, sn));
//				else											
//					SetPrompt(String.format("onSendError(): mac=%s, sn=%d, err=%d", mac, sn, err));
			}
		});		
		XlwDevice.getInstance().SetStatusCheck(3000);
	}
	
	
	 private Timer myTimer;  
	 private long	m_iTickSmartConfigStart = 0;
	private int m_iSmartconfigProgress = 0;
	    private void MySetTimer()
	    {
	    	myTimer = new Timer();
	    	myTimer.schedule( 
					new TimerTask() 
					{
						@Override
						public void run() 
						{	
							if (m_iSmartconfigProgress == 0)
							{
//								if ((System.currentTimeMillis()-m_iTickSmartConfigStart) > 60000)
//								{
//									m_iTickSmartConfigStart = 0;
//									return;
//								}
								m_iSmartconfigProgress = XlwDevice.getInstance().DeviceCount();
								if(m_iSmartconfigProgress > 0){
									Log.e("aa", "11111111111设备连接数"+m_iSmartconfigProgress);
									return;
								}
								Log.e("aa", "设备连接数"+m_iSmartconfigProgress);
//								m_iSmartconfigProgress = XlwDevice.getInstance().SmartConfigProgressGet();	
							}		
						}
					} , 0, 1000);   
	    	
	    }
	
	
	private void connWifi(String wifiName,String wifiPass){
		
		if (XlwDevice.getInstance().SmartConfigStart(wifiName, wifiPass, 60000) == false)
    	{
    		return;
    	}
		
		WifiTools.updatawifilist(wifiName, wifiPass, this);
		
		m_iTickSmartConfigStart = System.currentTimeMillis();     
	}

	private boolean isConn = false;
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.wifi_conn_bt){
			if(isConn){
				return;
			}
			String wifiPassString = wifiPass.getText().toString();
			if(wifiPassString.equals("")){
				return;
			}
			notivetxt.setVisibility(View.INVISIBLE);
			
			isConn = true;
//			bar.setVisibility(View.VISIBLE);
			searchView.beginSearch();
			searchView.setVisibility(View.VISIBLE);
			
			connWifi(ssidNow,wifiPassString);
			MySetTimer();
		}
		switch (v.getId()) {
			//返回键
			case R.id.images:
				finish();
				break;
		}

	}

	@Override
	public void doComfirm(int flag) {
		finish();
	}

	@Override
	public void doCancel(int flag) {
		finish();
	}

	@Override
	public void doBack(int flag) {
		finish();
	}
}