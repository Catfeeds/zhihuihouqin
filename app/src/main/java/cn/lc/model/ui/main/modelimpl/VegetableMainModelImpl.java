package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.VegetableMainModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class VegetableMainModelImpl implements VegetableMainModel {

    @Override
    public Observable getVegetableData(int page, String keyword) {
        Observable observable = RetrofitUtils.getInstance().getVegetableData(page, keyword);
        return observable;
    }
}