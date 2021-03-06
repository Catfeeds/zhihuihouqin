package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.SpCommentCountModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpCommentCountModelImpl implements SpCommentCountModel {

    @Override
    public Observable getData(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpAllCommentCount(id);
        return observable;
    }
}
