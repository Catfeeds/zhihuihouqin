package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/9 0009
 */

public interface ComplainReplyModel extends MvpModel {

    Observable getComplainReply(int id, int page);

    Observable feedbackMessage(int id, String content);

}
