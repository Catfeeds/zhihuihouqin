package com.moe.wl.ui.main.modelimpl;





import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.HealthServerceModel;

import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public class HealthServerceModelImpl implements HealthServerceModel {

    @Override
    public Observable getData() {
        LogUtil.log("HealthServerceModel请求数据-->HealthServerceModelImpl");
       Observable observer = RetrofitUtils.getInstance().getHealthServiceHomeData();
        return observer ;
    }
}
