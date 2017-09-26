package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/9/1 0001.
 */

public class DoctorListBean {

    @Override
    public String toString() {
        return "DoctorListBean{" +
                "msg='" + msg + '\'' +
                ", errCode=" + errCode +
                ", doctorlist=" + doctorlist +
                '}';
    }

    /**
     * msg : success
     * errCode : 0
     * doctorlist : [{"available":1,"consultcount":12,"doctorid":1,"hospitalname":"医务室","photo":"www.baidu.com","positionname":"主治医师","realname":"张德鲁","remaincount":1,"skilledinfo":"擅长高血压，高血脂","totalcount":2}]
     */

    private String msg;
    private int errCode;
    private List<DoctorlistBean> doctorlist;

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

    public List<DoctorlistBean> getDoctorlist() {
        return doctorlist;
    }

    public void setDoctorlist(List<DoctorlistBean> doctorlist) {
        this.doctorlist = doctorlist;
    }

    public static class DoctorlistBean implements Serializable{
        /**
         * available : 1
         * consultcount : 12
         * doctorid : 1
         * hospitalname : 医务室
         * photo : www.baidu.com
         * positionname : 主治医师
         * realname : 张德鲁
         * remaincount : 1
         * skilledinfo : 擅长高血压，高血脂
         * totalcount : 2
         */

        private int available;
        private int consultcount;
        private int doctorid;
        private String hospitalname;
        private String photo;
        private String positionname;
        private String realname;
        private int remaincount;
        private String skilledinfo;
        private int totalcount;

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
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

        public int getRemaincount() {
            return remaincount;
        }

        public void setRemaincount(int remaincount) {
            this.remaincount = remaincount;
        }

        public String getSkilledinfo() {
            return skilledinfo;
        }

        public void setSkilledinfo(String skilledinfo) {
            this.skilledinfo = skilledinfo;
        }

        public int getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(int totalcount) {
            this.totalcount = totalcount;
        }
    }
}
