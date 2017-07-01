package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.dialog.EditDialog;
import com.example.xiao.myappshuihu.dialog.ListDialog;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.entity.Kettle;
import com.example.xiao.myappshuihu.entity.Machine;
import com.example.xiao.myappshuihu.sqlite.DBNullException;
import com.example.xiao.myappshuihu.sqlite.DBhelperManager;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.view.RotateDrawView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.  ZDYData
 */

public class ZiDingYiBianJiActivity extends Base2Activity implements View.OnClickListener {
    // private SeekBar seeBar;
    // /******温度滚轮******/
    // private ProgressView progressView;
    //
    // private TextView edit_txt_01;
    private Kettle k;
    private RotateDrawView drawView;

    private RelativeLayout edit_layout;

    private CheckBox Switch1, Switch2, Switch3;

    private RelativeLayout zdy_layout_01_id, zdy_layout_02_id,
            zdy_layout_03_id;

    private TextView zdy_txt_layout_02, zdy_txt_layout_03, zdy_txt_layout_04,
            zdy_txt_layout_05, zdy_txt_layout21, layout_01;
    List<HashMap<String, Object>> mDataList = new ArrayList<HashMap<String, Object>>();
    private String mciID;
    /******
     * ‘ 添加或者修改自定义的数据
     * ******/
    public DBhelperManager.ZDYData data = new DBhelperManager.ZDYData();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_zdy);

        // edit_txt_01 = (TextView)findViewById(R.id.edit_txt_01);
        // progressView = (ProgressView)findViewById(R.id.arcProgressbar1);
        // seeBar = (SeekBar) findViewById(R.id.seekBar1);

        setindex(0);
        getIntentE();
    }

    @Override
    public void setActionBar() {
        //取出设备号
         mciID = ShareUtils.getString(getApplicationContext(),"micID","");

        setActivityTitle(R.string.customsetting);
        setRight(this.getString(R.string.save), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPromptDialog(ZiDingYiBianJiActivity.this.getString(R.string.save_success), new PromptDialog.DialogListener() {
                    @Override
                    public void doComfirm(int flag) {
                        // TODO 自动生成的方法存根
                        try {
                            if(mciID==null){
//                                data.MACHINE_ID=k.machineid;
                                Toasts.makeTexts(getApplicationContext(),"设备号为空");
                            }
                            data.ZDY_ISDE = 0;
                            // ========注释
                            data.ZDY_SW = drawView.getTemp() + "°c";
                            data.MACHINE_ID = mciID;
//                            //这里保存·有没有？
                            data.ZDY_IMAGE = String.valueOf(R.drawable.i5);
                            data.ZDY_ISOPEN = 1;
                            if (Switch2.isChecked()) {
                                data.ZDY_TIME =getIntString(zdy_txt_layout_03.getText().toString()) ;
                            } else {
                                data.ZDY_TIME = "0";
                            }
                            if (Switch3.isChecked()) {
                                data.ZDY_TIME1 =getIntString( zdy_txt_layout_04.getText().toString());

                            } else {
                                data.ZDY_TIME1 = "0";
                            }
                            if (data.ZDY_ID == null) {
                                data.ZDY_ID = System.currentTimeMillis() + "";

                                DBhelperManager.getInstance(ZiDingYiBianJiActivity.this).insert(data);

                            } else {
                                DBhelperManager.getInstance(ZiDingYiBianJiActivity.this)
                                        .update(data);
                            }

                        } catch (DBNullException e) {
                            // TODO 自动生成的 catch 块
                            e.printStackTrace();
                        }
                        finish();
                    }

                    @Override
                    public void doCancel(int flag) {
                        // TODO 自动生成的方法存根

                    }

                    @Override
                    public void doBack(int flag) {
                        // TODO 自动生成的方法存根

                    }

                }, 100);

            }

        });

    }

    @Override
    public void findView() {
        drawView = (RotateDrawView) findViewById(R.id.rotatedrawview);

        edit_layout = (RelativeLayout) findViewById(R.id.zdy_edit_layout_id);
        edit_layout.setOnClickListener(this);

        Switch1 = (CheckBox) findViewById(R.id.Switch1);
        Switch2 = (CheckBox) findViewById(R.id.Switch2);
        Switch3 = (CheckBox) findViewById(R.id.Switch3);

        zdy_layout_01_id = (RelativeLayout) findViewById(R.id.zdy_layout_01_id);
        zdy_layout_02_id = (RelativeLayout) findViewById(R.id.zdy_layout_02_id);
        zdy_layout_03_id = (RelativeLayout) findViewById(R.id.zdy_layout_03_id);
        zdy_layout_01_id.setOnClickListener(this);
        zdy_layout_02_id.setOnClickListener(this);
        zdy_layout_03_id.setOnClickListener(this);

        zdy_txt_layout_02 = (TextView) findViewById(R.id.zdy_txt_layout_02);
        zdy_txt_layout_03 = (TextView) findViewById(R.id.zdy_txt_layout_03);
        zdy_txt_layout_04 = (TextView) findViewById(R.id.zdy_txt_layout_04);
        zdy_txt_layout_05 = (TextView) findViewById(R.id.zdy_txt_layout_05);
        zdy_txt_layout21 = (TextView) findViewById(R.id.zdy_txt_layout_21);
        layout_01 = (TextView) findViewById(R.id.layout_01);
    }

    @Override
    public void initView() {
        zdy_txt_layout_03.setOnClickListener(this);
        zdy_txt_layout_04.setOnClickListener(this);
        layout_01.setOnClickListener(this);
        zdy_txt_layout_02.setOnClickListener(this);

        Switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO 自动生成的方法存根
                if (!isChecked) {
                    Switch2.setChecked(false);
                    data.ZDY_ISZF = "0";
                    Switch2.setChecked(false);

                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.purify_length));
                    stringBuffer.append(" 0 ");
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.min));
                    startTime = 0;
                    zdy_txt_layout_03.setText(stringBuffer.toString());
                    data.ZDY_TIME = "0";
                    zdy_txt_layout21.setTextColor(getResources().getColor(
                            R.color.gray));
                    zdy_txt_layout_03.setTextColor(getResources().getColor(
                            R.color.gray));
                    zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time)+" "
                            + (startTime + endTime) +" "+ ZiDingYiBianJiActivity.this.getString(R.string.min));
                } else {
                    zdy_txt_layout21.setTextColor(getResources().getColor(
                            R.color.black));
                    data.ZDY_ISZF = "1";
                }
            }
        });

        Switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO 自动生成的方法存根
                if (!isChecked) {
                    data.ZDY_TIME = "0";
                    zdy_txt_layout_03.setTextColor(getResources().getColor(
                            R.color.gray));
                    startTime = 1;
                    zdy_txt_layout_03.setText(ZiDingYiBianJiActivity.this.getString(R.string.purify_length)+" "+data.ZDY_TIME+" "+ZiDingYiBianJiActivity.this.getString(R.string.min));
                } else {

                    if(!Switch1.isChecked()){
                        Switch2.setChecked(false);
                    }else{
//						if(data.ZDY_TIME.equals("0")){
//							data.ZDY_TIME = "1";
//						}
                        data.ZDY_TIME = "1";
                        zdy_txt_layout_03.setTextColor(getResources().getColor(
                                R.color.black));
                        zdy_txt_layout_03.setText(ZiDingYiBianJiActivity.this.getString(R.string.purify_length) +" " + data.ZDY_TIME + " " +ZiDingYiBianJiActivity.this.getString(R.string.min));
                    }
                }
                zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time)+" " + (startTime + endTime)+" "
                        + ZiDingYiBianJiActivity.this.getString(R.string.min));
            }
        });

        Switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO 自动生成的方法存根
                if (!isChecked) {
                    data.ZDY_TIME1 = "0";
                    zdy_txt_layout_04.setTextColor(getResources().getColor(
                            R.color.gray));
                    endTime = 1;
                    zdy_txt_layout_04.setText(ZiDingYiBianJiActivity.this.getString(R.string.insulation_length)+" " +data.ZDY_TIME1+" " + ZiDingYiBianJiActivity.this.getString(R.string.min));
                } else {
                    data.ZDY_TIME1 = "1";
                    zdy_txt_layout_04.setTextColor(getResources().getColor(
                            R.color.black));
                    zdy_txt_layout_04.setText(ZiDingYiBianJiActivity.this.getString(R.string.insulation_length) +" " + data.ZDY_TIME1 +" " + ZiDingYiBianJiActivity.this.getString(R.string.min));

                }
                zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time) +" " +(startTime + endTime)+" "
                        + ZiDingYiBianJiActivity.this.getString(R.string.min));
            }
        });
    }

    private void setindex(int state) {
        if (state == 0) {
            zdy_txt_layout21
                    .setTextColor(getResources().getColor(R.color.gray));
            zdy_txt_layout_03.setTextColor(getResources()
                    .getColor(R.color.gray));
            zdy_txt_layout_04.setTextColor(getResources()
                    .getColor(R.color.gray));
        } else if (state == 1) {
            zdy_txt_layout21.setTextColor(getResources()
                    .getColor(R.color.black));
            zdy_txt_layout_03.setTextColor(getResources()
                    .getColor(R.color.gray));
            zdy_txt_layout_04.setTextColor(getResources()
                    .getColor(R.color.gray));
        } else if (state == 2) {
            zdy_txt_layout21.setTextColor(getResources()
                    .getColor(R.color.black));
            zdy_txt_layout_03.setTextColor(getResources().getColor(
                    R.color.black));
            zdy_txt_layout_04.setTextColor(getResources()
                    .getColor(R.color.gray));
        } else if (state == 3) {
            zdy_txt_layout21.setTextColor(getResources()
                    .getColor(R.color.black));
            zdy_txt_layout_03.setTextColor(getResources().getColor(
                    R.color.black));
            zdy_txt_layout_04.setTextColor(getResources().getColor(
                    R.color.black));
        } else if (state == 3) {
            zdy_txt_layout21.setTextColor(getResources()
                    .getColor(R.color.black));
            zdy_txt_layout_03.setTextColor(getResources()
                    .getColor(R.color.gray));
            zdy_txt_layout_04.setTextColor(getResources().getColor(
                    R.color.black));
        }

    }

    private void getIntentE() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            data = (DBhelperManager.ZDYData) intent.getSerializableExtra("data");
