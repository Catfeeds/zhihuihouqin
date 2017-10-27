package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class Auth {
    /**
     * officeid : 1
     * departid : 1
     * buildnum : 1
     * roomnum : 121
     * name : 12332
     * mobile : 13269770032
     * idcard : 111212130d
     * positionid : 132
     * roomid : 302
     * officetel : 123
     * nation : 1
     * email : 123.@123.com
     * sex : 1
     * birthday : 2017-09-09
     * startworktime : 2017-09-09
     * ldtime : 2017-09-09
     */

    private int officeid;
    private int departid;
    private int buildnum;
    private int roomnum;
    private String name;
    private int mobile;
    private String idcard;
    private int positionid;
    private int roomid;
    private String officetel;
    private String nation;
    private String email;
    private String sex;
    private String birthday;
    private String startworktime;
    private String ldtime;

    public int getOfficeid() {
        return officeid;
    }

    public void setOfficeid(int officeid) {
        this.officeid = officeid;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public int getBuildnum() {
        return buildnum;
    }

    public void setBuildnum(int buildnum) {
        this.buildnum = buildnum;
    }

    public int getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(int roomnum) {
        this.roomnum = roomnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getOfficetel() {
        return officetel;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStartworktime() {
        return startworktime;
    }

    public void setStartworktime(String startworktime) {
        this.startworktime = startworktime;
    }

    public String getLdtime() {
        return ldtime;
    }

    public void setLdtime(String ldtime) {
        this.ldtime = ldtime;
    }

    public Auth(int officeid, int departid, int buildnum, int roomnum, String name, int mobile,/* String idcard,*/ int positionid, String officetel, String nation, String email, String sex, String birthday, String startworktime, String ldtime) {
        this.officeid = officeid;
        this.departid = departid;
        this.buildnum = buildnum;
        this.roomnum = roomnum;
        this.name = name;
        this.mobile = mobile;
        this.idcard = idcard;
        this.positionid = positionid;
        this.officetel = officetel;
        this.nation = nation;
        this.email = email;
        this.sex = sex;
        this.birthday = birthday;
        this.startworktime = startworktime;
        this.ldtime = ldtime;
    }
    /* *//* "officeid":1,
        "departid":1,
        "buildnum":1,
        "roomnum":121,
        "name":12332,
        "mobile":13269770032,
        "idcard":"111212130d",
        "positionid":132,
        "roomid":302,
        "officetel":"123",
        "nation":"1"*//*
    private String officeid;
    private String departid;
    private String buildnum;
    private String roomnum;
    private String name;
    private String mobile;
    private String idcard;
    private String positionid;
    private String roomid;
    private String officetel;
//    private String nation;
*//*, String nation*/
/*    public Auth(String officeid, String departid, String buildnum, String roomnum, String name, String mobile, String idcard, String positionid, String officetel) {
        this.officeid = officeid;
        this.departid = departid;
        this.buildnum = buildnum;
        this.roomnum = roomnum;
        this.name = name;
        this.mobile = mobile;
        this.idcard = idcard;
        this.positionid = positionid;
        this.officetel = officetel;
//        this.nation = nation;
    }*/
}
