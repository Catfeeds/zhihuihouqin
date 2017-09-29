package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.BarberMoreCommentBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.BarberMoreCommentModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.BarberMoreCommentView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class BarberMoreCommentPresenter extends MvpRxPresenter<BarberMoreCommentModel, BarberMoreCommentView> {

    public void getDat(int id,int page,int limit) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id,page,limit);
        getNetWork(request, new Subscriber<BarberMoreCommentBean>() {

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
            public void onNext(BarberMoreCommentBean bean) {
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
