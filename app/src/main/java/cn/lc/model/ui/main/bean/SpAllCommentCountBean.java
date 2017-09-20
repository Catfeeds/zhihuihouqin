package cn.lc.model.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/19 0019.
 */

public class SpAllCommentCountBean {

    /**
     * total : 1
     * goodPersent : 0.0%
     * badPersent : 100.0%
     * middlePersent : 0.0%
     * errCode : 0
     * msg : success
     */

    private int total;
    private String goodPersent;
    private String badPersent;
    private String middlePersent;
    private int errCode;
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getGoodPersent() {
        return goodPersent;
    }

    public void setGoodPersent(String goodPersent) {
        this.goodPersent = goodPersent;
    }

    public String getBadPersent() {
        return badPersent;
    }

    public void setBadPersent(String badPersent) {
        this.badPersent = badPersent;
    }

    public String getMiddlePersent() {
        return middlePersent;
    }

    public void setMiddlePersent(String middlePersent) {
        this.middlePersent = middlePersent;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
