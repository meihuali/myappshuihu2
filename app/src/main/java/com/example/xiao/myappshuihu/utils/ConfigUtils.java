package com.example.xiao.myappshuihu.utils;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class ConfigUtils {
    /*接口总地址·这里是域名·也可以是IP*/
    public static final String CONFIG = "http://store.sunyie.com/";
    /*轮播图拼接的 前缀*/
    public static final String LU_BO_IMAGE = "http://store.sunyie.com/public/uploads/";
    /*商品列表接口后缀,1为 养生壶*/
    public static final String SOPING_HOU_ZHUI = "Healthkettle/list_.php/type/1";
    /*商品列表接口后缀,2为 分类里面的养生壶*/
    public static final String SOPING_HOU_ZHUI_TWO = "Healthkettle/list_.php/type/2";
    public static final String SOPING_HOU_ZHUANTI_TWO = "Healthkettle/list_.php/type/3";

    /*==================================================================================================*/
    /*主界面的右上角的那个popupwindow 里面的 历史记录接口*/
   // http://api.sunsyi.com:8081/teapot/getorderlist/machineid/01020601411608666666/appid/d7e728b2-2257-4ddc-8e55-4d2b83de17f2/page/1/pagesize/10
    public static final String LI_SHI_JI_LU = "http://api.sunsyi.com:8081/teapot/getactionloglist/machineid/01020601411608666666/appid/";
    //这个是后缀
    public static final String LI_SHI_JI_LU_HOUZHUI = "/page/1/pagesize/10";

    //==========水壶显示界面==========
   // http://api.sunyie.com:8081/app/getnearmachine/appid/8610690389464250/onlineflag/all/bindflag/unbind
    public static final String SHUIHUSHOU = "http://api.sunyie.com:8081/app/getnearmachine/appid/";
    public static final String SHUIHUHOUZHUI = "/onlineflag/all/bindflag/unbind";

}
