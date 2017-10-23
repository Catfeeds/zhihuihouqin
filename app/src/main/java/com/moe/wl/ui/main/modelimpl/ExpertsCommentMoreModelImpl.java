package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ExpertsCommentMoreModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/23 0023
 */

public class ExpertsCommentMoreModelImpl implements ExpertsCommentMoreModel {

    @Override
    public Observable getData(int doctorid, int page) {
        Observable observable = RetrofitUtils.getInstance().getExpertComment(doctorid, page, 20);
        return observable;
    }
}
