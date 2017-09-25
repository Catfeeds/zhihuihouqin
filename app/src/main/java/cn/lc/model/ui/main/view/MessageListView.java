package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.MessageListBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */

public interface MessageListView extends MvpView {

    void getMessageListSucc(MessageListBean bean);

}
