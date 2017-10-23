package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ExpertsCommentBean;
import com.moe.wl.ui.main.model.ExpertsCommentMoreModel;
import com.moe.wl.ui.main.view.ExpertsCommentMoreView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ExpertsCommentMorePresenter extends MvpRxPresenter<ExpertsCommentMoreModel, ExpertsCommentMoreView> {

    public void getDat(int id, int page) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id, page);
        getNetWork(request, new Subscriber<ExpertsCommentBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ExpertsCommentBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getDataSucc(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
