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
import com.example.xiao.myappshuihu.dialog.PopupWindowRigth;
import com.example.xiao.myappshuihu.view.ScreenUtils;
import com.example.xiao.myappshuihu.view.SlidingArcView;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shouye,container,false);
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
                Toast.makeText(getActivity(),"点击选中条目"+index,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private String titles[] = {"养生汤", "花茶", "煮沸", "水果茶", "默认"};
    private int src[] = {R.drawable.yangshengtang2, R.drawable.huacha2, R.drawable.zhufei2, R.drawable.shuiguicha2, R.drawable.i5};
    private void initView(View view) {

        btn_add = (Button) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
         qtView= (SlidingArcView) view.findViewById(R.id.view);
        qtView.setData(src,titles);
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
