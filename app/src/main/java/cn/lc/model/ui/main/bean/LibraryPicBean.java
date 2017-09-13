package cn.lc.model.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/9/8 0008.
 */

public class LibraryPicBean {

    /**
     * msg : success
     * errCode : 0
     * photo : http://dentist.oss-cn-beijing.aliyuncs.com/ciming_console/upload/community/img/201707131499911709547.jpg
     */

    private String msg;
    private int errCode;
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
