package com.example.xiao.myappshuihu.utils;

/**
 * 项目名：MyAppShuiHu
 * 包名：com.example.xiao.myappshuihu.utils
 * 文件名：ZiFuChuanZhuanHuan
 * 创建者 ：${梅华黎}
 * 创建时间： 2017/5/11 0011 下午 4:52
 * 描述：TODO
 */
public class ZiFuChuanZhuanHuan {
    private static double doubles;

    public static double paserDoubles(String text) {
        if (!text.equals("")) {
            doubles = Double.parseDouble(text);
        }
       return  doubles;
    }
}
