package com.moe.wl.ui.main.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 我的电脑 on 2017/12/21 0021.
 */

public class Bbean implements Serializable{

    /**
     * msg : success
     * visitOrder : {"adminreason":null,"buildName":null,"buildingEntityList":null,"buildnum":null,"byuserlist":[{"id":169,"idcard":"8888888888888","name":"zzzzz","visituid":4,"vlid":null}],"cancelreason":null,"carcode":null,"cartype":null,"checked":0,"createtime":"2017-12-19 01:35:04","departname":null,"expertarrivaltime":"2017-12-19 01:35:04","expertleavetime":"2017-12-19 01:35:04","from":null,"id":4,"isdel":null,"mobile":null,"notify":null,"officename":null,"operatetype":null,"ordercode":"1513661704572","phonenum":"01045648754","realname":"王","reason":"","roomnum":"2211","stamp":null,"status":0,"token":null,"uid":45,"unit":null,"username":null,"valid":0,"vidnum":null,"visitchecked":1,"visitperiod":null,"visitreason":null,"visittime":null,"visittimeStr":null,"vlid":null,"vmobile":null,"vname":null,"vperiod":0,"vpnum":"0"}
     * errCode : 0
     */

    private String msg;
    private VisitOrderBean visitOrder;
    private int errCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VisitOrderBean getVisitOrder() {
        return visitOrder;
    }

