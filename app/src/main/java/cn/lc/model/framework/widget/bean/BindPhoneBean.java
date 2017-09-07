package cn.lc.model.framework.widget.bean;

/**
 * Created by 我的电脑 on 2017/9/6 0006.
 */

public class BindPhoneBean {

    /**
     * msg : success
     * errCode : 0
     * userinfo : {"sex":null,"nickname":null,"photo":null,"userId":1,"mobile":"13211","realname":"12","nation":"汉族"}
     * token : 3795ceae-fa71-4afe-9a8e-30d43f981df0
     */

    private String msg;
    private int errCode;
    private UserinfoBean userinfo;
    private String token;

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

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserinfoBean {
        /**
         * sex : null
         * nickname : null
         * photo : null
         * userId : 1
         * mobile : 13211
         * realname : 12
         * nation : 汉族
         */

        private Object sex;
        private Object nickname;
        private Object photo;
        private int userId;
        private String mobile;
        private String realname;
        private String nation;

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }
    }
}
