package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.MoreServiceModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/22 0022
 */

public class MoreServiceModelImpl implements MoreServiceModel {

    @Override
    public Observable getMyService() {
        Observable observable = RetrofitUtils.getMyService();
        return observable;
    }

    @Override
    public Observable submitMyService(int[] ids) {
        Observable observable = RetrofitUtils.submitMyService(ids);
        return observable;
    }
}
