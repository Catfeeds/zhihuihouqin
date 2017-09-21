package cn.lc.model.ui.main.bean;

/**
 * 类描述：获取订单返回bean
 * 作者：Shixhe On 2017/9/19 0019
 */

public class PayBean {

    private int errCode;
    private int ordertype;
    private int orderid;
    private String msg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
