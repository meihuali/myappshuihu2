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
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.anonymous.greendao.model.ZDYDataModel;
import com.example.xiao.myappshuihu.Perserent.AddMachInePerserent;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.AddNewInfoAdpter;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.entity.AddMachineBean;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.httpsocket.SocketCallback;
import com.example.xiao.myappshuihu.httpsocket.SocketConnect;
import com.example.xiao.myappshuihu.httpsocket.SocketData;
import com.example.xiao.myappshuihu.sqlite.UserSP;
import com.example.xiao.myappshuihu.utils.EditTextWithDelete;
import com.example.xiao.myappshuihu.utils.MachineStateData;
import com.example.xiao.myappshuihu.utils.NetWorkUtils;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.utils.Utils;
import com.example.xiao.myappshuihu.utils.WifiTools;
import com.example.xiao.myappshuihu.view.IsAddMachIneView;
import com.example.xiao.myappshuihu.view.SearchView;
import com.gyf.barlibrary.ImmersionBar;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.nostra13.universalimageloader.utils.L;
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
public class AddNewInfoActivity extends AppCompatActivity implements OnClickListener, PromptDialog.DialogListener, IsAddMachIneView {
	private String ssidNow,strHead;

	private Button connBt;
	private TextView wifiName,tv_receive;

	private EditTextWithDelete wifiPass;

//	private ProgressBar bar;

	private SearchView searchView;

	private GridView gridView;

	private AddNewInfoAdpter adpter;

	private LinearLayout notivetxt;
	private ArrayList<HashMap<String, Object>>	m_listMac = new ArrayList<HashMap<String, Object>>();
	private ImageView image_back;
	private int type = 1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addinfo);

		//沉浸式
		ImmersionBar.with(this)
				.statusBarColor(R.color.white) //指定主题颜色 意思 是在这里可以修改 styles 里面的主题颜色
				 .statusBarDarkFont(true,0.2f)  //  如果是白色或者透明状态的时候就加上他
				.fitsSystemWindows(true) //解决状态栏和布局重叠问题，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色
				.init();
		findViews();
	}

	private void findViews() {
		wifiName = (TextView)findViewById(R.id.wifi_name_ids);
		connBt = (Button)findViewById(R.id.wifi_conn_bt);
		wifiPass = (EditTextWithDelete) findViewById(R.id.wifi_pass_id);
		image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(this);
		searchView = (SearchView) findViewById(R.id.searchView);
		searchView.setOnClickListener(this);
		searchView.setVisibility(View.GONE);
		notivetxt = (LinearLayout) findViewById(R.id.notivetxt);
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






	private void initWifi(){
		WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
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
	private String machineid;
	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 100){
//				bar.setVisibility(View.GONE);
				searchView.stopSearch();
				searchView.setVisibility(View.INVISIBLE);
				tv_receive.setVisibility(View.VISIBLE);
				type = 2;
				connBt.setText("添加设备");
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

							if (map != null) {
								machineid = map.get("MACHINEID").toString();
								ShareUtils.putString(getApplicationContext(),"micID",machineid);
							}
							addMacData(map);
						}
					}

					@Override
					public void disconnect() {

						com.example.xiao.myappshuihu.utils.L.e("测试");
					}

					@Override
					public void connected() {
						com.example.xiao.myappshuihu.utils.L.e("测试");

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
			@Override   //搜索成功
			public boolean onSmartFound(String mac, String ip, String version, String capability)
			{
				//	Log.e("aa", "onSmartFound ip:----------------"+ip);
				L.e("测试中 "+"onSmartFound "+capability);

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
				//Log.e("aa", "onSearchFound ip:----------------"+ip);
				L.e("测试中 "+"onSearchFound");

				return true;
			}

			@Override  //搜索中····的回调
			public void onStatusChange(String mac, int status)
			{
				//Log.e("aa", "ip:----------------onStatusChange");
				L.e("测试中 "+"onStatusChange");
			}

			@Override
			public void onReceive(String mac, byte[] data, int length)
			{
				String rsp = new String(data, 0, length);
				//Log.e("aa", "ip:----------------onReceive");
				L.e("测试中 "+"onReceive");
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

				L.e("测试中 "+"onSendError");
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
					public void run(){
						if (m_iSmartconfigProgress == 0){
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

		if (XlwDevice.getInstance().SmartConfigStart(wifiName, wifiPass, 60000) == false) {
			return;
		} else {
			WifiTools.updatawifilist(wifiName, wifiPass, this);
			m_iTickSmartConfigStart = System.currentTimeMillis();
		}


	}


	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.wifi_conn_bt){
			if (type == 1) {
				boolean wifiStatus = NetWorkUtils.isWifi(getApplicationContext());
				if (wifiStatus) {
					String wifiPassString = wifiPass.getText().toString();
					if (!wifiPassString.equals("")) {
						notivetxt.setVisibility(View.INVISIBLE);
						searchView.beginSearch();
						searchView.setVisibility(View.VISIBLE);
						connWifi(ssidNow, wifiPassString);
						MySetTimer();

					} else {
						StyledDialog.buildIosAlert("提示", "请输入WiFi密码!", new MyDialogListener() {
							@Override
							public void onFirst() {
							}
							@Override
							public void onSecond() {
							}
						}).setBtnText("确定", "").show();
					}
				} else {
					StyledDialog.buildIosAlert("提示", "请连接您的WiFi", new MyDialogListener() {
						@Override
						public void onFirst() {
						}

						@Override
						public void onSecond() {
						}
					}).setBtnText("确定", "").show();
				}
			} else if (type == 2) {
				StyledDialog.buildLoading("正在添加中···").show();
				String appid = ToolsGetAppId.getinitAppId(this);
				AddMachInePerserent buiddn = new AddMachInePerserent(this);
				buiddn.buind(appid,machineid);
				addSqliteDb();
			}
		}

		switch (v.getId()) {
			//返回键
			case R.id.image_back:
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
	/*
	* 该接口是添加设备后的接口回调
	* */
	@Override
	public void showAddReluset(Object object) {
		StyledDialog.dismissLoading();
		AddMachineBean addmachine = (AddMachineBean) object;
		String  status = addmachine.getStatus();
		if (status.equals("1")) {
			StyledDialog.buildIosAlert("提示", "恭喜您成功添加设备", new MyDialogListener() {
				@Override
				public void onFirst() {

				}

				@Override
				public void onSecond() {
				}
			}).setBtnText("确定", "").show();
		} else {

		}

	}

	/*
* 往数据库里面添加数据· 弱水三千
*
* */
	private void addSqliteDb() {
		ZDYDataModel.getInstance().addDefault(machineid);
//        mList = ZDYDataModel.getInstance().getAlermList(1, machineid);
//        NZYDataModel.getInstance().addDefault(mList, machineid);
	}

}
