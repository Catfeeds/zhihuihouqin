package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.PreOderBarberModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PreOrderBarberModelImpl implements PreOderBarberModel {

    @Override
    public Observable getData(int id) {
        LogUtil.log("PreOrderBarberModelImpl请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().preOrderBarber(id);
        return observer ;
    }
}
