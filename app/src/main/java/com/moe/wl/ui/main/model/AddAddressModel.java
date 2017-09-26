package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface AddAddressModel extends MvpModel {

    Observable addAddress(String realname, String mobile, String address);

    Observable editAddress(int id, String realname, String mobile, String address);

    Observable deleteAddress(int[] id);

}
