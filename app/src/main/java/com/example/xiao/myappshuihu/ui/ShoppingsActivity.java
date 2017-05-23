package com.example.xiao.myappshuihu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.adapter.ShoppingsAdapterYSH;
import com.example.xiao.myappshuihu.entity.ShangChenLiBiaoBean;
import com.example.xiao.myappshuihu.sqlite.MyGreenDaoUtils;
import com.example.xiao.myappshuihu.utils.ShoppingCartBiz;
import com.example.xiao.myappshuihu.utils.Toasts;
import com.example.xiao.myappshuihu.utils.ZiFuChuanZhuanHuan;

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
    private List<ShangChenLiBiaoBean> list = new ArrayList<>();
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
    // @Bind(R.id.btnSettle)
    TextView btnSettle;
    TextView tvCountMoney;
    @Bind(R.id.tvTitle)
    TextView tvTitle;


    private ImageView ivCheckGood;
    private List<ShangChenLiBiaoBean> litss;
    private ShangChenLiBiaoBean gwcb;
    ImageView ivSelectAll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppings);
        btnSettle = (TextView) findViewById(R.id.btnSettle);
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

        // 这里是遍历的假数据
    /*    for (int i = 0; i <30; i++) {
            GouWuCheBean gdcb = new GouWuCheBean();
            gdcb.setCounts(0);
            gdcb.setMoney(198);
            list.add(gdcb);
        }*/
        /*设置适配器*/
        shoppingsAdapter = new ShoppingsAdapterYSH(R.layout.activity_shoppings_item, list, getApplicationContext());
        shoppingsAdapter.setOnShoppingCartChangeListener(new ShoppingsAdapterYSH.OnShoppingCartChangeListener() {
            @Override
            public void onDataChange(String selectCount, String selectMoney) {
                operateTotalMoney();
            }

            @Override
            public void onSelectItem(boolean isSelectedAll) {
                ShoppingCartBiz.checkItem(isSelectedAll, ivSelectAll);
            }
            @Override
            public void onItemCheck(ShangChenLiBiaoBean item) {//单选按钮
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
        View.OnClickListener listener = shoppingsAdapter.getAdapterListener();
        if (listener != null) {
            //结算时，一般是需要将数据传给订单界面的
            btnSettle.setOnClickListener(shoppingsAdapter.getAdapterListener());
        }

        mRecyclerView.setAdapter(shoppingsAdapter);
//        这一句是开启 item 动画
        shoppingsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        //这句话·是绑定 recycleView 可以初始化 任何控件
        shoppingsAdapter.bindToRecyclerView(mRecyclerView);

        //从数据库取数据
        litss = MyGreenDaoUtils.getqueryUserList(getApplicationContext());
        //添加到集合中
        list.addAll(litss);
        shoppingsAdapter.notifyDataSetChanged();

        //设置item点击监听
        shoppingsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.image_colses:
                        gwcb = list.get(position);
                        list.remove(gwcb);
                        //清楚集合后把 下面的总金额显示的 数据设为0
//                        tv_moneyes.setText(0+"");
                        shoppingsAdapter.notifyDataSetChanged();
                        shoppingsAdapter.notifyItemChanged(position);

//                       从数据库  删除点击的那个对象
                        MyGreenDaoUtils.deleData(getApplicationContext(), gwcb);
                        break;


//                         /* 这个方法是用来在activity 中初始化 item上的 控件的 */
//                        ivCheckGood = (ImageView) shoppingsAdapter.getViewByPosition(position, R.id.ivCheckGood);


                }
            }
        });


    }

    /**
     * 更新购物车金额 单选的 金额
     */
    private void operateTotalMoney() {
        Double money = 0.0;
        int number=0;
        for (ShangChenLiBiaoBean bean : list) {
            if (bean.isChildSelected()) {
                try {
                    money += Double.parseDouble(bean.getMoney()) * Integer.parseInt(bean.getNumber());
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
                      for(ShangChenLiBiaoBean bean:list){
                          bean.setIsChildSelected(true);
                      }
                  }else{
                      ivSelectAll.setImageResource(R.drawable.ic_uncheck);
                      ivSelectAll.setTag(false);
                      for(ShangChenLiBiaoBean bean:list){
                          bean.setIsChildSelected(false);
                      }
                  }
                shoppingsAdapter.notifyDataSetChanged();
                operateTotalMoney();
                break;
        }
    }


}
