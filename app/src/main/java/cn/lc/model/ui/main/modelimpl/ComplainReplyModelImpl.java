package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ComplainReplyModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ComplainReplyModelImpl implements ComplainReplyModel {

    @Override
    public Observable getComplainReply(int id, int page) {
        Observable observable = RetrofitUtils.getInstance().getComplainReply(id, page);
        return observable;
    }

    @Override
    public Observable feedbackMessage(int id, String content) {
        Observable observable = RetrofitUtils.getInstance().feedbackMessage(id, content);
        return observable;
    }
}
