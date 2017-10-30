package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.SpOrderModel;

import java.util.List;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpOrderModelImpl implements SpOrderModel {

    @Override
    public Observable getData(String addressid,String expectedTime,String remark,List productList) {
        Observable observable = RetrofitUtils.getInstance().spOrder(addressid,expectedTime,remark,productList);
        return observable;
    }
}
