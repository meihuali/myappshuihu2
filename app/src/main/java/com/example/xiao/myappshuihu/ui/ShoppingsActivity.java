package com.example.xiao.myappshuihu.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.ShoppingsAdapterYSH;
import com.example.xiao.myappshuihu.dialog.PopupWindowCenter;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.entity.ShoppingcartlistBean;
import com.example.xiao.myappshuihu.sqlite.MyGreenDaoUtils;
import com.example.xiao.myappshuihu.utils.ConfigUtils;
import com.example.xiao.myappshuihu.utils.DividerItemDecoration;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ShoppingCartBiz;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ZiFuChuanZhuanHuan;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.http.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.ui
 * 文件名：ShoppingsActivity
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/10 0010 下午 3:01
 * 描述：该类负责购物车
 */
public class ShoppingsActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private ShoppingsAdapterYSH shoppingsAdapter;
    private ImageView imge_back;
    //假数据 集合
//    private List<ShangChenLiBiaoBean> list = new ArrayList<>();
    private List<ShoppingcartlistBean.DataBean> list = new ArrayList<>();
    private EditText et_shru;
    private TextView tv_add;
    private String types = "1";
    private int cuonts = 1;
    private ImageView tv_jians;
    private ImageView tv_jian_liang;
    private ImageView tv_jian;
    private TextView tv_moneyes;
    private double moneyss;
    private String money;
    private double moneys;
    private ShangChenLiBiaoBean sclbb;
    private String initTypes = "1";
    private Button btn_jiesuan;

    TextView btnSettle;
    TextView tvCountMoney;
    @Bind(R.id.tvTitle)
    TextView tvTitle;


    private ImageView ivCheckGood;
    //    private List<ShangChenLiBiaoBean> litss;
    private ShoppingcartlistBean.DataBean  gwcb;
    ImageView ivSelectAll;
    //购物车清单 集合
    private List<ShoppingcartlistBean> mlistshoppingcat;
    private View headerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppings);
        btnSettle = (TextView) findViewById(R.id.btnSettle);
        btnSettle.setOnClickListener(this);
        ivSelectAll=(ImageView)findViewById(R.id.ivSelectAll);
        tvCountMoney = (TextView) findViewById(R.id.tvCountMoney);
        initView();
    }

    /*控件初始化*/
    private void initView() {
        ivSelectAll = (ImageView) findViewById(R.id.ivSelectAll);
        ivSelectAll.setOnClickListener(this);
//        btn_jiesuan = (Button)findViewById(R.id.btn_jiesuan);
//        btn_jiesuan.setOnClickListener(this);
//        tv_moneyes = (TextView) findViewById(R.id.tv_moneyes);
        imge_back = (ImageView) findViewById(R.id.imge_back);
        imge_back.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*设置适配器*/
        shoppingsAdapter = new ShoppingsAdapterYSH(R.layout.activity_shoppings_item, list, getApplicationContext());
        //这个是一个适配里面的接口回调
        shoppingsAdapter.setOnShoppingCartChangeListener(new ShoppingsAdapterYSH.OnShoppingCartChangeListener() {
            @Override
            public void onDataChange(String selectCount, String selectMoney) {
                operateTotalMoney();
            }

            @Override
            public void onSelectItem(boolean isSelectedAll) {
                ShoppingCartBiz.checkItem(isSelectedAll, ivSelectAll);
            }

            @Override  //单选按钮
            public void onItemCheck(ShoppingcartlistBean.DataBean item) {
                for(int i=0;i<list.size();i++){
                    if(list.get(i)==item){
                        if(item.isChildSelected()){
                            item.setIsChildSelected(false);
                        }else{
                            item.setIsChildSelected(true);
                        }
                        shoppingsAdapter.notifyItemChanged(i);
                        operateTotalMoney();
                        break;
                    }
                }
            }
        });

        //通过监听器关联Activity和Adapter的关系，解耦；
//        View.OnClickListener listener = shoppingsAdapter.getAdapterListener();
//        if (listener != null) {
//            //结算时，一般是需要将数据传给订单界面的
//            btnSettle.setOnClickListener(shoppingsAdapter.getAdapterListener());
//        }
        mRecyclerView.setAdapter(shoppingsAdapter);

//        这一句是开启 item 动画
        shoppingsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //这句话·是绑定 recycleView 可以初始化 任何控件
        shoppingsAdapter.bindToRecyclerView(mRecyclerView);
/*======================下面这两句是从本地sqlite 数据库中去取出数据源===================================================================*/
  /*      //从数据库取数据
        litss = MyGreenDaoUtils.getqueryUserList(getApplicationContext());
        //添加到集合中
        list.addAll(litss);*/
  /*===========================================================================================*/
        //从服务器请求 数据源
        requsetHttpAskingshoppingcart();

        //设置item点击监听
        shoppingsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
/*                    case R.id.image_colses:
                        gwcb = list.get(position);
                        list.remove(gwcb);
                        //清楚集合后把 下面的总金额显示的 数据设为0
//                        tv_moneyes.setText(0+"");
                        shoppingsAdapter.notifyDataSetChanged();
                        shoppingsAdapter.notifyItemChanged(position);
//                       从数据库  删除点击的那个对象
                  //      MyGreenDaoUtils.deleData(getApplicationContext(), gwcb);
                        break;*/
//                         /* 这个方法是用来在activity 中初始化 item上的 控件的 */
//                        ivCheckGood = (ImageView) shoppingsAdapter.getViewByPosition(position, R.id.ivCheckGood);
                }
            }
        });


    }
    /*从服务器请求商品列表的接口*/
    private void requsetHttpAskingshoppingcart() {
        String url = ConfigUtils.ZhuYuMing+ConfigUtils.SHOPPINGEDSE_CART;
        HttpParams params = new HttpParams();
        String members =  ShareUtils.getString(getApplicationContext(),"member","");
        params.put("member_id",members);

        RxVolley.post(url, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                L.e("gouwucheqingdan  购物车清单"+t);
                try {
                    Gson gson = new Gson();
                    ShoppingcartlistBean shoping =  gson.fromJson(t, ShoppingcartlistBean.class);
                    int status = shoping.getStatus();
                    if (status == 1) {
                        List<ShoppingcartlistBean.DataBean> shopingbenData =  shoping.getData();
                        list.addAll(shopingbenData);
                        shoppingsAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    Toasts.makeTexts(getApplicationContext(),"购物车清单接口解析失败···");
                }
            }

            @Override
            public void onFailure(VolleyError error) {
                super.onFailure(error);
                Toasts.makeTexts(getApplicationContext(),"购物车清单接口挂了···");
            }
        });


    }

    /**
     * 更新购物车金额 单选的 金额
     */
    private void operateTotalMoney() {
        Double money = 0.0;
        int number=0;
        for (ShoppingcartlistBean.DataBean bean : list) {
            if (bean.isChildSelected()) {
                try {
                    money += Double.parseDouble(bean.getPrice()) * Integer.parseInt(bean.getNumber());
                    number+=Integer.parseInt(bean.getNumber());
                } catch (Exception e) {
                    continue;
                }
            }
        }
        String title = String.format(getResources().getString(R.string.count_money), money+"");
        tvCountMoney.setText(title);
        String moneyss = String.format(getResources().getString(R.string.count_goods), number+"");
        btnSettle.setText(moneyss);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imge_back:
                finish();
                break;
            case R.id.ivSelectAll: //这里是全选
                if(ivSelectAll.getTag()==null||!(Boolean) ivSelectAll.getTag()){
                    ivSelectAll.setImageResource(R.drawable.ic_checked);
                    ivSelectAll.setTag(true);
                    for(ShoppingcartlistBean.DataBean bean:list){
                        bean.setIsChildSelected(true);
                    }
                }else{
                    ivSelectAll.setImageResource(R.drawable.ic_uncheck);
                    ivSelectAll.setTag(false);
                    for(ShoppingcartlistBean.DataBean bean:list){
                        bean.setIsChildSelected(false);
                    }
                }
                shoppingsAdapter.notifyDataSetChanged();
                operateTotalMoney();
                break;
            case R.id.btnSettle:
                if (ShoppingCartBiz.hasSelectedGoods(list)) {

                    PopupWindowCenter pwc = new PopupWindowCenter(this);
                    pwc.showPopupWindow();
                } else {
                    Toasts.makeTexts(getApplicationContext(),"请选择商品···");
                }
                break;
        }
    }


}
