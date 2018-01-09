package com.moe.wl.ui.main.bean;

/**
 * Created by 我的电脑 on 2017/12/23 0023.
 */

public class Demand {
    private int username;
    private int age;
    private int depart;
    private int position;
    private int office;
    private int mobile;
    private int sex;
    private int nation;

    public Demand(int username, int age, int depart, int position, int office, int mobile, int sex, int nation) {
        this.username = username;
        this.age = age;
        this.depart = depart;
        this.position = position;
        this.office = office;
        this.mobile = mobile;
        this.sex = sex;
        this.nation = nation;
    }
}
