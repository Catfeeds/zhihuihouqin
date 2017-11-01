package com.moe.wl.framework.base;

/**
 * 作者 Wang
 * 日期 2017/8/30.
 * 描述
 */

public class MessageEvent<T> {

    public static final int WECHAT_PAY = 1000;  //微信支付成功
    public static final int HEADERCHANGE = 1001;  //通知头像发生了变化


    private int code;
    private T data;
    private String param1;
    private String param2;
    private String param3;

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public MessageEvent(int code, String param1, String param2, String param3) {
        this.code = code;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public MessageEvent(int code) {
        this.code = code;
    }

    public MessageEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public MessageEvent(int code, String param1, String param2) {
        this.code = code;
        this.param1 = param1;
        this.param2 = param2;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
