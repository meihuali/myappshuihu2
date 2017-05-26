package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.NzAdpter;
import com.example.xiao.myappshuihu.connerler.ConnHandler;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.httpsocket.HttpConnectionUtils;
import com.example.xiao.myappshuihu.httpsocket.HttpHandler;
import com.example.xiao.myappshuihu.httpsocket.HttpUrl;
import com.example.xiao.myappshuihu.json.JsonParser;
import com.example.xiao.myappshuihu.sqlite.DBhelperManager;
import com.example.xiao.myappshuihu.sqlite.NZ_DBhelperManager;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.MachineStateData;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.utils.Utils;
import com.example.xiao.myappshuihu.view.ListViewCompat;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/20 0020.
 * 定时预约 界面
 */

public class YuYueActivity extends Base2Activity implements PromptDialog.DialogListener {

    private ListViewCompat listview;

    private NzAdpter adpter;

    private List<NZ_DBhelperManager.NZYData> mList;

    private String MACHINEID;
    private String APPid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue);
        getOrdeList();
    }

    @Override
    public void setActionBar() {

        setActivityTitle(this.getString(R.string.alarm));
        /// 点击右上角的按钮跳转
        setRightImg(R.drawable.add, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YuYueActivity.this, NZEditActivity.class);
                intent.putExtra("machineid", MACHINEID);
                startActivity(intent);
            }
        });
    }

    @Override
    public void findView() {
        listview = (ListViewCompat) findViewById(R.id.girdview_id);
        findViewById(R.id.flowlayout).setVisibility(View.GONE);
    }

    @Override
    public void initView() {

    }


    public void getOrdeList() {
//        if (!ConnHandler.isConn)
//            return;
//        MACHINEID = getIntent().getStringExtra("MACHINEID");
        //直接从 搜索到的 那个adapter 界面 取出来  的 设备号 id
        MACHINEID = ShareUtils.getString(getApplicationContext(),"micID","");
        HashMap<String, Object> map = new HashMap<String, Object>();
         APPid = ToolsGetAppId.getinitAppId(this);
        map.put("appid", APPid);
        map.put("machineid", MACHINEID);
        map.put("page", 1);
        map.put("pagesize", 20);
        HttpConnectionUtils httpUtil = new HttpConnectionUtils(jrhandler);
        httpUtil.create(0, Utils.getUrl(map, HttpUrl.getorderlist), null);
        httpUtil.setState(110);
    }

    @Override
    protected void onStart() {
        // TODO 自动生成的方法存根
        super.onStart();
        mList = NZ_DBhelperManager.getInstance(this).getAlermList(MACHINEID);
        Log.e("aa", "mList:------------" + mList.size());
        if (mList != null) {
            adpter = new NzAdpter(this, mList, jrhandler, MACHINEID);
            adpter.setHandler(handler);
            listview.setAdapter(adpter);
            listview.setOnItemClickListener(adpter);
        }

    }

    NZ_DBhelperManager.NZYData data = null;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 11) {
                    data = (NZ_DBhelperManager.NZYData) msg.obj;
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_set_confirm), YuYueActivity.this, 100, false);
            } else if (msg.what == 12) {
                    data = (NZ_DBhelperManager.NZYData) msg.obj;
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_delete_confirm), YuYueActivity.this, 101, false);

            }
        }

        ;
    };

    @Override
    protected void onResume() {
        // TODO 自动生成的方法存根
        super.onResume();

    }

    @Override
    protected void onRestart() {
        // TODO 自动生成的方法存根
        super.onRestart();

    }

    private HttpHandler jrhandler = new HttpHandler(this) {
        /*请求成功*/
        protected void succeed(String jObject, int state) {
            super.succeed(jObject, state);
            L.e("cdddddd   "+jObject);

            if (state == 100) {//预约
                showPromptDialog(YuYueActivity.this.getString(R.string.alarm_set_success));
                data.NZ_ISOPEN = 1;

                NZ_DBhelperManager.getInstance(YuYueActivity.this).update(data);
                adpter.notifyDataSetChanged();
                String NZ_ID = data.NZ_ID;
                try {
                    JSONObject object = new JSONObject(jObject);
                    JSONObject data1 = object.optJSONObject("data");
                    String order_id = data1.getString("orderid");
                    // Toast.makeText(NzActivity.this, order_id, 1).show();
                    data.ORDER_ID = order_id;
                    NZ_DBhelperManager.getInstance(YuYueActivity.this)
                            .updateOrederId(order_id, NZ_ID);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Toast.makeText(NzActivity.this, jObject, 1).show();
            } else if (state == 101) {//取消预约

                String status = "";
                try {
                    JSONObject object = new JSONObject(jObject);
                    status = object.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    showPromptDialog(YuYueActivity.this.getString(R.string.heating_cancel_failed));
                } else {
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_delete_success));
                    data.NZ_ISOPEN = 0;
                    NZ_DBhelperManager.getInstance(YuYueActivity.this)
                            .update(data);
                    adpter.notifyDataSetChanged();
                }

            } else if (state == 102) {//删除预约，删除前记得取消
                // MachineStateData stateData = JsonParser.getInstance()
                // .revertJsonToObj(jObject, MachineStateData.class);
                // if (!stateData.getData().getState().equals("0")) {
                // showPromptDialog("服务器返回数据显示取消加热失败,赶紧去拔了电源吧");
                // }
                String status = "";
                try {
                    JSONObject object = new JSONObject(jObject);
                    status = object.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    showPromptDialog(YuYueActivity.this.getString(R.string.heating_cancel_failed));
                } else {
                    Toasts.makeTexts(getApplicationContext(),"成功");
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_delete_success));
                    NZ_DBhelperManager.getInstance(YuYueActivity.this)
                            .delete(data);
                    adpter.notifyDataSetChanged();
                }
            } else if (state == 103) {//更新预约，删除前记得取消
      /*           MachineStateData stateData = JsonParser.getInstance()
                        .revertJsonToObj(jObject, MachineStateData.class);
                if (!stateData.getData().getState().equals("0")) {
                    showPromptDialog("服务器返回数据显示取消加热失败,赶紧去拔了电源吧");
                }*/
                String status = "";
                try {
                    JSONObject object = new JSONObject(jObject);
                    status = object.getString("status");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if ("0".equals(status)) {
                    showPromptDialog(YuYueActivity.this.getString(R.string.heating_cancel_failed));
                } else {
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_update_success));
                    data.NZ_ISOPEN = 0;
                    NZ_DBhelperManager.getInstance(YuYueActivity.this)
                            .update(data);
                    adpter.notifyDataSetChanged();
                }
            }
        }

        ;
            /*请求失败*/
        protected void failed(String jObject, int state) {
            super.failed(jObject, state);
            L.e("cdddddd  失败 "+jObject);
            if (state == 101) {
                showPromptDialog(YuYueActivity.this.getString(R.string.heating_cancel_failed));
            } else if (state == 100) {//有情况是预约还没有取消，导致无法清除预约，这时候直接设置为已经开始？
                if (jObject.toString().contains("ordered")) {
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_set_failed) + YuYueActivity.this.getString(R.string.alarm_set_failed_reason));
                } else {
                    showPromptDialog(YuYueActivity.this.getString(R.string.alarm_set_failed) + jObject.toString());
                }

            }
            mList = NZ_DBhelperManager.getInstance(YuYueActivity.this)
                    .getAlermList(MACHINEID);
            adpter.notifyDataSetChanged();
        }
    };

    List<NameValuePair> jr_list = new ArrayList<NameValuePair>();

    @Override
    public void doComfirm(int flag) {
        // 预约
        jr_list.clear();
        if (flag == 100) {

            String[] timeStrings = data.NZ_TIME.split(":");
            String resultString = "";
            L.e("resultString   预约成功" +resultString);
            for (String tempString : timeStrings) {
                resultString += tempString.length() == 1 ? "0" + tempString : tempString;
            }
            DBhelperManager.ZDYData zdydata = DBhelperManager.getInstance(this).inquiry(
                    data.NZ_ZDYID);
            if (zdydata == null) {
                showPromptDialog(YuYueActivity.this.getString(R.string.no_mode));

            } else {
                jr_list.add(new BasicNameValuePair("appid", APPid));
                jr_list.add(new BasicNameValuePair("machineid", MACHINEID));
                jr_list.add(new BasicNameValuePair("heattime", resultString + "00"));
                int times = Integer.parseInt(zdydata.ZDY_TIME) * 60
                        + Integer.parseInt(zdydata.ZDY_TIME1) * 60 + 360;
                jr_list.add(new BasicNameValuePair("costtime", times + ""));
                String sw = zdydata.ZDY_SW.replace("°c", "");
                jr_list.add(new BasicNameValuePair("temp", sw + "C"));
                jr_list.add(new BasicNameValuePair("purify", zdydata.ZDY_TIME));
                jr_list.add(new BasicNameValuePair("keepwarm", zdydata.ZDY_TIME1));
                jr_list.add(new BasicNameValuePair("boil", zdydata.ZDY_ISZF));
                jr_list.add(new BasicNameValuePair("week", data.NZ_SW));
                HttpConnectionUtils httpUtil = new HttpConnectionUtils(jrhandler);
                httpUtil.create(1, HttpUrl.HEAT, jr_list);
                httpUtil.setState(100);
            }

        } // 取消预约
        else if (flag == 101) {
            if (TextUtils.isEmpty(data.ORDER_ID)) {
                showPromptDialog(YuYueActivity.this.getString(R.string.no_nz_id));
            } else {
                jr_list.add(new BasicNameValuePair("appid", APPid));
                jr_list.add(new BasicNameValuePair("machineid", MACHINEID));
                jr_list.add(new BasicNameValuePair("orderid", data.ORDER_ID));
                // Toast.makeText(NzActivity.this, data.ORDER_ID, 1).show();
                HttpConnectionUtils httpUtil = new HttpConnectionUtils(
                        jrhandler);
                httpUtil.create(1, HttpUrl.CANCELHEAT, jr_list);
                httpUtil.setState(101);
            }

        }
    }

    @Override
    public void doCancel(int flag) {
        // TODO 自动生成的方法存根

    }

    @Override
    public void doBack(int flag) {
        // TODO 自动生成的方法存根
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 111) {
            jr_list.add(new BasicNameValuePair("appid", APPid));
            jr_list.add(new BasicNameValuePair("machineid", MACHINEID));
            jr_list.add(new BasicNameValuePair("orderid", mList.get(adpter.onClickIte).ORDER_ID));
            // Toast.makeText(NzActivity.this, data.ORDER_ID, 1).show();
            HttpConnectionUtils httpUtil = new HttpConnectionUtils(
                    jrhandler);
            httpUtil.create(1, HttpUrl.CANCELHEAT, jr_list);
            httpUtil.setState(103);//更新预约，和删除预约的功能一样，稍微修改一下提示而已
            Toasts.makeTexts(getApplicationContext(),"修改预约成功");
            //修改预约成功后 这里 要从服务器删除 修改之前预约的那条数据
            deleteYuYue();
        }

    }
    /*删除上一条的预约记录 */
    private void deleteYuYue() {
        String orderid  = mList.get(adpter.onClickIte).ORDER_ID;
        String urlssses = ConfigUtils.SHUIHU_ZONG_JIEKOU+ConfigUtils.DETATE_YUYUE+"appid/"+APPid+"/machineid/"+MACHINEID+"/orderid/"+orderid;
        new RxVolley.Builder().callback(new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                L.e("delte  删除 "+t);
            }
        })
                .url(urlssses) //接口地址
                //设置缓存时间: 默认是 get 请求 5 分钟, post 请求不缓存
                .cacheTime(0)
                //内容参数传递形式，如果不加，默认为 FORM 表单提交，可选项 JSON 内容
                .contentType(RxVolley.ContentType.FORM)
                //是否缓存，默认是 get 请求 5 缓存分钟, post 请求不缓存
                .shouldCache(false)
                .encoding("UTF-8") //编码格式，默认为utf-8
                .doTask();  //执行请求操作
    }


}
