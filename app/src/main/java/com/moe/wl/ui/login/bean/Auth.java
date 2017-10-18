package com.moe.wl.ui.login.bean;

/**
 * Created by 我的电脑 on 2017/10/17 0017.
 */

public class Auth {
    /* "officeid":1,
        "departid":1,
        "buildnum":1,
        "roomnum":121,
        "name":12332,
        "mobile":13269770032,
        "idcard":"111212130d",
        "positionid":132,
        "roomid":302,
        "officetel":"123",
        "nation":"1"*/
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
/*, String nation*/
    public Auth(String officeid, String departid, String buildnum, String roomnum, String name, String mobile, String idcard, String positionid, String officetel) {
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
    }
}
