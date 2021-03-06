package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.RecommandBookModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RecommandVBookModelImpl implements RecommandBookModel {

    @Override
    public Observable getData(String title,String author,String publisher,String remark) {

        Log.e("RecommandVBookModelImpl","请求数据");
        Observable observer = RetrofitUtils.getInstance().getRecommandResult(title,author,publisher,remark);
        return observer ;
    }
}
