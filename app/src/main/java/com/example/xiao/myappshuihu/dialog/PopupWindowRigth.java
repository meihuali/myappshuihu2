package com.example.xiao.myappshuihu.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.ui.AddOldInfoActivity;

import com.example.xiao.myappshuihu.ui.LiShiJiLuAcativity;
import com.example.xiao.myappshuihu.ui.SaoYiSaoActivity;
import com.example.xiao.myappshuihu.ui.SettingsActivity;
import com.example.xiao.myappshuihu.ui.YuYueActivity;
import com.example.xiao.myappshuihu.utils.Toasts;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class PopupWindowRigth extends BasePopupWindow implements View.OnClickListener {
    private TextView tx1;
    private TextView tx2;
    private TextView tx3;

    public PopupWindowRigth(Activity context) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        findViewById(R.id.tx_1).setOnClickListener(this);
        findViewById(R.id.tx_2).setOnClickListener(this);
        findViewById(R.id.tx_3).setOnClickListener(this);
        findViewById(R.id.tx_4).setOnClickListener(this);
        findViewById(R.id.tx_5).setOnClickListener(this);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_menu);
    }

    @Override
    public View initAnimaView() {
        return null;
    }
    /*这个方法是 popupwindow 显示在具体 什么位子 ·这里是显示右上角*/
    @Override
    public void showPopupWindow(View v) {
        setOffsetX(-(getPopupViewWidth() - v.getWidth() / 2));
       int h = v.getHeight();
        setOffsetY(v.getHeight()+18);
        super.showPopupWindow(v);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tx_1:
                Toasts.makeTexts(getContext(), "click tx_1");
                /*跳转到设置*/
                getContext().startActivity(new Intent(getContext(),SettingsActivity.class));
                dismiss();
                break;
            case R.id.tx_2:
                Toasts.makeTexts(getContext(), "click tx_2");
                Intent intent = new Intent(getContext(),YuYueActivity.class);
                getContext().startActivity(intent);
                dismiss();
                break;
            case R.id.tx_3:
                Toasts.makeTexts(getContext(), "click tx_3");
                //启动一个activity 打开二维码扫描
                getContext().startActivity(new Intent(getContext(),SaoYiSaoActivity.class));
                break;
            case R.id.tx_4:
                // 历史记录
                Toasts.makeTexts(getContext(), "click tx_4");
                getContext().startActivity(new Intent(getContext(), LiShiJiLuAcativity.class));
                PopupWindowRigth.this.dismiss();
                break;
            case R.id.tx_5:
                // 添加设备
                Toasts.makeTexts(getContext(), "click tx_4");
                getContext().startActivity(new Intent(getContext(), AddOldInfoActivity.class));
                PopupWindowRigth.this.dismiss();
                break;
        }
    }


}
