package com.example.xiao.myappshuihu.entity;

/**
 * Created by Administrator on 2017/7/7 0007.
 */

public class ZfbBean {

    /**
     * status : 1
     * data : alipay_sdk=alipay-sdk-php-20161101&app_id=2017061607503315&biz_content=%7B%22body%22%3A%22%5Cu5168%5Cu81ea%5Cu52a8%5Cu52a0%5Cu539a%5Cu73bb%5Cu7483%5Cu591a%5Cu529f%5Cu80fd%5Cu517b%5Cu751f%5Cu58f6%5Cu517b%5Cu751f%5Cu58f6%5Cu7cfb%5Cu5217%5Cu4ea7%5Cu54c1%22%2C%22subject%22%3A2%2C%22out_trade_no%22%3A%221499409103816475%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2299.0%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fstore.sunyie.com%2FAlipay%2FCompletePayment.php&sign_type=RSA2&timestamp=2017-07-07+14%3A31%3A43&version=1.0&sign=DZkx2cvm3WH0yR04Jo3sAttBRXy3Z1uzxzLEx9Mbtrmejm%2FlMYOXt2OnMdLflnbLNIc8DHwiKsc%2FWKJDsYMB2IQEjUhWFzu5y%2FRakLDslakm13WpIxSzwGYKEHp%2FN4s4EcuRnaanLRbhD%2B4nVD%2FUx3%2F959hrXNkI7Pxae5SdNnWIpDsBpoV2a2ZH6lCdPVXnsWTneB1nZMpnbx2Qaw90D0UzwyAIt9t1wskt5WSlLwfIBFkaEM3BvTGarrvUe5NrE2uG0mEFWwrITTklJkC%2Bn9dVcIKAyU3Pe5APkUPKZNZjZJkJHVT0duCURQuNfwb1%2F3TW5Mr0ZzVfj%2BZDcfzffg%3D%3D
     */

    private int status;
    private String data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
