package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/9 0009
 */

public interface ComplainReplyModel extends MvpModel {

    Observable getComplainReply(int id);

    Observable feedbackMessage(int id, String content);

}
