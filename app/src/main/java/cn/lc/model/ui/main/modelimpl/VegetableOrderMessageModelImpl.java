package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.VegetableOrderMessageModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class VegetableOrderMessageModelImpl implements VegetableOrderMessageModel {

    @Override
    public Observable getVegetableSelectTime() {
        Observable observer = RetrofitUtils.getInstance().getVegetableSelectTime();
        return observer ;
    }
}
