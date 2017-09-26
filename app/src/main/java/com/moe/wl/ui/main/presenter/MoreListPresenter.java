package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.model.MoreListModel;
import com.moe.wl.ui.main.view.MoreListView;
import com.moe.wl.ui.main.bean.MoreListBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MoreListPresenter extends MvpRxPresenter<MoreListModel, MoreListView> {

    public void getData(int page, int limit, String s) {
        getView().showProgressDialog();
        Observable login = getModel().getData(page, limit, s);
        getNetWork(login, new Subscriber<MoreListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(MoreListBean moreListBean) {
                if (moreListBean.getErrCode() == 0) {
                    getView().getMoreList(moreListBean);
                } else {
                    getView().showToast(moreListBean.getMsg());
                }
            }
        });
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
