package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.OrderHomeModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderHomeModelImpl implements OrderHomeModel {

    @Override
    public Observable getWaterType() {
        Observable observable = RetrofitUtils.getInstance().queryWaterType();
        return observable;
    }
}
