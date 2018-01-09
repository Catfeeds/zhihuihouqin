package com.moe.wl.ui.main.bean;

import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/12 0012.
 */

public class HairOrderDetailBean {

    /**
     * msg : success
     * errCode : 0
     * detaillist : [{"orderid":284,"price":12,"name":"板寸","id":361,"typename":"普剪"},{"orderid":284,"price":22,"name":"小卷","id":362,"typename":"精剪"}]
     * detail : {"addr":"教育部机关北楼西侧厢房","barberid":19,"changes":0,"checkstatus":0,"createtime":"2017-11-20 14:04","invitetime":"2017-11-21 09:30","isdel":0,"mobile":"18515826536","ordercode":"0061511157883023","orderid":284,"paystatus":0,"paytime":null,"paytype":null,"photo":"http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710231508751503990.jpg","price":34,"realname":"单承高","score":null,"status":1,"uid":48}
     */

    private String msg;
    private int errCode;
    private DetailBean detail;
    private List<DetaillistBean> detaillist;

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

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public List<DetaillistBean> getDetaillist() {
        return detaillist;
    }

    public void setDetaillist(List<DetaillistBean> detaillist) {
        this.detaillist = detaillist;
    }

    public static class DetailBean {
        /**
         * addr : 教育部机关北楼西侧厢房
         * barberid : 19
         * changes : 0
         * checkstatus : 0
         * createtime : 2017-11-20 14:04
         * invitetime : 2017-11-21 09:30
         * isdel : 0
         * mobile : 18515826536
         * ordercode : 0061511157883023
         * orderid : 284
         * paystatus : 0
         * paytime : null
         * paytype : null
         * photo : http://dentist.oss-cn-beijing.aliyuncs.com/zhhq:lffw:img201710231508751503990.jpg
         * price : 34
         * realname : 单承高
         * score : null
         * status : 1
         * uid : 48
         */

        private String addr;
        private int barberid;
        private int changes;
        private int checkstatus;
        private String createtime;
        private String invitetime;
        private int isdel;
        private String mobile;
        private String ordercode;
        private int orderid;
        private int paystatus;
        private Object paytime;
        private Object paytype;
        private String photo;
        private int price;
        private String realname;
        private double score;
        private int status;
        private int uid;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public int getBarberid() {
            return barberid;
        }

        public void setBarberid(int barberid) {
            this.barberid = barberid;
        }

        public int getChanges() {
            return changes;
        }

        public void setChanges(int changes) {
            this.changes = changes;
        }

        public int getCheckstatus() {
            return checkstatus;
        }

        public void setCheckstatus(int checkstatus) {
            this.checkstatus = checkstatus;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getInvitetime() {
            return invitetime;
        }

        public void setInvitetime(String invitetime) {
            this.invitetime = invitetime;
        }

        public int getIsdel() {
            return isdel;
        }

        public void setIsdel(int isdel) {
            this.isdel = isdel;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getPaystatus() {
            return paystatus;
        }

        public void setPaystatus(int paystatus) {
            this.paystatus = paystatus;
        }

        public Object getPaytime() {
            return paytime;
        }

        public void setPaytime(Object paytime) {
            this.paytime = paytime;
        }

        public Object getPaytype() {
            return paytype;
        }

        public void setPaytype(Object paytype) {
            this.paytype = paytype;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }

    public static class DetaillistBean {
        /**
         * orderid : 284
         * price : 12
         * name : 板寸
         * id : 361
         * typename : 普剪
         */

        private int orderid;
        private int price;
        private String name;
        private int id;
        private String typename;

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }
    }
}
