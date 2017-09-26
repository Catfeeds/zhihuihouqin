package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.HistoryPostModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HistoryPostModelImpl implements HistoryPostModel {

    @Override
    public Observable getData(String page, String limit) {
        Observable observable = RetrofitUtils.getInstance().historyPost(page,limit);
        return observable;
    }
}
