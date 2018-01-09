package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.bean.ApppointmentInfo;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.OrderInfoModel;

import org.json.JSONArray;

import java.util.List;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderInfoModelImpl implements OrderInfoModel {

    @Override
    public Observable getOrderTime() {
        Observable observable = RetrofitUtils.getInstance().getOrderWaterTime();
        return observable;
    }

    @Override
    public Observable generateOrder(List<ApppointmentInfo> timeList, String realname, String mobile, int addressId, String sendTime, Object[] arr, String remark) {
        Observable observable = RetrofitUtils.getInstance().generateOrder(timeList,realname,mobile,addressId,sendTime,arr,remark);
        return observable;
    }
}
