package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.BarberListModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberListModelImpl implements BarberListModel {

    @Override
    public Observable getData() {
        LogUtil.log("BarberListModelImpl请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getBarberList();
        return observer ;
    }
}
