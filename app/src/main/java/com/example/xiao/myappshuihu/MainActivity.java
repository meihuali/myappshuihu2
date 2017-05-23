package com.example.xiao.myappshuihu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiao.myappshuihu.fragmengt.ShangCheng;
import com.example.xiao.myappshuihu.fragmengt.ShouYe;
import com.example.xiao.myappshuihu.fragmengt.WoDe;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /*声明三个tab栏的 linelayout*/
    private LinearLayout  llay_00,llay_01,llay_02;
    /*声明Fragmengt*/
    private Fragment mtab0,mtab01,mtab02;
    /*声明tab栏下面的每一个textview*/
    private TextView tv_00,tv_01,tv_02;
    /*声明tab栏下面的 每一张图片Image*/
    private ImageView image_00,image_01,image_02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView(); //初始化
        setSelect(0); //默认选择第几个开场的tab栏 显示

    }
    /*默认显示 第几个tab 显示*/
    private void setSelect(int i) {
        //获取fragment 的实例
        FragmentManager fm = getSupportFragmentManager();
        //获取fragment 管理器
        FragmentTransaction ft = fm.beginTransaction();
        //这里是 隐藏哪一个fragment
        hideFragment(ft);
//        下面开始对每一个fragment进行操作
        switch (i) {
            case 0:
                if (mtab0 == null) { //如果mtab0 等于空我们就去实例化他
                    mtab0 = new ShouYe();
                    //然后通过fragment管理器的ADD方法 把实例化过后的fragment对象放进 fragmengt 里面去
                    ft.add(R.id.id_content, mtab0);
                } else {
                    ft.show(mtab0); //否则说明fragment里面已经有该对象了·就直接让他显示吧
                }
                //同时把背景图片设置为亮的图片
                image_00.setImageResource(R.drawable.shouye_xuanzhong);
                //设置textview字体颜色
                tv_00.setTextColor(getResources().getColor(R.color.txt_balck));
                break;
            case 1:
                if (mtab01 == null) {
                    mtab01 = new ShangCheng();
                    ft.add(R.id.id_content, mtab01);
                } else {
                    ft.show(mtab01);
                }
                image_01.setImageResource(R.drawable.shangcheng_xuanzhong);
                tv_01.setTextColor(getResources().getColor(R.color.txt_balck));
                break;
            case 2:
                if (mtab02 == null) {
                    mtab02 = new WoDe();
                    ft.add(R.id.id_content, mtab02);
                } else {
                    ft.show(mtab02);
                }
                image_02.setImageResource(R.drawable.wode_xuanzhong);
                tv_02.setTextColor(getResources().getColor(R.color.txt_balck));
                break;
        }
        ft.commit(); //最后提交一下 到管理器里面
    }
    /*隐藏fragment*/
    private void hideFragment(FragmentTransaction ft) {
        if (mtab0 != null) { //如果fragmengt 不等于空 就隐藏他
            ft.hide(mtab0); //隐藏
            tv_00.setTextColor(getResources().getColor(R.color.but_color1)); //隐藏以后吧字体颜色换成自己想要的
        }
        if (mtab01 != null) {
            ft.hide(mtab01);
            tv_01.setTextColor(getResources().getColor(R.color.but_color1));
        }
        if (mtab02 != null) {
            ft.hide(mtab02);
            tv_02.setTextColor(getResources().getColor(R.color.but_color1));
        }
    }

    /*初始化控件*/
    private void initView() {
        llay_00 = (LinearLayout) findViewById(R.id.llay_00);
        llay_00.setOnClickListener(this);
        llay_01 = (LinearLayout) findViewById(R.id.llay_01);
        llay_01.setOnClickListener(this);
        llay_02 = (LinearLayout) findViewById(R.id.llay_02);
        llay_02.setOnClickListener(this);
        /*================textview==================*/
        tv_00 = (TextView) findViewById(R.id.tv_00);
        tv_01 = (TextView) findViewById(R.id.tv_01);
        tv_02 = (TextView) findViewById(R.id.tv_02);
        /*================imaggview===========================*/
        image_00 = (ImageView) findViewById(R.id.id_img00);
        image_01 = (ImageView) findViewById(R.id.id_img01);
        image_02 = (ImageView) findViewById(R.id.id_img02);
    }

    @Override   /*点击 下面teab栏的时候 事件*/
    public void onClick(View view) {
        restImage();//当用户点击的时候 首先 将所有的图片都改成灰色
        switch (view.getId()) {
            case R.id.llay_00:
                setSelect(0);
            break;
            case R.id.llay_01:
                setSelect(1);
            break;
            case R.id.llay_02:
                setSelect(2);
            break;
        }
    }
    /*当用户点击的时候 首先 将所有的图片都改成灰色*/
    private void restImage() {
        image_00.setImageResource(R.drawable.shouye_weixuanzhong);
        image_01.setImageResource(R.drawable.shangcheng_weixuanzhong);
        image_02.setImageResource(R.drawable.wode_weixuanzhong);
    }
}
