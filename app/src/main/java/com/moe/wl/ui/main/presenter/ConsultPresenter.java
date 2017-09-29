package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.ConsultBarberBean;
import com.moe.wl.ui.main.bean.SendMessageBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.ConsultModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.ConsultView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ConsultPresenter extends MvpRxPresenter<ConsultModel, ConsultView> {

    public void getConsultBarberInfo(int id) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id);
        getNetWork(request, new Subscriber<ConsultBarberBean>() {

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
            public void onNext(ConsultBarberBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getConsultInfo(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void sendMessage(int id,String content) {
        getView().showProgressDialog();
        Observable request = getModel().sendMessage(id,content);
        getNetWork(request, new Subscriber<SendMessageBean>() {

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
            public void onNext(SendMessageBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().sendMessageSucc(bean);
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
