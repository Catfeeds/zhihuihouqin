package cn.lc.model.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public class ActivitySignListBean {

    /**
     * msg : success
     * memberlist : [{"aId":1,"asAvatar":"http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg","asId":1,"asMebile":"13244243212","asUsername":"123","userId":1}]
     * errCode : 0
     */

    private String msg;
    private int errCode;
    private List<MemberlistBean> memberlist;

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

    public List<MemberlistBean> getMemberlist() {
        return memberlist;
    }

    public void setMemberlist(List<MemberlistBean> memberlist) {
        this.memberlist = memberlist;
    }

    public static class MemberlistBean {
        /**
         * aId : 1
         * asAvatar : http://img0.imgtn.bdimg.com/it/u=2600862994,2565094368&fm=26&gp=0.jpg
         * asId : 1
         * asMebile : 13244243212
         * asUsername : 123
         * userId : 1
         */

        private int aId;
        private String asAvatar;
        private int asId;
        private String asMebile;
        private String asUsername;
        private int userId;

        public int getAId() {
            return aId;
        }

        public void setAId(int aId) {
            this.aId = aId;
        }

        public String getAsAvatar() {
            return asAvatar;
        }

        public void setAsAvatar(String asAvatar) {
            this.asAvatar = asAvatar;
        }

        public int getAsId() {
            return asId;
        }

        public void setAsId(int asId) {
            this.asId = asId;
        }

        public String getAsMebile() {
            return asMebile;
        }

        public void setAsMebile(String asMebile) {
            this.asMebile = asMebile;
        }

        public String getAsUsername() {
            return asUsername;
        }

        public void setAsUsername(String asUsername) {
            this.asUsername = asUsername;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
