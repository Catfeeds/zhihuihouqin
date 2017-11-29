package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.OfficeIndexBean;
import com.moe.wl.ui.main.model.OfficeIndexModel;
import com.moe.wl.ui.main.view.OfficeIndexView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeIndexPresenter extends MvpRxPresenter<OfficeIndexModel, OfficeIndexView> {

    public void getIndexInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getIndex();
        getNetWork(request, new Subscriber<OfficeIndexBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(OfficeIndexBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getIndexInfo(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
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
                if (mResponse==null)
                    return;
                if (mResponse.errCode==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
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
