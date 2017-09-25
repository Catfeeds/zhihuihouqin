package cn.lc.model.ui.main.activity.message;

import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.ui.main.bean.MessageListBean;
import cn.lc.model.ui.main.model.MessageListModel;
import cn.lc.model.ui.main.modelimpl.MessageListModelImpl;
import cn.lc.model.ui.main.presenter.MessageListPresenter;
import cn.lc.model.ui.main.view.MessageListView;

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
