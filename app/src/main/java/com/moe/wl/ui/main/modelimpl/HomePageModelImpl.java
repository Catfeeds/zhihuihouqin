package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.HomePageModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public class HomePageModelImpl implements HomePageModel {
    @Override
    public Observable getHomePageData() {
        Observable observable = RetrofitUtils.homePageData();
        return observable;
    }
}
