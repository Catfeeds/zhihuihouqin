package com.moe.wl.ui.login.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.login.model.PositionListModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PositionListModelImpl implements PositionListModel {


    @Override
    public Observable getPositionList() {
        Log.e("PositionListModelImpl","--->获取了数据");
        Observable observer = RetrofitUtils.getInstance().getPostionList();
        return observer ;
    }

}
