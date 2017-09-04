package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.BarberDetailModel;
import cn.lc.model.ui.main.model.BarberListModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberDetailModelImpl implements BarberDetailModel {

    @Override
    public Observable getData(int id) {
        LogUtil.log("BarberDetailModelImpl-->login");
        Observable observer = RetrofitUtils.getInstance().getBarberDetail(id);
        return observer ;
    }
}
