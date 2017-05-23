package com.example.xiao.myappshuihu.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.entity.GouWuCheBean;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.sqlite.MyGreenDaoUtils;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShoppingCartBiz;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ZiFuChuanZhuanHuan;

import java.util.List;

import static com.example.xiao.myappshuihu.R.id.holder;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.adapter
 * 文件名：ShoppingsAdapterYSH
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/10 0010 下午 3:13
 * 描述：TODO
 */
public class ShoppingsAdapterYSH extends BaseQuickAdapter<ShangChenLiBiaoBean,BaseViewHolder> {
    public List<ShangChenLiBiaoBean> list;
    public Context context;
    private boolean isBoolean;
    private int cuonts = 1;
    private EditText et_shru;
    private ImageView tv_jian;
    private ImageView tv_jian_liang;
    private double moneys;
    private double moneyss;
    private boolean isSelectAll;
    /** 选中 */
    ImageView ivCheckGood;
    private boolean isChildSelected;

    public ShoppingsAdapterYSH(int layoutResId, List<ShangChenLiBiaoBean> list, Context context) {
        super(layoutResId,list);
        this.context = context;
        this.list = list;

    }
    /*================================================================接口回调*/
    private OnShoppingCartChangeListener mChangeListener;
    public interface OnShoppingCartChangeListener {
        void onDataChange(String selectCount, String selectMoney);
        void onSelectItem(boolean isSelectedAll);
        void onItemCheck(ShangChenLiBiaoBean item);
    }

    public void setOnShoppingCartChangeListener(OnShoppingCartChangeListener changeListener) {
        this.mChangeListener = changeListener;
    }
    /*============================================================================================*/

    public View.OnClickListener getAdapterListener() {
        return listener;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final ShangChenLiBiaoBean item) {
        helper.addOnClickListener(R.id.image_colses);
        // helper.addOnClickListener(R.id.tv_add); //这个是用户点击加号增加商品数量
        helper.addOnClickListener(R.id.tv_jian); //这个是用户点击 减号 减少 这个是那个不亮 的那个减号
//        helper.addOnClickListener(R.id.tv_jian_liang); //这个是用户点击 减号 减少 这个是那个亮的那个减号
        //显示item上的金额 单价
        helper.setText(R.id.tv_money, item.getMoney());
        //初始化选择 item 勾选结账 的那个图片
        helper.addOnClickListener(R.id.ivCheckGood);

        /*单选回调 把选中的 item 对象 回调出去·给 activity */
        helper.getView(R.id.ivCheckGood).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mChangeListener!=null){
                    mChangeListener.onItemCheck(item);
                }
            }
        });

        if(item.getIsChildSelected()){
            helper.setImageResource(R.id.ivCheckGood,R.drawable.ic_checked);
        }else{
            helper.setImageResource(R.id.ivCheckGood,R.drawable.ic_uncheck);
        }

        /*这里是item 上的点击 加号添加数据*/
        helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShoppingCartBiz.addOrReduceGoodsNum(true, item, ( (EditText) (  ((View) (view.getParent())  ).findViewById(R.id.et_shru) )  ) );
                setSettleInfo();
            }
        });
        /*这里是点击item 上的减号去 减少商品件数*/
        helper.getView(R.id.tv_jian_liang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = ShoppingCartBiz.addOrReduceGoodsNum(false, item, ((EditText) (((View) (view.getParent())).findViewById(R.id.et_shru))));
                L.e("num "+num);
                setSettleInfo();

            }
        });

        helper.setText(R.id.et_shru,item.getNumber());

    }


    //删除或者选择商品之后，需要通知结算按钮，更新自己的数据；
    private void setSettleInfo() {
        String[] infos = ShoppingCartBiz.getShoppingCount(list);

        if (mChangeListener != null && infos != null) {
            mChangeListener.onDataChange(infos[0], infos[1]);
        }
    }

    private void selectAll() {
        if (mChangeListener != null) {
            mChangeListener.onSelectItem(isSelectAll);
        }
    }
View.OnClickListener listener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSettle:
                if (ShoppingCartBiz.hasSelectedGoods(list)) {
                   Toasts.makeTexts(context,"等待开发中···");
                } else {
                    Toasts.makeTexts(context,"请选择商品···");
                }
                break;
        }
    }
};



}
