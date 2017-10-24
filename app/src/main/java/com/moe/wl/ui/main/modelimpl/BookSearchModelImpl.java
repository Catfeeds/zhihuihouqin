package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.BookSearchModel;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/24 0024
 */

public class BookSearchModelImpl implements BookSearchModel {

    @Override
    public Observable getSearchData() {
        Observable observable = RetrofitUtils.getInstance().getSearchData();
        return observable;
    }

    @Override
    public Observable clearHistory() {
        Observable observable = RetrofitUtils.getInstance().clearHistory();
        return observable;
    }
}
