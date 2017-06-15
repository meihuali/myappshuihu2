package com.example.xiao.myappshuihu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.ShangPinLieBiaoBean;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.GlideUtils;
import com.example.xiao.myappshuihu.utils.PicassoUtils;

import java.util.List;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.adapter
 * 文件名：AnimationAdapter
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/9 0009 上午 10:35
 * 描述：TODO
 */
public class AnimationAdapterYSH extends BaseQuickAdapter<ShangPinLieBiaoBean.DataBean,BaseViewHolder> {
    private List<ShangPinLieBiaoBean.DataBean> data;
    private Context context;
    public AnimationAdapterYSH(int layoutResId,List<ShangPinLieBiaoBean.DataBean> data,Context context) {
        super(layoutResId, data);
        this.context = context;
    }
    @Override
    protected void convert(BaseViewHolder helper, ShangPinLieBiaoBean.DataBean item) {
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
            //glide 加载图片·
            if (!TextUtils.isEmpty(urls)) {
                GlideUtils.loadImageViewDiskCache(context,urls,imge_liebiao);
            }


        }



    }

}
