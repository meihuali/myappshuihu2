package com.example.xiao.myappshuihu.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiao.myappshuihu.utils.L;

import java.util.List;

/**
 * 项目名：myappshuihu2
 * 包名：com.example.xiao.myappshuihu.adapter
 * 文件名：GuideAdapter
 * 创建者 ：梅华黎
 * 创建时间： 2017/9/28 0028 11:54
 * 描述：TODO
 */
public class GuideAdapter  extends PagerAdapter {
    private List<View> lists;

    public GuideAdapter(List<View> list) {
        this.lists = list;
    }

    @Override
    public int getCount() {
        L.e("测试个数  " + lists.size());
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(lists.get(position));
        return lists.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(lists.get(position));

        //super.destroyItem(container, position, object);
    }
}
