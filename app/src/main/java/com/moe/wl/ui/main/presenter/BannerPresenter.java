package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.model.BannerModel;
import com.moe.wl.ui.main.view.BannerView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：轮播图
 */

public class BannerPresenter extends MvpRxPresenter<BannerModel, BannerView> {

    public void getBanner(int serviceType) {
        getView().showProgressDialog();
        Observable request = getModel().getBanner(serviceType);
        getNetWork(request, new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse.errCode == 0) {
                    getView().setData(mResponse.getServiceInfo());
                } else {
                    getView().showToast(mResponse.msg);
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
