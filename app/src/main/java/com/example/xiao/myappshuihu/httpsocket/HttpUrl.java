package com.example.xiao.myappshuihu.httpsocket;

public class HttpUrl {

    public static String APP_ID = null;

    //public static final String IMGURL = "http://api.sunsyi.com:8082/";
    public static final String IMGURL = "http://112.124.125.97:8082/";
    // public static final String URL = "http://112.124.125.97:8081";
    //
  public static final String URL = "http://api.sunyie.com:8081";// 正式
    //public static final String URL = "http://gkettle.sunyie.com";// 正式

    /**
     * 注册APP
     */
    public static final String REQ = URL + "/app/reg";
    /**
     * 绑定商品
     */
    public static final String BIND = URL + "/app/bind";
    /**
     * 获取商品列表
     */
    public static final String GET_MCHINELIST = URL + "/app/getmachinelist/";
    /**
     * 删除商品
     */
    public static final String DELE = URL + "/app/deletemachine/";

    /**
     * 发送加热命令
     */
    public static final String HEAT = URL + "/teapot/heat/";

    public static final String CANCELHEAT = URL + "/teapot/cancelheat/";

    public static final String SHTOPHEAT = URL + "/teapot/stopheat/";
    /**
     * 获取设备的当前状态
     */
    public static final String GET_STATE = URL + "/teapot/getstate/";
    /**
     * 获取商品使用记录
     */


    public static final String KETTLE_STATE = URL + "/teapot/stat";
    public static final String KETTLE_STATE_LIST = URL + "/teapot/getactionloglist";

    public static final String getnearmachine = URL + "/app/getnearmachine/";

    public static final String getorderlist = URL + "/teapot/getorderlist/";

    public static final String updatelocation = URL + "/app/updatelocation";

    /*
     * 加湿器
     */
    public static final String H_GET_STATE = URL + "/humidifier/getstate";
    // 开始停止
    public static final String H_START = URL + "/humidifier/start";
    public static final String H_STOP = URL + "/humidifier/stop";

    // 设置
    public static final String H_CONFIG = URL + "/humidifier/getconfig";

    public static final String H_SCONFIG = URL + "/humidifier/saveconfig";

    // 定时
    public static final String H_ORDER = URL + "/humidifier/order";

    public static final String H_CORDER = URL + "/humidifier/cancelorder";

    // 历史记录
    public static final String H_STAT = URL + "/humidifier/stat";

    public static final String H_STAT_LIST = URL
            + "/humidifier/getactionloglist";

    // 意见反馈
    public static final String FEEDBACK = URL + "/app/feedback";

    public static final String FEEDBACK_LIST = URL + "/app/feedbackdetail";

    //this is attendance activity main
    public static final String GET_ATTENDANCE_LIST = URL + "/attendance/getPunchList";
    public static final String PUT_ATTENDANCE_LOCATION = URL + "/attendance/updatelocation";
    //	public static final String GET_MONTH_LIST = URL + "/attendance/getPunchMonth";
//	public static final String GET_DAY_LIST = URL + "/attendance/getPunchDay";
//  fsg改了
    public static final String GET_MONTH_LIST = URL + "/attendance/getPunchMonth";
    public static final String GET_DAY_LIST = URL + "/attendance/getPunchDay";
    //this is attendance activity add member
    public static final String ADD_ATTENDANCE_MEMBER = URL + "/attendance/addpunch";
    public static final String ATTENDANCE_UPLOAD = URL + "/attendance/upload";
    public static final String ATTENDANCE_PUNCH_IN = URL + "/attendance/enter";
    public static final String ATTENDANCE_PUNCH_OUT = URL + "/attendance/leave";
    public static final String ATTENDANCE_GET_NAME = URL + "/attendance/getname";
    public static final String ATTENDANCE_SET_NAME = URL + "/attendance/setname";
    public static final String ATTENDANCE_SET_DEFAULT_TIME = URL + "/attendance/setworktime";
    public static final String ATTENDANCE_SET_PERSONAL_TIME = URL + "/attendance/setworktimeapp";
    public static final String ATTENDANCE_GET_PERSONAL_TIME = URL + "/attendance/getworktime";
    //this is switch activity
    public static final String SWITCH_GET_LIGHT = URL + "/attendance/addpunch";
    public static final String SWITCH_UPDATE = URL + "/switch/bindswitch";
    public static final String SWITCH_RESPONSE = URL + "/attendance/addpunch";

    /**
     * 打开灯
     */
    public static final String OPEN_LAMP = URL + "/light/start";

    /**
     * 关闭灯
     */
    public static final String CLOSE_LAMP = URL + "/light/stop";

    /**
     * 改变灯的状态
     */
    public static final String UPDATE_LAMP = URL + "/light/updatestate";
    /**
     * 灯预约
     */
    public static final String LAMP_ORDER = URL + "/light/order";
    /**
     * 灯取消预约
     */
    public static final String LAMP_CANCELORDER = URL + "/light/cancelorder";
    //灭蚊器状态
    public static final String GET_MOSQUITO_STATUS = URL + "/mosquitokiller/getstate";
    public static final String OPEN_MOSQUITO_STATUS = URL + "/mosquitokiller/start";
    public static final String STOP_MOSQUITO_STATUS = URL + "/mosquitokiller/stop";
    public static final String MOSQUITO_CANCEL_ORDER = URL + "/mosquitokiller/cancelorder";
    public static final String MOSQUITO_ORDER = URL + "/mosquitokiller/order";
    public static final String MOSQUITO_GET_CFG = URL + "/mosquitokiller/getconfig";
    public static final String MOSQUITO_SET_CFG = URL + "/mosquitokiller/saveconfig";
    public static final String MOSQUITO_STAT = URL + "/mosquitokiller/stat";
    public static final String MOSQUITO_STAT_LIST = URL + "/mosquitokiller/getactionloglist";
    public static final String ATTENDANCE_REG = URL + "/attendance/reg";
    public static final String ATTENDANCE_LOGIN = URL + "/attendance/login";

    public static final String GET_LIGHT_LIST = URL + "/switch/getLightList";
    public static final String BIND_SWITCH = URL + "/app/bindswitch";
    public static final String GET_HEAD = URL + "/attendance/getheadurl";


    public static final String LIGHT_CANCEL_ORDER = URL + "/light/cancelorder";
    public static final String LIGHT_ORDER = URL + "/light/order";
    public static final String LIGHT_GET_ORDER = URL + "/light/getorderlist";
    public static final String LIGHT_GET_CFG = URL + "/light/getconfig";
    public static final String LIGHT_SAVE_CFG = URL + "/light/saveconfig";
    public static final String LIGHT_STAT = URL + "/light/stat";
    public static final String LIGHT_STAT_LIST = URL + "/light/getactionloglist";


    public static final String MESSAGE_GET_LIST = URL + "/attendance/getmsg";
    public static final String MESSAGE_GET_DETAIL = URL + "/attendance/getmsginfo";
    public static final String MESSAGE_DELETE = URL + "/attendance/getmsginfo";
    public static final String MESSAGE_ACCEPTPUNCH = URL + "/attendance/acceptpunch";

    public static final String CENTER_LABEL_CHECK = URL + "/attendance/labelbind";


    //04 mutifuction
    public static final String MUTI_FUNCTION = URL + "/remotecontroll";
}
