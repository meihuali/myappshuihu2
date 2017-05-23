package com.example.xiao.myappshuihu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.LiShiJiLuBean;
import com.example.xiao.myappshuihu.utils.GetTime;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class MyLiShiJiLuAdapter extends BaseQuickAdapter<LiShiJiLuBean.DataBean,BaseViewHolder> {

    public MyLiShiJiLuAdapter(@LayoutRes int layoutResId, @Nullable List<LiShiJiLuBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LiShiJiLuBean.DataBean item) {
        //设置开始时间
        String shijian = item.getCreatetime();
        String zhuanhuanhou_shijian = GetTime.stampToDate(shijian);
        helper.setText(R.id.tv_date, zhuanhuanhou_shijian);
        //设置水量
        helper.setText(R.id.tv_shuiliang,item.getLevel());
        //设置温度
        helper.setText(R.id.tv_wendu, item.getTemp());
        //设置 耗电
        helper.setText(R.id.tv_haodian, item.getEnergy());
        //操作方式 有三种状态 需要判断
       String opera =  item.getOperation();
        if (opera.equals("0")) {
            helper.setText(R.id.tv_caozuo, "预约");
        }
        if (opera.equals("1")) {
            helper.setText(R.id.tv_caozuo, "手动");
        }
        if (opera.equals("2")) {
            helper.setText(R.id.tv_caozuo, "App");
        }
   /*     if (item.getOperation().equals(0)) {
            helper.setText(R.id.tv_caozuo, "预约");
        } else if (item.getOperation().equals(1)) {
            helper.setText(R.id.tv_caozuo, "手动");
        } else if (item.getOperation().equals(2)){
            helper.setText(R.id.tv_caozuo, "App");
        }*/
    }
}
