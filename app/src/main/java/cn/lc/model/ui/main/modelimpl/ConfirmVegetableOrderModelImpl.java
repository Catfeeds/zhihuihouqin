package cn.lc.model.ui.main.modelimpl;

import java.util.HashMap;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ConfirmVegetableOrderModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ConfirmVegetableOrderModelImpl implements ConfirmVegetableOrderModel {

    @Override
    public Observable submitVegetableOrder(HashMap<String, String> map) {
        Observable observer = RetrofitUtils.getInstance().submitVegetableOrder(map);
        return observer ;
    }
}
