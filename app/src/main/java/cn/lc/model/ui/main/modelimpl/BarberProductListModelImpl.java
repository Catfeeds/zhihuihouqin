package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.BarberPoductListModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberProductListModelImpl implements BarberPoductListModel {

    @Override
    public Observable getData(String id,String page,String limit) {
        LogUtil.log("BarberProductListModelImpl请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getBarberProductList(id,page,limit);
        return observer ;
    }
}
