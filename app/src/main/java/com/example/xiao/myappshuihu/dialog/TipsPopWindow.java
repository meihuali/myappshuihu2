package com.example.xiao.myappshuihu.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.dialog
 * 文件名：TipsPopWindow
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/29 0029 18:21
 * 描述：TODO
 */
public class TipsPopWindow extends PopupWindow {
    private View contentView;
    private String t_mubiaowendu;
    private String t_jinghuashijian;
    private String t_baowenshijian;
    private String t_shangcijiareshijian;

    public TipsPopWindow(Context context,String mubiaowendu,String jinghuashijian,String baowenshijian,String shangcijiareshijian) {
        contentView = LayoutInflater.from(context).inflate(R.layout.popitemcenterwindow, null);
        this.setContentView(contentView);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        this.t_mubiaowendu = mubiaowendu;
        this.t_jinghuashijian = jinghuashijian;
        this.t_baowenshijian = baowenshijian;
        this.t_shangcijiareshijian = shangcijiareshijian;
        //初始化控件
        initView(contentView);
    }
    /*
    * 初始化
    * */
    private void initView(View contentView) {
        TextView tv_mubiaowendu = (TextView) contentView.findViewById(R.id.tv_mubiaowendu);
        TextView tv_jinghuashijian = (TextView) contentView.findViewById(R.id.tv_jinghuashijian);
        TextView tv_baowenshijian = (TextView) contentView.findViewById(R.id.tv_baowenshijian);
        TextView tv_shangcijiareshijian = (TextView) contentView.findViewById(R.id.tv_shangcijiareshijian);
        //设置目标温度
        tv_mubiaowendu.setText(t_mubiaowendu);
        //设置净化时间
        tv_jinghuashijian.setText(t_jinghuashijian);
        //设置保温时间
        tv_baowenshijian.setText(t_baowenshijian);
        //设置上次加热时间
        tv_shangcijiareshijian.setText(t_shangcijiareshijian);
    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
          // this.showAsDropDown(parent,0,parent.getHeight());
            this.showAtLocation(parent, Gravity.CENTER,0,parent.getHeight()+80);
        } else {
            this.dismiss();
        }
    }



}
