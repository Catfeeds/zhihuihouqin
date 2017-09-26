package com.moe.wl.ui.main.presenter;

import android.util.Log;

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
            public void onNext(ServiceMyBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getMyServiceSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
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
