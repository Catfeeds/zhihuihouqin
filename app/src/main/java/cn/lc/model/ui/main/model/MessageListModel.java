package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/23 0023
 */

public interface MessageListModel extends MvpModel {

    Observable getMessageListSucc(int messageType, int page);

}
