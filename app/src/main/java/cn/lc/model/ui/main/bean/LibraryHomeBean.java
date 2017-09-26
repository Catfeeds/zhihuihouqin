package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/8/31 0031.
 */

public class LibraryHomeBean {

    /**
     * msg : success
     * booklist : [{"author":"1","bollowstatus":1,"id":1,"img":"1","publisher":"1","score":1,"shortbrief":"1","title":"1","typeid":1,"url":"1"},{"author":"2","bollowstatus":2,"id":2,"img":"2","publisher":"2","score":2,"shortbrief":"2","title":"2","typeid":2,"url":"1"}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<BooklistBean> booklist;

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

    public List<BooklistBean> getBooklist() {
        return booklist;
    }

    public void setBooklist(List<BooklistBean> booklist) {
        this.booklist = booklist;
    }

}
