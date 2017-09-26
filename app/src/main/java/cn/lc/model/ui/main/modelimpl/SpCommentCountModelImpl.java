package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.SpCommentCountModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpCommentCountModelImpl implements SpCommentCountModel {

    @Override
    public Observable getData(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpAllCommentCount(id);
        return observable;
    }
}
