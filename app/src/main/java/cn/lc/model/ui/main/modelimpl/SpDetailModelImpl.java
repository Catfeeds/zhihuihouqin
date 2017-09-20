package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.SpDetailModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpDetailModelImpl implements SpDetailModel {

    @Override
    public Observable getData(String s) {
        Observable observable = RetrofitUtils.getInstance().getSpDetail(s);
        return observable;
    }

    @Override
    public Observable collect(int s, int s1) {
        Observable observable = RetrofitUtils.getInstance().getHealthInfoColect(s,s1);
        return observable;

    }

    @Override
    public Observable getShopCarInfo(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpShopCarInfo(id);
        return observable;
    }
}
