package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.HistoryPostModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HistoryPostModelImpl implements HistoryPostModel {

    @Override
    public Observable getData(String page, String limit) {
        Observable observable = RetrofitUtils.getInstance().historyPost(page,limit);
        return observable;
    }
}
