package com.example.xiao.myappshuihu.fragmengt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiao.myappshuihu.R;
import com.example.xiao.myappshuihu.utils.L;
import com.example.xiao.myappshuihu.utils.SetTexts;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.fragmengt
 * 文件名：ShangCheng
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/3 0003 下午 5:00
 * 描述：TODO
 */
public class ShangCheng extends Fragment {
    //TabLayout
    private TabLayout mTabLayout;
    private ViewPager mviewpage;
    //tatile 用来封装tablayout 上的3个tab文字
    private List<String> tatile;
    //Fragment
    private List<Fragment> mFragment;
    //这是tatle的字体
    private ImageView tv_tatletext;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shangcheng, container, false);

        initData();
        iniView(view);
        return view;
    }
    /*初始化数据*/
    private void initData() {
        tatile = new ArrayList<>();
        tatile.add(getResources().getString(R.string.yangshenghu));
        tatile.add(getResources().getString(R.string.fenlei));
        tatile.add(getResources().getString(R.string.zhuanti));
        //初始化fragmengt
        mFragment = new ArrayList<>();
        mFragment.add(new YangShengHu());
        mFragment.add(new FenLei());
        mFragment.add(new ZhuanTi());
    }

    /*初始化view控件等等···*/
    private void iniView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.mtablayout);
        mviewpage = (ViewPager) view.findViewById(R.id.viewPager);
        tv_tatletext = (ImageView) view.findViewById(R.id.tv_tatletext);
//        //设置字体
//        SetTexts.setFont(getActivity(),tv_tatletext);
        //viewpage 预加载
        mviewpage.setOffscreenPageLimit(mFragment.size());
        //mviewpage 滑动监听
        mviewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                L.e("position"+position); /* 滑动监听第几页*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mviewpage.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override    //选中的item
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }
                //返回item个数
            @Override
            public int getCount() {
                return mFragment.size();
            }
            //这是标题
            @Override
            public CharSequence getPageTitle(int position) {
                return tatile.get(position);
            }
        });
        //绑定到Tablay 中
        mTabLayout.setupWithViewPager(mviewpage);
    }

}
