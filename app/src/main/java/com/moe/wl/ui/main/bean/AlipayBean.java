package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/16 0016.
 */

public class AlipayBean {

    /**
     * msg : success
     * errCode : 0
     * payLink : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017030105983566&biz_content=%7B%22body%22%3A%22%E9%A2%84%E7%BA%A6%E7%90%86%E5%8F%91%22%2C%22out_trade_no%22%3A%220061503479007343%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E9%A2%84%E7%BA%A6%E7%90%86%E5%8F%91%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.25%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Flocalhost%3A8080%2Fzhihuihouqin-api%2Fappuer%2Fpay%2Falipayreceive&sign=VNOAcl7D7OJIhi0XkjpPIKrB%2FrlkYP8l%2FK4x4Bl4q%2BbnZOt1Qr3r0VyzN8z2yRTdISkLAqbx%2Fjwwq6gNLazJTKtOBqvcxk4J6CxlQthiqgT6mHTy9dWrV32GfeCzzortuQFUGQH5phHmODOOZfYysOqCxeza3a1iO6eF5zt%2FRNO7Uc3Y172X0R3OR5vJOMpHMrD90IL2zkX0RtTVL4FZNvMloIQp0bCRzGj4W5gWpjpMkVHs%2FU5Pe6YlZB2fnM2%2BgoGJkvcxo923akxENwIeitwIbM3IO%2FkGJ%2FLYdvJfqjL7EWKEhXunusS%2BPtY8AQikV8bcZ8K6ah3AGVKcZEhf%2Fw%3D%3D&sign_type=RSA2&timestamp=2017-09-30+16%3A41%3A51&version=1.0
     */

    private String msg;
    private int errCode;
    private String payLink;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getPayLink() {
        return payLink;
    }

    public void setPayLink(String payLink) {
        this.payLink = payLink;
    }
}
