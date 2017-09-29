package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.BarberMoreCommentModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class BarberMoreCommentModelImpl implements BarberMoreCommentModel {

    @Override
    public Observable getData(int id,int page,int limit) {
        Observable observable = RetrofitUtils.getInstance().getBarberMoreComment(id,page,limit);
        return observable;
    }
}
