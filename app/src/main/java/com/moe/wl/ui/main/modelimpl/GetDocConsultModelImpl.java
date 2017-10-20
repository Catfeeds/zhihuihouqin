package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.GetDocConsultListModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class GetDocConsultModelImpl implements GetDocConsultListModel {

    @Override
    public Observable getConsultList(String s) {
        Observable observable = RetrofitUtils.getInstance().expertnoticelist(s);
        return observable;
    }
    @Override
    public Observable setMess(int i, String s) {
        Observable observable = RetrofitUtils.getInstance().addexpertnotice(i,s);
        return observable;
    }
}
