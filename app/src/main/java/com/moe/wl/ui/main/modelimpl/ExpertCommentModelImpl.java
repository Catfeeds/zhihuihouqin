package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.ExpertCommentModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public class ExpertCommentModelImpl implements ExpertCommentModel {

    @Override
    public Observable getExpertCommentList(int id, int page, int pageSize) {
        Observable observable = RetrofitUtils.getExpertComment(id, page, pageSize);
        return observable;
    }
}
