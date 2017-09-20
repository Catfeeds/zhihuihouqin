package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.ShopCarModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ShopCarModelImpl implements ShopCarModel {

    @Override
    public Observable shopCar(String s,String count) {
        Observable observable = RetrofitUtils.getInstance().shopCar(s,count);
        return observable;
    }
}
