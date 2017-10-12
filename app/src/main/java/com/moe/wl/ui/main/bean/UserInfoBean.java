package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/10/9 0009.
 */

public class UserInfoBean {


    /**
     * errCode : 0
     * userinfo : {"id":1,"position":null,"sex":null,"nickname":"小张","tel":null,"photo":"http://avatar.csdn.net/C/1/8/1_u013083576.jpg","roomnum":null,"mobile":"13244243212","authStatus":0,"buildnum":"101"}
     * msg : success
     */

    private int errCode;
    private UserinfoBean userinfo;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class UserinfoBean {
        /**
         * id : 1
         * position : null
         * sex : null
         * nickname : 小张
         * tel : null
         * photo : http://avatar.csdn.net/C/1/8/1_u013083576.jpg
         * roomnum : null
         * mobile : 13244243212
         * authStatus : 0
         * buildnum : 101
         */

        private int id;
        private String position;
        private int  sex;
        private String nickname;
        private String tel;
        private String photo;
        private int  roomnum;
        private String mobile;
        private int authStatus;
        private String buildnum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getRoomnum() {
            return roomnum;
        }

        public void setRoomnum(int roomnum) {
            this.roomnum = roomnum;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(int authStatus) {
            this.authStatus = authStatus;
        }

        public String getBuildnum() {
            return buildnum;
        }

        public void setBuildnum(String buildnum) {
            this.buildnum = buildnum;
        }
    }
}
