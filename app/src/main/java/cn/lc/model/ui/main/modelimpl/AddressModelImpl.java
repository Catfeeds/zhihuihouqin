package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddressModelImpl implements AddressModel {

    @Override
    public Observable getAddress() {
        Observable observable = RetrofitUtils.getInstance().getAddress();
        return observable;
    }
}
