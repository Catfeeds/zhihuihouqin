package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.OrderingModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class OrderingModelImpl implements OrderingModel {
    @Override
    public Observable getData(String userName, String phoneNumber, int timeId, int count, int addressId) {
        LogUtil.log("OrderingModelImpl-->getData");
        Observable observer = RetrofitUtils.getInstance().createOrdering(userName, phoneNumber, timeId, count, addressId);
        return observer;
    }

    @Override
    public Observable getTime() {
        LogUtil.log("OrderingModelImpl-->getTime");
        Observable observer = RetrofitUtils.getInstance().getTime();
        return observer;
    }
}
