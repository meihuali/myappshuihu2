package com.example.xiao.myappshuihu.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.dialog.PromptDialog;
import com.example.xiao.myappshuihu.utils.HandlerConstant;
import com.example.xiao.myappshuihu.utils.ToastUtil;

/**
 * Created by wx091 on 2016/4/15.
 */
public abstract class Base2Activity extends AppCompatActivity {
    protected String TAG = this.getClass().getSimpleName();
    private LinearLayout contentView = null;
    protected Context mContext = this;
    private Toolbar mToolbar;
    private TextView toolbarTitle;
    private TextView amRightTv;
    private Toolbar.OnMenuItemClickListener onMenuItemClickListener;
    private int menuRes = INVALID_MENU;
    private static final int INVALID_MENU = -1;
    public RequestQueue volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_base);
        volley= Volley.newRequestQueue(getApplicationContext());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        if (contentView == null && R.layout.activity_base == layoutResID) {
            super.setContentView(R.layout.activity_base);
            contentView = (LinearLayout) findViewById(R.id.layout_center);
            toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
            amRightTv = (TextView) findViewById(R.id.am_right_tv);
            contentView.removeAllViews();

        } else if (layoutResID != R.layout.activity_base) {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            contentView.addView(addView, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            findView();
            initView();

            //不要改变下面三者的顺序
            beforeSetActionBar();
            setActionBar();
            afterSettingActionBar();

            //isNZ();
        }
    }

    public void beforeSetActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.backbtn);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
    }

    private void afterSettingActionBar() {
        //setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            //隐藏标题栏
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public abstract void setActionBar();

    public abstract void findView();

    public abstract void initView();

    public void setMenuId(@MenuRes int menuRes) {
        this.menuRes = menuRes;
    }

    public void setMenu(@MenuRes int menuRes, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.menuRes = menuRes;
        setMenuClickListener(onMenuItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuRes != INVALID_MENU) {
            getMenuInflater().inflate(menuRes, menu);
        }
        return true;
    }

    public void setLeftImg(@DrawableRes int imgId) {
        mToolbar.setNavigationIcon(imgId);
    }

    public void setActivityTitle(String text) {
        toolbarTitle.setText(text);
    }

    public void setActivityTitle(@StringRes int textId) {
        toolbarTitle.setText(textId);
    }

    public void setRightText(String text) {
        amRightTv.setText(text);
    }

    public void setRight(String text, View.OnClickListener listener) {
        amRightTv.setText(text);
        amRightTv.setOnClickListener(listener);
    }

    public void setRight(@StringRes int textId) {
        amRightTv.setText(textId);
    }

    public void setRightImg(@DrawableRes int imgId) {
        amRightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, imgId, 0);
    }
    public void setRightImg(@DrawableRes int imgId, View.OnClickListener listener) {
        amRightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, imgId, 0);
        amRightTv.setOnClickListener(listener);
    }

    public void setMenuClickListener(Toolbar.OnMenuItemClickListener clickListener) {
        this.onMenuItemClickListener = clickListener;
    }

    public void setOnNavigationClickListener(View.OnClickListener onNavigationClickListener) {
        mToolbar.setNavigationOnClickListener(onNavigationClickListener);
    }

    protected void show(String content){
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 确定对话框 没有回调
     *
     * @param showStr
     * @param
     */
    public void showPromptDialog(String showStr) {
        PromptDialog.show(this, showStr, null);
    }

    /**
     * 确定对话框 有回调
     *
     * @param showStr
     * @param dialogListener
     */
    public void showPromptDialog(final String showStr,
                                 final PromptDialog.DialogListener dialogListener, final int flag) {
        PromptDialog.show(this, showStr, dialogListener, flag);
    }

    public void showPromptDialog(final String showStr,
                                 final PromptDialog.DialogListener dialogListener, final int flag, final boolean b) {
        PromptDialog.show(this, showStr, dialogListener, flag,b);
    }
    protected void isNZ(){
//        List<NZ_DBhelperManager.NZYData> mList = NZ_DBhelperManager.getInstance(this).getAlermList();
//        for(int i = 0 ; i < mList.size() ; i++){
//            NZ_DBhelperManager.NZYData nzData = mList.get(i);
//            ImageView img = (ImageView)findViewById(R.id.is_nz);
//            if(nzData.NZ_ISOPEN == 1){
//                img.setVisibility(View.VISIBLE);
//                break;
//            }else if(i==mList.size()-1){
//                img.setVisibility(View.GONE);
//            }
//        }
    }
    private Handler handler = new Handler() {

    };
    public Handler responseHandler=new Handler(){
        public void handleMessage(android.os.Message msg) {//做这个handler因为listview里面的例如关闭、删除等操作，还需要往服务器发送数据
            if (msg.what == HandlerConstant.response_false) {//关闭预约adapter中关闭
                ToastUtil.Short(Base2Activity.this,(String)msg.obj);
            } else if (msg.what == HandlerConstant.JsonSyntaxException) {//打开预约 adapter中开启
                ToastUtil.Short(Base2Activity.this,"JsonSyntaxException:"+(String)msg.obj);
            }else if (msg.what == HandlerConstant.JSONException) {//打开预约 adapter中开启
                ToastUtil.Short(Base2Activity.this,"JSONException:"+(String)msg.obj);
            }else if (msg.what == HandlerConstant.UnsupportedEncodingException) {//打开预约 adapter中开启
                ToastUtil.Short(Base2Activity.this,"UnsupportedEncodingException:"+(String)msg.obj);
            }
        };
    };
}
