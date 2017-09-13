package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ComplainDetailModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ComplainDetailModelImpl implements ComplainDetailModel {

    @Override
    public Observable getComplainDetail(int id) {
        Observable observable = RetrofitUtils.getInstance().getComplainDetail(id);
        return observable;
    }
}
