package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddAddressModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddAddressModelImpl implements AddAddressModel {

    @Override
    public Observable addAddress(String realname, String mobile, String address) {
        Observable observable = RetrofitUtils.getInstance().addAddress(realname, mobile, address);
        return observable;
    }

    @Override
    public Observable editAddress(int id, String realname, String mobile, String address) {
        Observable observable = RetrofitUtils.getInstance().editAddress(id, realname, mobile, address);
        return observable;
    }

    @Override
    public Observable deleteAddress(int[] id) {
        Observable observable = RetrofitUtils.getInstance().deleteAddress(id);
        return observable;
    }
}
