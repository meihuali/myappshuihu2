package com.example.xiao.myappshuihu.dialog;

import android.animation.Animator;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.dialog
 * 文件名：PopItemCenterWindow
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/29 0029 16:08
 * 描述：TODO
 */
public class PopItemCenterWindow extends BasePopupWindow implements View.OnClickListener {

    private Activity activity;


    public PopItemCenterWindow(Activity context) {
        super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        activity = context;
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        set.setInterpolator(new DecelerateInterpolator());
        set.addAnimation(getScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 1, Animation.RELATIVE_TO_SELF, 0));
        set.addAnimation(getDefaultAlphaAnimation());
        return set;
        //return null;
    }

    @Override
    public Animator initShowAnimator() {
       /* AnimatorSet set=new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(mAnimaView,"scaleX",0.0f,1.0f).setDuration(300),
                ObjectAnimator.ofFloat(mAnimaView,"scaleY",0.0f,1.0f).setDuration(300),
                ObjectAnimator.ofFloat(mAnimaView,"alpha",0.0f,1.0f).setDuration(300*3/2));*/
        return null;
    }

    @Override       /*显示位子*/
    public void showPopupWindow(View v) {
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        // x 拿到茶的坐标，拿到茶图片的宽高，x=屏幕宽度/2-popwidth/2，y=茶的y-pop高
        int x = width /2 - getPopupViewWidth()/2;
        setOffsetX(x);
        setOffsetY(-(v.getHeight()+180));
        super.showPopupWindow(v);
    }

    @Override
    public View getClickToDismissView() {
        return null;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popitemcenterwindow);
    }

    @Override
    public View initAnimaView() {
        return getPopupWindowView().findViewById(R.id.popup_contianer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;

        }

    }
}
