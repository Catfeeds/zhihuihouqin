package com.moe.wl.ui.main.model;

import org.json.JSONArray;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface OrderInfoModel extends MvpModel {

    Observable getOrderTime();
    Observable generateOrder(String realname, String mobile, int addressId , String sendTime,
                             Object[] arr, String remark);

}