package com.example.xiao.myappshuihu.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoBean;
import com.example.xiao.myappshuihu.entity.ZhuanTiBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.PicassoUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class ZhuanTiAdapter extends BaseQuickAdapter<ShangPinLieBiaoBean.DataBean,BaseViewHolder>{
    private List<ShangPinLieBiaoBean.DataBean> data;
    private Context context;
    public ZhuanTiAdapter(int layoutResId, List<ShangPinLieBiaoBean.DataBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, ShangPinLieBiaoBean.DataBean item) {
        L.e("item "+item);
        //给item 上的子控件设置监听 然后在 主activity 里面调用 用adapter 掉setOnItemChildClickListener
        helper.addOnClickListener(R.id.imge_gouwuche);
        helper.addOnClickListener(R.id.ll_layout_item);
        if (item != null) {
            //设置tatile
            helper.setText(R.id.tv_titales, item.getName());
            //设置出售地址地址
            helper.setText(R.id.tv_addreagss, item.getAddress());
            //设置出售金额
            helper.setText(R.id.tv_moneyss, item.getPrice());
            //设置 使用人数
            helper.setText(R.id.tv_shiyongrenshu,item.getRurchase_number());

            // 设置商品列表 上的图片
            String urlss = item.getImg(); //先拿到网络图片的url
            // 这里拼接图片路径
            String urls = ConfigUtils.LU_BO_IMAGE+urlss;
            //这里要获得imageview 控件的对象 也就是初始化
            ImageView imge_liebiao = helper.getView(R.id.imge_liebiao);
            if (urls != "") {
                PicassoUtils.loadImageViewSize(context,urls,100,100,imge_liebiao);
            }
        }



    }

}
