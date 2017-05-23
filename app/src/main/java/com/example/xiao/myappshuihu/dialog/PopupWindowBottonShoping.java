package com.example.xiao.myappshuihu.dialog;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anonymous.greendao.gen.ShangChenLiBiaoBeanDao;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.ui.ShoppingsActivity;

import razerdp.basepopup.BasePopupWindow;


public class PopupWindowBottonShoping extends BasePopupWindow implements View.OnClickListener {
    private View popupView; // 声明view
    boolean isActive;
    private ImageView image_cimas;
    private ShangChenLiBiaoBean sclbb;
    //声明 实际金额
    private TextView tv_shijijier;
    //商品tatitle
    private TextView tv_titale;
    private ShangChenLiBiaoBeanDao scbbss;
//    private GwcSqlBeanDao scbbs;

    public PopupWindowBottonShoping(Activity context) {
        super(context);
        //初始化控件
        bindEvent();
    }
    /*初始化*/
    private void bindEvent() {
        if (popupView != null) {
            popupView.findViewById(R.id.image_colse).setOnClickListener(this);
            image_cimas = (ImageView) popupView.findViewById(R.id.image_cima);
            image_cimas.setOnClickListener(this);
            popupView.findViewById(R.id.btn_adds).setOnClickListener(this);
            /*实际金额*/
            tv_shijijier = (TextView) popupView.findViewById(R.id.tv_shijijiner);
            //tv_shijijier.setText(sclbb.getMoney());
            tv_shijijier.setText("198");
            /*商品titale*/
            tv_titale = (TextView) popupView.findViewById(R.id.tv_titale);
//            tv_titale.setText(sclbb.getTitale());
            tv_titale.setText("养生壶");
        }
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateAnimation(250 * 2, 0, 300);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.btoom_dialog, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.ll_waicheng);
    }

    @Override
    public void onClick(View view) {
        //  boolean 定义一个boolean型变量

        switch (view.getId()) {
            //这里是点击叉号 隐藏popupwindow
            case R.id.image_colse:
                dismiss();
                break;
            case R.id.image_cima:
                //这里 点击选择 button 按钮的图片 改变
                if (isActive) {
                    isActive = false;
                    image_cimas.setBackgroundResource(R.drawable.weixuanze_cima);
                } else {
                    isActive = true;
                    image_cimas.setBackgroundResource(R.drawable.xuanze_cima);
                }
                break;
            case R.id.btn_adds:
                /* 保存到数据库*/
//                scbbss =  MyGreenDaoUtils.saveData(getContext());
                String moneyses = sclbb.getMoney();
                String titale = sclbb.getTitale();
                String fukuanrenshu = sclbb.getFukuanrenshu();
                String Fresshping = sclbb.getFreeshipping();
                String address = sclbb.getAddress();
               /*
               * 这个对象就是·自己配置greendao  数据库的时候建立的那个对象
               * 就比如你要存入哪些字段就生成那个实力类。
               * 然后 把你要存入的字段 通过构造器传过去就OK
               * */
                ShangChenLiBiaoBean gsb = new ShangChenLiBiaoBean(null,titale,address,Fresshping,moneyses,fukuanrenshu);
                scbbss.insert(gsb);

                    /*点击确定按钮的时候 商品金额数据就带过去·到购物车列表*/
                Intent intent = new Intent(getContext(),ShoppingsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sclbb",sclbb);
                intent.putExtra("bundles",bundle);
                String styls = "1";
                intent.putExtra("styls",styls);
                getContext().startActivity(intent);
                dismiss();


                break;
        }
    }
}
