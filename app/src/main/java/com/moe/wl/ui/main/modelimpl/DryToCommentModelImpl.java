package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.ui.main.model.DryToCommentModel;

import java.io.File;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class DryToCommentModelImpl implements DryToCommentModel {

    @Override
    public Observable dryToComment(int oid, double stars, String content, String isAnonymous, File imgFile) {
        Observable observable = RetrofitUtils.getInstance().dryToComment(oid, stars, content, isAnonymous, imgFile);
        return observable;
    }
}
