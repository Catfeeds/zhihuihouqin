package com.moe.wl.ui.home.bean.office;

import java.io.Serializable;

/**
 * 作者 Wang
 * 日期 2017/10/19.
 * 描述 会议类型
 */

public class TypeListBean implements Serializable{
    /**
     * id : 1
     * typename : 文艺会议
     */

    private String id;
    private String typename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }
}