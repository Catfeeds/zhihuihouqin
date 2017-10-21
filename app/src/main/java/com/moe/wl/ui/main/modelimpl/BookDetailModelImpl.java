package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.BookDetailModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BookDetailModelImpl implements BookDetailModel {

    @Override
    public Observable getData(int type,int bookId) {
        Log.e("BookDetailModelImpl","请求数据-->bookdetail");
        Observable observer = RetrofitUtils.getInstance().addCollect(type,bookId);
        return observer ;
    }

    @Override
    public Observable getDetail(int id) {
        Observable observable = RetrofitUtils.getInstance().getBookDetailResult(id);
        return observable;
    }
}