    public void setVisitOrder(VisitOrderBean visitOrder) {
        this.visitOrder = visitOrder;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public static class VisitOrderBean implements Serializable {
        /**
         * adminreason : null
         * buildName : null
         * buildingEntityList : null
         * buildnum : null
         * byuserlist : [{"id":169,"idcard":"8888888888888","name":"zzzzz","visituid":4,"vlid":null}]
         * cancelreason : null
         * carcode : null
         * cartype : null
         * checked : 0
         * createtime : 2017-12-19 01:35:04
         * departname : null
         * expertarrivaltime : 2017-12-19 01:35:04
         * expertleavetime : 2017-12-19 01:35:04
         * from : null
         * id : 4
         * isdel : null
         * mobile : null
         * notify : null
         * officename : null
         * operatetype : null
         * ordercode : 1513661704572
         * phonenum : 01045648754
         * realname : 王
         * reason :
         * roomnum : 2211
         * stamp : null
         * status : 0
         * token : null
         * uid : 45
         * unit : null
         * username : null
         * valid : 0
         * vidnum : null
         * visitchecked : 1
         * visitperiod : null
         * visitreason : null
         * visittime : null
         * visittimeStr : null
         * vlid : null
         * vmobile : null
         * vname : null
         * vperiod : 0
         * vpnum : 0
         */

        private Object adminreason;
        private Object buildName;
        private Object buildingEntityList;
        private Object buildnum;
        private Object cancelreason;
        private Object carcode;
        private Object cartype;
        private int checked;
        private String createtime;
        private Object departname;
        private String expertarrivaltime;
        private String expertleavetime;
        private Object from;
        private int id;
        private Object isdel;
        private Object mobile;
        private Object notify;
        private Object officename;
        private Object operatetype;
        private String ordercode;
        private String phonenum;
        private String realname;
        private String reason;
        private String roomnum;
        private Object stamp;
        private int status;
        private Object token;
        private int uid;
        private String unit;
        private Object username;
        private int valid;
        private String vidnum;
        private int visitchecked;
        private Object visitperiod;
        private Object visitreason;
        private String visittime;
        private Object visittimeStr;
        private Object vlid;
        private String vmobile;
        private String vname;
        private int vperiod;
        private String vpnum;
        private List<ByuserlistEntity> byuserlist;

        public Object getAdminreason() {
            return adminreason;
        }

        public void setAdminreason(Object adminreason) {
            this.adminreason = adminreason;
        }

        public Object getBuildName() {
            return buildName;
        }

        public void setBuildName(Object buildName) {
            this.buildName = buildName;
        }

        public Object getBuildingEntityList() {
            return buildingEntityList;
        }

        public void setBuildingEntityList(Object buildingEntityList) {
            this.buildingEntityList = buildingEntityList;
        }

        public Object getBuildnum() {
            return buildnum;
        }

        public void setBuildnum(Object buildnum) {
            this.buildnum = buildnum;
        }

        public Object getCancelreason() {
            return cancelreason;
        }

        public void setCancelreason(Object cancelreason) {
            this.cancelreason = cancelreason;
        }

        public Object getCarcode() {
            return carcode;
        }

        public void setCarcode(Object carcode) {
            this.carcode = carcode;
        }

        public Object getCartype() {
            return cartype;
        }

        public void setCartype(Object cartype) {
            this.cartype = cartype;
        }

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Object getDepartname() {
            return departname;
        }

        public void setDepartname(Object departname) {
            this.departname = departname;
        }

        public String getExpertarrivaltime() {
            return expertarrivaltime;
        }

        public void setExpertarrivaltime(String expertarrivaltime) {
            this.expertarrivaltime = expertarrivaltime;
        }

        public String getExpertleavetime() {
            return expertleavetime;
        }

        public void setExpertleavetime(String expertleavetime) {
            this.expertleavetime = expertleavetime;
        }

        public Object getFrom() {
            return from;
        }

        public void setFrom(Object from) {
            this.from = from;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getIsdel() {
            return isdel;
        }

        public void setIsdel(Object isdel) {
            this.isdel = isdel;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getNotify() {
            return notify;
        }

        public void setNotify(Object notify) {
            this.notify = notify;
        }

        public Object getOfficename() {
            return officename;
        }

        public void setOfficename(Object officename) {
            this.officename = officename;
        }

        public Object getOperatetype() {
            return operatetype;
        }

        public void setOperatetype(Object operatetype) {
            this.operatetype = operatetype;
        }

        public String getOrdercode() {
            return ordercode;
        }

        public void setOrdercode(String ordercode) {
            this.ordercode = ordercode;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRoomnum() {
            return roomnum;
        }

        public void setRoomnum(String roomnum) {
            this.roomnum = roomnum;
        }

        public Object getStamp() {
            return stamp;
        }

        public void setStamp(Object stamp) {
            this.stamp = stamp;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public int getValid() {
            return valid;
        }

        public void setValid(int valid) {
            this.valid = valid;
        }

        public String getVidnum() {
            return vidnum;
        }

        public void setVidnum(String vidnum) {
            this.vidnum = vidnum;
        }

        public int getVisitchecked() {
            return visitchecked;
        }

        public void setVisitchecked(int visitchecked) {
            this.visitchecked = visitchecked;
        }

        public Object getVisitperiod() {
            return visitperiod;
        }

        public void setVisitperiod(Object visitperiod) {
            this.visitperiod = visitperiod;
        }

        public Object getVisitreason() {
            return visitreason;
        }

        public void setVisitreason(Object visitreason) {
            this.visitreason = visitreason;
        }

        public String getVisittime() {
            return visittime;
        }

        public void setVisittime(String visittime) {
            this.visittime = visittime;
        }

        public Object getVisittimeStr() {
            return visittimeStr;
        }

        public void setVisittimeStr(Object visittimeStr) {
            this.visittimeStr = visittimeStr;
        }

        public Object getVlid() {
            return vlid;
        }

        public void setVlid(Object vlid) {
            this.vlid = vlid;
        }

        public String getVmobile() {
            return vmobile;
        }

        public void setVmobile(String vmobile) {
            this.vmobile = vmobile;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public int getVperiod() {
            return vperiod;
        }

        public void setVperiod(int vperiod) {
            this.vperiod = vperiod;
        }

        public String getVpnum() {
            return vpnum;
        }

        public void setVpnum(String vpnum) {
            this.vpnum = vpnum;
        }

        public List<ByuserlistEntity> getByuserlist() {
            return byuserlist;
        }

        public void setByuserlist(List<ByuserlistEntity> byuserlist) {
            this.byuserlist = byuserlist;
        }

        /*public static class ByuserlistBean {
            *//**
             * id : 169
             * idcard : 8888888888888
             * name : zzzzz
             * visituid : 4
             * vlid : null
             *//*

            private int id;
            private String idcard;
            private String name;
            private int visituid;
            private Object vlid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getVisituid() {
                return visituid;
            }

            public void setVisituid(int visituid) {
                this.visituid = visituid;
            }

            public Object getVlid() {
                return vlid;
            }

            public void setVlid(Object vlid) {
                this.vlid = vlid;
            }
        }*/
    }
}
