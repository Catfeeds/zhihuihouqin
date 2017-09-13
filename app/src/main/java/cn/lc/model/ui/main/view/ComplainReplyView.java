package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ComplainReplyBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ComplainReplyView extends MvpView {

    void getComplainReply(ComplainReplyBean bean);

    void feedbackMessage();

}
