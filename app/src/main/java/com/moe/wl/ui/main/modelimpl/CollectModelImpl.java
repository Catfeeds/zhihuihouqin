package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.CollectModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class CollectModelImpl implements CollectModel {

    @Override
    public Observable getData(int type,int entityid) {
        Log.e("CollectModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getHealthInfoColect(type,entityid);
        return observer ;
    }
}
