package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.MessageListModel;
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
