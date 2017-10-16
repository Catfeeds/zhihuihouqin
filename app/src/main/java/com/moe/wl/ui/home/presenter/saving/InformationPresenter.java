package com.moe.wl.ui.home.presenter.saving;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.model.saving.InformationModel;
import com.moe.wl.ui.home.view.saving.InformationView;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;


public class InformationPresenter extends MvpRxPresenter<InformationModel, InformationView> {

    public void information() {
        getView().showProgressDialog();
        Observable request = getModel().information();
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("接口请求错误："+e);
                getView().setData();
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().setData();
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}