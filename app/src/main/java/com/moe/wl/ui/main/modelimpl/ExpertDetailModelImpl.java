package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ExpertDetailModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ExpertDetailModelImpl implements ExpertDetailModel {

    @Override
    public Observable getExpertDetail() {
        Observable observer = RetrofitUtils.getInstance().getExpertDetail();
        return observer ;
    }
}
