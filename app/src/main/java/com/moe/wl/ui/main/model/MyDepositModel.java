package com.moe.wl.ui.main.model;

import com.moe.wl.ui.main.bean.ApppointmentInfo;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface MyDepositModel extends MvpModel {

    Observable getDepositInfo();
    Observable getBackDeposit();

    Observable generateChargeWalletOrder(double s, int s1, int s2);

    Observable getOrderTime();

    Observable generateOrder(List<ApppointmentInfo> timeList, String realname, String mobile, int addressId, String sendTime,
                             Object[] arr, String remark);
}
