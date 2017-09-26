package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.OfficeIndexModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeIndexModelImpl implements OfficeIndexModel {

    @Override
    public Observable getIndex() {
        Observable observable = RetrofitUtils.getInstance().officeIndex();
        return observable;
    }
}
