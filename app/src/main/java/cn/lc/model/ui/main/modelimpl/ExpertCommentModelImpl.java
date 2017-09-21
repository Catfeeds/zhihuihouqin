package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ExpertCommentModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public class ExpertCommentModelImpl implements ExpertCommentModel {

    @Override
    public Observable getExpertCommentList(int id, int page, int pageSize) {
        Observable observable = RetrofitUtils.getExpertComment(id, page, pageSize);
        return observable;
    }
}
