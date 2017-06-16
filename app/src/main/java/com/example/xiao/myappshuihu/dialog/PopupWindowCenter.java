package com.example.xiao.myappshuihu.dialog;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.xiao.myappshuihu.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class PopupWindowCenter extends BasePopupWindow implements View.OnClickListener{
    private View popupView;
    private Activity activity;
    private ImageView weixin;
    private ImageView zhifubao;

    public PopupWindowCenter(Activity activity) {
        super(activity);
        this.activity = activity;
        bindEvent();
    }



    @Override
    protected Animation initShowAnimation() {
        return getDefaultScaleAnimation();
    }


    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView= LayoutInflater.from(getContext()).inflate(R.layout.popup_normal,null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView!=null){
           weixin = (ImageView) popupView.findViewById(R.id.image_weixin_gouxuan);
            weixin.setOnClickListener(this);
            zhifubao = (ImageView) popupView.findViewById(R.id.image_zhifubao_weigouxuan);
            zhifubao.setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tx_1:
                break;
            case R.id.tx_2:
                break;
            case R.id.tx_3:

                break;
            //充值
            case R.id.tx_4:

                break;
            //点击微信勾选
            case R.id.image_weixin_gouxuan:
                weixin.setImageDrawable(activity.getDrawable(R.drawable.gouxuan));
                zhifubao.setImageDrawable(activity.getDrawable(R.drawable.weigouxuan));
                break;
            case R.id.image_zhifubao_weigouxuan:
                zhifubao.setImageDrawable(activity.getDrawable(R.drawable.gouxuan));
                weixin.setImageDrawable(activity.getDrawable(R.drawable.weigouxuan));
                break;
            default:
                break;
        }

    }
}