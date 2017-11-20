package com.moe.wl.ui.home.bean.office;

/**
 * 作者 Wang
 * 日期 2017/10/19.
 * 描述 会场设备
 */

public class EquipmentListBean {
    /**
     * code : 12
     * id : 1
     * name : 鲜花
     */

    private String code;
    private String id;
    private String name;
    private int count;
    private boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
