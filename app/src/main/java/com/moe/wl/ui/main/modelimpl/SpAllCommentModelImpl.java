package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.SpAllCommentModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpAllCommentModelImpl implements SpAllCommentModel {

    @Override
    public Observable getData(String id,String type,String page,String limit) {
        Observable observable = RetrofitUtils.getInstance().getSpAllComment(id,type,page,limit);
        return observable;
    }
}
