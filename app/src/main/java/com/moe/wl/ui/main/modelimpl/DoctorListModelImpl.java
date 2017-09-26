package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.ui.main.model.DoctorListModel;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorListModelImpl implements DoctorListModel {

    @Override
    public Observable getData() {
        Log.e("DoctorListModelImpl","请求了数据");
        Observable observer = RetrofitUtils.getInstance().getDoctorList();
        return observer ;
    }
}
