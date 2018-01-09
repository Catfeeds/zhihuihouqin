package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.McNocticeModel;

import java.util.List;
import java.util.Map;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticeModelImpl implements McNocticeModel {

    @Override
    public Observable findUserFavorList(String type) {
        Observable observable = RetrofitUtils.getInstance().findUserFavorList(type);
        return observable;
    }

    @Override
    public Observable deleteFavorList(List<Map<String, Integer>> type) {
        Observable observable = RetrofitUtils.getInstance().deleteFavorList(type);
        return observable;
    }
}
