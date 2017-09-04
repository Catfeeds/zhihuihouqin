package cn.lc.model.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/1 0001.
 */

public class BookDetailBean {


    /**
     * msg : success
     * favorstatus : 1
     * errCode : 0
     */

    private String msg;
    private int favorstatus;
    private int errCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getFavorstatus() {
        return favorstatus;
    }

    public void setFavorstatus(int favorstatus) {
        this.favorstatus = favorstatus;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
