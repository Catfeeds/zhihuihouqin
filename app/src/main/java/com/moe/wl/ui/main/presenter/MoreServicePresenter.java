package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ServiceMyBean;
import com.moe.wl.ui.main.model.MoreServiceModel;
import com.moe.wl.ui.main.view.MoreServiceView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MoreServicePresenter extends MvpRxPresenter<MoreServiceModel, MoreServiceView> {

    public void getMyService() {
        getView().showProgressDialog();
        Observable request = getModel().getMyService();
        getNetWork(request, new Subscriber<ServiceMyBean>() {
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
            public void onNext(ServiceMyBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getMyServiceSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }


    public void submitMyService(int[] ids) {
        getView().showProgressDialog();
        Observable request = getModel().submitMyService(ids);
        getNetWork(request, new Subscriber<CollectBean>() {
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
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().submitMyServiceSucc(bean);
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
