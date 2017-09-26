package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.PostNeedModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PostNeedModelImpl implements PostNeedModel {

    @Override
    public Observable getData(String realname, String mobile, String remark, String productName, String count) {
        Observable observable = RetrofitUtils.getInstance().post(realname, mobile, remark, productName, count);
        return observable;
    }
}
