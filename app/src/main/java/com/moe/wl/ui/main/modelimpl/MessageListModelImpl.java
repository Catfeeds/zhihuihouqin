package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.MessageListModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */

public class MessageListModelImpl implements MessageListModel {

    @Override
    public Observable getMessageListSucc(int messageType, int page) {
        Observable observable = RetrofitUtils.getMessageList(messageType, page);
        return observable;
    }
}
