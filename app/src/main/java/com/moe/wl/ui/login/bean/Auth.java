package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class Auth {

    private int officeid;
    private int departid;
    private String buildnum;
    private String roomnum;
    private String name;
    private String mobile;
    private int positionid;
    private String officetel;
    private int nation;
    private String email;
    private int sex;
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

    public String getBuildnum() {
        return buildnum;
    }

    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    public String getOfficetel() {
        return officetel;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    public int getNation() {
        return nation;
    }

    public void setNation(int nation) {
        this.nation = nation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public Auth(String birthday, String buildnum, int departid, String email, String ldtime, String mobile, String name,
                      int nation, int officeid, String officetel, int positionid, String roomnum, int sex, String startworktime) {
        this.birthday = birthday;
        this.buildnum = buildnum;
        this.departid = departid;
        this.email = email;
        this.ldtime = ldtime;
        this.mobile = mobile;
        this.name = name;
        this.nation = nation;
        this.officeid = officeid;
        this.officetel = officetel;
        this.positionid = positionid;
        this.roomnum = roomnum;
        this.sex = sex;
        this.startworktime = startworktime;
    }

}
