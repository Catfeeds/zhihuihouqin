package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.ComplainHistoryModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class ComplainHistoryModelImpl implements ComplainHistoryModel {

    @Override
    public Observable getComplainHistory(int page, int pageCount) {
        Observable observable = RetrofitUtils.getInstance().getComplainHistory(page, pageCount);
        return observable;
    }
}
