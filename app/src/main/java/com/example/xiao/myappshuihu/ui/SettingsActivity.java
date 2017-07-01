package com.example.xiao.myappshuihu.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.greendao.gen.SettingsDataSqlBeanDao;
import com.example.anonymous.greendao.gen.SettingsDataSql_item1_BeanDao;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.MyAdapter;
import com.example.xiao.myappshuihu.adapter.ZdyAdpter;
import com.example.xiao.myappshuihu.entity.Kettle;
import com.example.xiao.myappshuihu.entity.SettingsBean;
import com.example.xiao.myappshuihu.entity.SettingsDataSqlBean;
import com.example.xiao.myappshuihu.entity.SettingsDataSql_item1_Bean;
import com.example.xiao.myappshuihu.sqlite.DBhelperManager;
import com.example.xiao.myappshuihu.sqlite.MyGreenDaoUtils;
import com.example.xiao.myappshuihu.sqlite.MyGreenDao_settings_Utils;
import com.example.xiao.myappshuihu.sqlite.MyGreenDao_settings_item_1_Utils;
import com.example.xiao.myappshuihu.sqlite.NZ_DBhelperManager;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.StringUtil;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.WifiTools;
import com.example.xiao.myappshuihu.view.ListViewCompat;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/5/15 0015.
 * //设置温度时间 等  界面
 */

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imgsss_back;
    private ListViewCompat listview;

    private ZdyAdpter adpter;
    private Kettle data;

    private List<DBhelperManager.ZDYData> mList;

    private FlowLayout flowLayout;
    private Button btn_addconmit;
    private String machineid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    /*初始化*/
    private void initView() {
        btn_addconmit = (Button) findViewById(R.id.btn_addconmit);
        btn_addconmit.setOnClickListener(this);
        imgsss_back = (ImageView) findViewById(R.id.imgsss_back);
        imgsss_back.setOnClickListener(this);
        listview = (ListViewCompat) findViewById(R.id.girdview_id);
        flowLayout=(FlowLayout)findViewById(R.id.flowlayout);
        //程序第一次进来·就得去取数据

    }





    private Handler hanlder = new Handler() {
        public void handleMessage(android.os.Message msg) {
            initTitle();
        };
    };
    @Override
    protected void onStart() {
        super.onStart();
        /*程序通过onsart 周期 进来·先 判断 先从数据库去取
        * 数据，取得时候给他一个水壶设备ID号 然后如果取出来是空的
        *  就直接 往里面默认添加 4条数据 例如煮沸 花茶 咖啡 等等之类的·
        *  设备号：  01020101411608666666
        * */
        machineid = ShareUtils.getString(getApplicationContext(),"micID","");
        L.e("进来的时候 "+machineid);
        if (StringUtil.isEmpty(machineid)) {
            machineid = "machineid";
        }
        mList = DBhelperManager.getInstance(this).getAlermList(1, machineid);
        if (mList.size() <= 0) {
            DBhelperManager.getInstance(this).addDefault(machineid);
            mList = DBhelperManager.getInstance(this).getAlermList(1, machineid);
            NZ_DBhelperManager.getInstance(this).addDefault(mList, machineid);
        }

        /*
        * 然后继续判断 是否有数据·如果·没有数据 ·如果有数据·就直接设置adapter 的
        * */
//        mList = DBhelperManager.getInstance(this).getAlermList(1,machineid);
        if (mList != null) {
            adpter = new ZdyAdpter(this, mList);
            adpter.setHandler(hanlder);
            listview.setAdapter(adpter);
            listview.setOnItemClickListener(adpter);
        }
        initTitle();
    }

    private void initTitle() {
        //这边的数据显示不出来了·
        flowLayout.removeAllViews();
        List<DBhelperManager.ZDYData> mList = DBhelperManager.getInstance(this).getAlermList(1,machineid);
        int length = mList.size();
        for (int i = 0; i < mList.size(); i++) {
            addToFlow(mList.get(i).ZDY_NAME);
        }
    }

    private void addToFlow(String s) {
        int ranHeight = WifiTools.dip2px(this, 30);
        ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
        lp.setMargins(WifiTools.dip2px(this, 10), 0, WifiTools.dip2px(this, 10), 0);
        TextView tv = new TextView(this);
        tv.setPadding(WifiTools.dip2px(this, 15), 0, WifiTools.dip2px(this, 15), 0);
        tv.setTextColor(Color.parseColor("#FF3030"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        int index = (int)(Math.random() * mList.size());
        tv.setText(s);
        tv.setGravity(Gravity.CENTER_VERTICAL);
//                tv.setSingleLine();
        tv.setBackgroundResource(R.drawable.bg_tag);
        flowLayout.addView(tv, lp);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgsss_back:
                finish();
                break;
            case R.id.btn_addconmit:
                Intent intent = new Intent(SettingsActivity.this, ZiDingYiBianJiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
