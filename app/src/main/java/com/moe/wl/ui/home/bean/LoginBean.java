package com.moe.wl.ui.home.bean;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginBean {

    /**
     * msg : success
     * errCode : 0
     * userinfo : {"sex":null,"nickname":null,"photo":null,"userId":1,"mobile":"13211","
     * realname":"12","nation":"汉族"}
     * token : 3795ceae-fa71-4afe-9a8e-30d43f981df0
     */
    private String msg;
    private int errCode;
    private UserinfoBean userinfo;
    private String token;
    private String userId;

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

    public String getUserId() {
        return userId;
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

        private String sex;
        private String nickname;
        private String photo;
        private int userId;
        private String mobile;
        private String realname;
        private String nation;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
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
