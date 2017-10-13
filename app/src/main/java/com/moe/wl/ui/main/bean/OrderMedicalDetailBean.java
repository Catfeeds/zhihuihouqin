package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderMedicalDetailBean {

    private int errCode;
    private String msg;
    private List<DetailEntity> detail;

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

    public List<DetailEntity> getDetail() {
        return detail;
    }

    public void setDetail(List<DetailEntity> detail) {
        this.detail = detail;
    }

    public static class DetailEntity {

        private int changes;
        private String createtime;
        private DoctorEntity doctor;
        private int doctorid;
        private int ghpaystatus;
        private int id;
        private String ordercode;
        private int paystatus;
        private String photo;
        private String realname;
        private int remind;
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

        public int getGhpaystatus() {
            return ghpaystatus;
        }

        public void setGhpaystatus(int ghpaystatus) {
            this.ghpaystatus = ghpaystatus;
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

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
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

        public int getRemind() {
            return remind;
        }

        public void setRemind(int remind) {
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

            private Object available;
            private int consultcount;
            private int doctorid;
            private String hospitalname;
            private String photo;
            private String positionname;
            private String realname;
            private Object remaincount;
            private double score;
            private String skilledinfo;
            private Object totalcount;

            public Object getAvailable() {
                return available;
            }

            public void setAvailable(Object available) {
                this.available = available;
            }

            public int getConsultcount() {
                return consultcount;
            }

            public void setConsultcount(int consultcount) {
                this.consultcount = consultcount;
            }

            public int getDoctorid() {
                return doctorid;
            }

            public void setDoctorid(int doctorid) {
                this.doctorid = doctorid;
            }

            public String getHospitalname() {
                return hospitalname;
            }

            public void setHospitalname(String hospitalname) {
                this.hospitalname = hospitalname;
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

            public Object getRemaincount() {
                return remaincount;
            }

            public void setRemaincount(Object remaincount) {
                this.remaincount = remaincount;
            }

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getSkilledinfo() {
                return skilledinfo;
            }

            public void setSkilledinfo(String skilledinfo) {
                this.skilledinfo = skilledinfo;
            }

            public Object getTotalcount() {
                return totalcount;
            }

            public void setTotalcount(Object totalcount) {
                this.totalcount = totalcount;
            }
        }
    }
}
