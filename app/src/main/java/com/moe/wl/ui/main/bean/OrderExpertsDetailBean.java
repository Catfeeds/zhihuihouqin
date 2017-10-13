package com.moe.wl.ui.main.bean;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderExpertsDetailBean {

    private String msg;
    private int errCode;
    private DetailEntity detail;

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

    public DetailEntity getDetail() {
        return detail;
    }

    public void setDetail(DetailEntity detail) {
        this.detail = detail;
    }

    public static class DetailEntity {

        private int changes;
        private String createtime;
        private DoctorEntity doctor;
        private int doctorid;
        private int id;
        private String ordercode;
        private String photo;
        private String realname;
        private Object remind;
        private String scheduledate;
        private int scheduleid;
        private int status;

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public DoctorEntity getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorEntity doctor) {
            this.doctor = doctor;
        }

        public int getDoctorid() {
            return doctorid;
        }

        public void setDoctorid(int doctorid) {
            this.doctorid = doctorid;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public Object getRemind() {
            return remind;
        }

        public void setRemind(Object remind) {
            this.remind = remind;
        }

        public String getScheduledate() {
            return scheduledate;
        }

        public void setScheduledate(String scheduledate) {
            this.scheduledate = scheduledate;
        }

        public int getScheduleid() {
            return scheduleid;
        }

        public void setScheduleid(int scheduleid) {
            this.scheduleid = scheduleid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public static class DoctorEntity {

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
            private int remaincount;
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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
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

            public int getInvitetotalcount() {
                return invitetotalcount;
            }

            public void setInvitetotalcount(int invitetotalcount) {
                this.invitetotalcount = invitetotalcount;
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

            public int getRemaincount() {
                return remaincount;
            }

            public void setRemaincount(int remaincount) {
                this.remaincount = remaincount;
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

            public int getValid() {
                return valid;
            }

            public void setValid(int valid) {
                this.valid = valid;
            }

            public String getWorktime() {
                return worktime;
            }

            public void setWorktime(String worktime) {
                this.worktime = worktime;
            }
        }
    }
}
