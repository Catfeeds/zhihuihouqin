package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.SpOrderModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpOrderModelImpl implements SpOrderModel {

    @Override
    public Observable getData(String addressid,String expectedTime,String remark,String productList,
                              String skuid,String count) {
        Observable observable = RetrofitUtils.getInstance().spOrder(addressid,expectedTime,remark,productList,skuid,count);
        return observable;
    }
}
