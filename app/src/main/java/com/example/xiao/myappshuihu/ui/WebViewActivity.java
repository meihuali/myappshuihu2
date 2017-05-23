package com.example.xiao.myappshuihu.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.dialog.PopupWindowBottonShoping;
import com.example.xiao.myappshuihu.dialog.PopupWindowUtils;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.ui
 * 文件名：WebViewActivity
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 下午 6:15
 * 描述：TODO
 */
public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private String url;
    private ImageView back;
    private RelativeLayout llay_popuowindow;
    private Button btn_goumai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initView();

    }
    /*初始化控件*/
    private void initView() {
     /*   llay_popuowindow = (RelativeLayout) findViewById(R.id.llay_popuowindow);
        llay_popuowindow.setOnClickListener(this);*/
        btn_goumai = (Button) findViewById(R.id.btn_goumai);
        btn_goumai.setOnClickListener(this);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);
        mWebView=(WebView) findViewById(R.id.webview1);
        mProgressBar=(ProgressBar) findViewById(R.id.progressBar1);

        //进行加载网页的逻辑
        url = "http://h5.m.taobao.com/awp/core/detail.htm?id=531287098468";
        //支持JS
        mWebView.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        //接口回调
        mWebView.setWebChromeClient(new WebViewClient());
        //加载网页
        mWebView.loadUrl(url);



        //本地显示
        mWebView.setWebViewClient(new android.webkit.WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                //我接受这个事件
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_goumai:
                PopupWindowBottonShoping pwu = new PopupWindowBottonShoping(this);
                pwu.showPopupWindow();
            break;
        }
    }

    public class WebViewClient extends WebChromeClient{

        //进度变化的监听
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);

            } else {
                mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                mProgressBar.setProgress(newProgress);//设置进度值
                /*这面这一长串是用来·搞定 webview 上的那些按钮 或者 广告条 注入掉 */
                mWebView.evaluateJavascript("document.documentElement.getElementsByClassName('_3HtOnURvSgW7cBAWmbmyMb _13RGBxKBfX2TEKXjP3I0Uf')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('sys_button cart')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('bottom_bar_fav')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('bottom_bar_icon')[1].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('bottom_bar_icon')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('navi_bar homePage')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('rate_header')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('shop_header')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('shop_info')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('button_container')[1].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('ctrl-ui-labelExtra')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('footer')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('body')[6].style.display = 'none';\n" +
                        "document.getElementById('e42964a')[0].style.display = 'none';\n" +
                        "document.documentElement.getElementsByClassName('d-titletab')[0].style.display = 'none';"  ,new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String s) {

                    }
                });
            }
            super.onProgressChanged(view, newProgress);
        }

    }


    //设置返回键动作（防止按返回键直接退出程序)
/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(mWebView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                mWebView.goBack();
                return true;
            }
            else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }


        }
        return super.onKeyDown(keyCode, event);
    }*/


}
