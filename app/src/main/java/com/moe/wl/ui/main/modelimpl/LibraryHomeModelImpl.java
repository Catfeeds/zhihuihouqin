package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.LibraryHomeModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class LibraryHomeModelImpl implements LibraryHomeModel {

    @Override
    public Observable getData(int position) {
        Log.e("LibraryHomeModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getLibraryHomeData(position);
        return observer ;
    }
}
