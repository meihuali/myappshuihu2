package com.example.xiao.myappshuihu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.SettingsBean;
import com.example.xiao.myappshuihu.entity.SettingsDataSqlBean;
import com.example.xiao.myappshuihu.entity.SettingsDataSql_item1_Bean;
import com.example.xiao.myappshuihu.sqlite.MyGreenDao_settings_Utils;
import com.example.xiao.myappshuihu.sqlite.MyGreenDao_settings_item_1_Utils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.Toasts;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public Context context;
    private List<SettingsBean> listss;
    public LayoutInflater minflater;
    public int mypostion;
    SettingsDataSqlBean sdmb;
    private SettingsDataSql_item1_Bean sdmb1;


    /*===========================接口回调=======================================*/
    //定义一个接口
    public interface OnClick {
        void itemClick(View view, int position, boolean b);
    }

    //声明接口为私有
    private OnClick click;

    //方法传入接口
    public void setClick(OnClick click) {
        this.click = click;
    }

    /*============================================================================*/

    public MyAdapter(List<SettingsBean> listss, Context context) {
        this.listss = listss;
        this.context = context;
        minflater = LayoutInflater.from(context);
//        程序刚进来的时候去遍历settingBean 对象取对应的名字的开关状态状态
        for (SettingsBean bean : listss) {
            boolean b = ShareUtils.getBoolean(context, bean.getName(), false);
            Log.e("TAG", b + "--");
            bean.setOpen(b);
        }
    }

    @Override /*加载 Item布局*/
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, final int position) {
        mypostion = position;
        L.e("mypostion  " + position);
        SettingsBean sb = listss.get(position); //获取对象
        holder.tv_wenduji.setText(sb.getWendu()); //设置温度
        holder.tv_names.setText(sb.getName());  //设置名字
        if (listss.get(position).isOpen()) {
            holder.mSwitch.setChecked(true);
        } else {
            holder.mSwitch.setChecked(false);
        }
            /*==============================注册开关回调事件====================================================*/
        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                L.e("statusssss  "+b);
                compoundButton.setChecked(b);
                listss.get(position).setOpen(b);
                if (click != null) {
                    click.itemClick(compoundButton, position, b);
                }
                ShareUtils.putBoolean(context, listss.get(position).getName(), b);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listss.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wenduji;
        TextView tv_names;
        View content;
        Switch mSwitch;
        ImageView avatar;
        LinearLayout ll_layout; //点击事件的

        public ViewHolder(View itemView) {
            super(itemView);
            //这里的初始化控件的ID 必须要跟·那个删除的 那个布局的 ID 相同·
            tv_wenduji = (TextView) itemView.findViewById(R.id.tv_wenduji);
            tv_names = (TextView) itemView.findViewById(R.id.tv_names);

            mSwitch = (Switch) itemView.findViewById(R.id.mSwitch);
            content = itemView.findViewById(R.id.content);
        }
    }
}
