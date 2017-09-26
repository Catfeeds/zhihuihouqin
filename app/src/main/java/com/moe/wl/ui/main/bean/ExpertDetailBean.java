package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：专家详情Bean
 * 作者：Shixhe On 2017/9/19 0019
 */

public class ExpertDetailBean implements Serializable {

    private String msg;
    private ExpertEntity expert;
    private int totalcommentcount;
    private int errCode;
    private List<CommentlistBean> commentlist;
    private List<SchedulesEntity> schedules;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ExpertEntity getExpert() {
        return expert;
    }

    public void setExpert(ExpertEntity expert) {
        this.expert = expert;
    }

    public int getTotalcommentcount() {
        return totalcommentcount;
    }

    public void setTotalcommentcount(int totalcommentcount) {
        this.totalcommentcount = totalcommentcount;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List<CommentlistBean> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<CommentlistBean> commentlist) {
        this.commentlist = commentlist;
    }

    public List<SchedulesEntity> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<SchedulesEntity> schedules) {
        this.schedules = schedules;
    }

    public static class ExpertEntity implements Serializable {

        private String brief;
        private int consultcount;
        private String createtime;
        private String hospitalName;
        private int id;
        private int invitetotalcount;
        private String mobile;
        private String photo;
        private String positionname;
        private String realname;
        private String remaincount;
        private int score;
        private String skilledinfo;
        private int valid;
        private String worktime;

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public int getConsultcount() {
            return consultcount;
        }

        public void setConsultcount(int consultcount) {
            this.consultcount = consultcount;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getPositionname() {
            return positionname;
        }

        public void setPositionname(String positionname) {
            this.positionname = positionname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getSkilledinfo() {
            return skilledinfo;
        }

        public void setSkilledinfo(String skilledinfo) {
            this.skilledinfo = skilledinfo;
        }

        public String getWorktime() {
            return worktime;
        }

        public void setWorktime(String worktime) {
            this.worktime = worktime;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getInvitetotalcount() {
            return invitetotalcount;
        }

        public void setInvitetotalcount(int invitetotalcount) {
            this.invitetotalcount = invitetotalcount;
        }

        public String getRemaincount() {
            return remaincount;
        }

        public void setRemaincount(String remaincount) {
            this.remaincount = remaincount;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }
    }

    public static class SchedulesEntity implements Serializable {

        private String endtime;
        private int id;
        private int periodtype;
        private String starttime;
        private int status;

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPeriodtype() {
            return periodtype;
        }

        public void setPeriodtype(int periodtype) {
            this.periodtype = periodtype;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
