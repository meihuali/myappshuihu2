package com.example.xiao.myappshuihu.fragmengt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.appliction.BaseAPPliction;
import com.example.xiao.myappshuihu.dialog.PopupWindowRigth;
import com.example.xiao.myappshuihu.sqlite.DBhelperManager;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.ShareUtils;
import com.example.xiao.myappshuihu.utils.ToastUtil;
import com.example.xiao.myappshuihu.utils.ToolsGetAppId;
import com.example.xiao.myappshuihu.view.ScreenUtils;
import com.example.xiao.myappshuihu.view.SlidingArcView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：ShouYe
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 4:36
 * 描述：TODO
 */
public class ShouYe extends Fragment implements View.OnClickListener {
    private Button btn_add;
    private SlidingArcView qtView;
    private List<DBhelperManager.ZDYData> mList;
    private String machineid;
    private String wendumama;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shouye, container, false);

        initView(view);
        initLinestener();
        return view;
    }

    /*点击事件*/
    private void initLinestener() {
        /*        qtView.setQtScrollListener(new SlidingArcView.QTScrollListener() {
            @Override
            public void onSelect(View v, int index) {
                Toast.makeText(getApplicationContext(),"选中条目"+index,Toast.LENGTH_SHORT).show();
            }
        });*/
        qtView.setQtItemClickListener(new SlidingArcView.QTItemClickListener() {
            @Override
            public void onClick(View v, int index) {
                Toast.makeText(getActivity(), "点击选中条目" + index, Toast.LENGTH_SHORT).show();
                DBhelperManager.ZDYData zdydata = mList.get(index);
                //appid
                String appi = ToolsGetAppId.getinitAppId(getActivity());
                //温度
                String wendu = zdydata.ZDY_SW;
                // 80°c
                if (wendu.contains("°c")) {
                   String wendus =  wendu.substring(0,wendu.indexOf("°"));
                    int wenduma = Integer.parseInt(wendus);
                    wendumama = wenduma+"C";
                }

                //是否煮沸
                String feiteng = zdydata.ZDY_ISZF;
                // 净化时间
                String jinghua = zdydata.ZDY_TIME1;
                //保温时间
                String baowen = zdydata.ZDY_TIME;
                //加热时间  暂时为0
                //水的重量
                String zhongliang = "0.5L";

                String url = "http://gkettle.sunyie.com/teapot/heat/machineid/"+machineid+"/appid/"+appi+"/temp/"+wendumama+"/boil/"+feiteng+"/purify/"+jinghua+"/keepwarm/"+baowen+"/heattime/"+0+"/costtime/"+180+"/week/"+0000000+"/startremind/1/endremind/"+1+"/nowaterremind/"+1+"/nowaterremindthreshold/"+zhongliang;
                OkGo.get(url)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(String s, Call call, Response response) {
                                L.e("烧水结果  "+s);
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
                                ToastUtil.Short(getActivity(),"烧水接口请求失败");
                            }
                        });
            }
        });
    }

   // private String titles[] = {"养生汤", "花茶", "煮沸", "水果茶", "默认"};
   // private int src[] = {R.drawable.yangshengtang2, R.drawable.huacha2, R.drawable.zhufei2, R.drawable.shuiguicha2, R.drawable.i5};

    private void initView(View view) {
        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        qtView = (SlidingArcView) view.findViewById(R.id.view);

         machineid = ShareUtils.getString(getActivity(), "micID", "");
         mList = DBhelperManager.getInstance(getActivity()).getAlermList(1, machineid);
        qtView.setData(mList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                PopupWindowRigth pwr = new PopupWindowRigth(getActivity());
                pwr.showPopupWindow(R.id.btn_add);

                break;
        }
    }
}
