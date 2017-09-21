package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ExpertOrderModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public class ExpertOrderModelImpl implements ExpertOrderModel {

    @Override
    public Observable submitExpertOrder(int doctorId, int timeId) {
        Observable observable = RetrofitUtils.submitExpertOrder(doctorId, timeId);
        return observable;
    }
}
