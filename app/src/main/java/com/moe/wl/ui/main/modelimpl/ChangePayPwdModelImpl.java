package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.ChangePayPwdModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ChangePayPwdModelImpl implements ChangePayPwdModel {

    @Override
    public Observable getData(String pwd) {
        Observable observable = RetrofitUtils.getInstance().modifyCode(pwd);
        return observable;
    }

    @Override
    public Observable checkOldPassword(String pwd) {
        Observable observable = RetrofitUtils.getInstance().checkOldPassword(pwd);
        return observable;
    }
}