//            k=(Kettle) intent.getSerializableExtra("machine");



            if(data==null){
                data=new DBhelperManager.ZDYData();
            }
            zdy_txt_layout_02.setText(data.ZDY_NAME);
            if (data.ZDY_ISZF != null && !data.ZDY_ISZF.equals("0")) {
                Switch1.setChecked(true);
            } else {
                //Switch2.setChecked(false);
            }
            if (!data.ZDY_TIME.equals("0")) {
                startTime = Integer.parseInt(data.ZDY_TIME);
                Switch2.setChecked(true);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.purify_length));
                stringBuffer.append(" "+startTime+" ");
                if(startTime>1){
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.mins));
                }else {
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.min));
                }
                zdy_txt_layout_03.setText(stringBuffer.toString());
                setindex(2);
            }
            if (!data.ZDY_TIME1.equals("0")) {
                endTime = Integer.parseInt(data.ZDY_TIME1);
                Switch3.setChecked(true);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.insulation));
                stringBuffer.append(" "+endTime+" ");
                if(endTime>1){
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.mins));
                }else {
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.min));
                }
                setindex(4);
                zdy_txt_layout_04.setText(stringBuffer.toString());
            }

            zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time) +" "+ (startTime + endTime)+" "
                    + ZiDingYiBianJiActivity.this.getString(R.string.min));

            data.ZDY_SW = data.ZDY_SW.replaceAll("°c", "");
            // =====注释
            drawView.setTemp(Integer.parseInt(data.ZDY_SW));
            // edit_txt_01.setText(data.ZDY_SW+"°c");

            int progress = Integer.parseInt(data.ZDY_SW);

            // seeBar.setProgress(progress);
            // progressView.setProgress(progress);
            index = 0;
        } else {
            index = 1;
            Switch2.setChecked(false);
        }
    }

    @Override
    protected void onStart() {
        // TODO 自动生成的方法存根
        super.onStart();

    }

    private int index;

    private void initData(int length) {
        mDataList.clear();
        mDataList = new ArrayList<HashMap<String, Object>>();
        for (int i = 1; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("txt", i);
            mDataList.add(map);
        }

    }





    @Override
    public void onClick(View v) {
        // TODO 自动生成的方法存根
        if (v.getId() == R.id.zdy_edit_layout_id
                || v.getId() == R.id.layout_01
                || v.getId() == R.id.zdy_txt_layout_02) { // 输入框按钮操作
            EditDialog dialog = new EditDialog(this, zdy_txt_layout_02
                    .getText().toString());
            dialog.setHandler(handler);
            dialog.show();
        } else if (v.getId() == R.id.zdy_layout_02_id
                || v.getId() == R.id.zdy_txt_layout_03) { // 净化时间选择
            if (Switch2.isChecked()) {
                initData(6);
                ListDialog dialog = new ListDialog(this, handler);
                dialog.setListData(mDataList, 100);
                dialog.show();
            }
        } else if (v.getId() == R.id.zdy_layout_03_id
                || v.getId() == R.id.zdy_txt_layout_04) {// 保温时间选择
            if (Switch3.isChecked()) {
                initData(60);
                ListDialog dialog = new ListDialog(this, handler);
                dialog.setListData(mDataList, 101);
                dialog.show();
            }
        }
    }

    private int startTime, endTime;

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO 自动生成的方法存根
            super.handleMessage(msg);
            if (msg.what == 100) {
                startTime = Integer.parseInt(msg.obj.toString());
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.purify));
                stringBuffer.append(" "+startTime+" ");
                if(startTime>1){
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.mins));
                }else {
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.min));
                }

                zdy_txt_layout_03.setText(stringBuffer.toString());

                zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time)+" "+ (startTime + endTime)+" "
                        + ZiDingYiBianJiActivity.this.getString(R.string.min));
                data.ZDY_TIME = msg.obj.toString();
            } else if (msg.what == 101) {
                endTime = Integer.parseInt(msg.obj.toString());
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.insulation));
                stringBuffer.append(" "+endTime+" ");
                if(endTime>1){
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.mins));
                }else {
                    stringBuffer.append(ZiDingYiBianJiActivity.this.getString(R.string.min));
                }
                zdy_txt_layout_04.setText(stringBuffer.toString());
                zdy_txt_layout_05.setText(ZiDingYiBianJiActivity.this.getString(R.string.caculate_time) +" "+ (startTime + endTime)+" "
                        + ZiDingYiBianJiActivity.this.getString(R.string.min));

                data.ZDY_TIME1 = msg.obj.toString();
            } else if (msg.what == 99) {
                data.ZDY_NAME = msg.obj.toString();
                zdy_txt_layout_02.setText(data.ZDY_NAME);
            }
        }
    };

    private String getIntString(String str){
        String s="";
        for(int i=0;i<str.length();i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                s += str.charAt(i);
            }
        }
        return s;
    }

}
