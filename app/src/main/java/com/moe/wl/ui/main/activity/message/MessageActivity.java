package com.moe.wl.ui.main.activity.message;

import com.moe.wl.ui.main.model.MessageListModel;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.bean.MessageListBean;
import com.moe.wl.ui.main.modelimpl.MessageListModelImpl;
import com.moe.wl.ui.main.presenter.MessageListPresenter;
import com.moe.wl.ui.main.view.MessageListView;

/**
 * 类描述：所有消息页面父类
 * 作者：Shixhe On 2017/9/23 0023
 */

public abstract class MessageActivity extends BaseActivity<MessageListModel, MessageListView, MessageListPresenter> implements MessageListView {

    @Override
    public void setContentLayout() {
    }

    @Override
    public void initView() {

    }

    @Override
    public void getMessageListSucc(MessageListBean bean) {
        getDataSuccess(bean);
    }

    public abstract void getDataSuccess(MessageListBean bean);

    @Override
    public MessageListModel createModel() {
        return new MessageListModelImpl();
    }

    @Override
    public MessageListPresenter createPresenter() {
        return new MessageListPresenter();
    }
}
