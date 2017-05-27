package com.example.xiao.myappshuihu.dialog;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/5/27 0027.
 */

public class WeChatPayMentPopupWindow extends BasePopupWindow {
    public WeChatPayMentPopupWindow(Activity context) {
        super(context);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return null;
    }

    @Override
    public View initAnimaView() {
        return null;
    }
}
