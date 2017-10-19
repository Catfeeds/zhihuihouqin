package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.SaveAdviceModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SaveAdviceModelImpl implements SaveAdviceModel {

    @Override
    public Observable saveAdvice(String content) {
        Observable observable = RetrofitUtils.getInstance().saveAdvice(content);
        return observable;
    }
}
