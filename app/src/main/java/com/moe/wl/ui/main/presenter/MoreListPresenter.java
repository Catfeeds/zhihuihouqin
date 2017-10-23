package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.MoreListBean;
import com.moe.wl.ui.main.model.MoreListModel;
import com.moe.wl.ui.main.view.MoreListView;

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
            public void onNext(MoreListBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getMoreList(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
