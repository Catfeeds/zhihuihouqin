package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.ConsultModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ConsultModelImpl implements ConsultModel {

    @Override
    public Observable getData(int id) {
        Observable observable = RetrofitUtils.getInstance().consultBarber(id);
        return observable;
    }
    @Override
    public Observable sendMessage(int id,String content) {
        Observable observable = RetrofitUtils.getInstance().sendMessage(id,content);
        return observable;
    }
}
