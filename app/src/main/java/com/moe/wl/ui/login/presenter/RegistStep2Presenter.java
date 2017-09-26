package com.moe.wl.ui.login.presenter;

import android.util.Log;

import com.moe.wl.ui.login.bean.RegistBean;
import com.moe.wl.ui.login.model.RegistStep2Model;
import com.moe.wl.ui.login.view.RegistStep2View;

import mvp.cn.rx.MvpRxPresenter;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2Presenter extends MvpRxPresenter<RegistStep2Model, RegistStep2View> {

    public void getData(String userName, String captcha, String password) {
        Log.e("RegistStep2Presenter", "发起请求");
        getView().showProgressDialog();
        getModel().regist(userName, captcha, password).subscribe(new Subscriber<RegistBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(RegistBean registBean) {
                Log.e("registBean", registBean.getErrCode() + "");
                Log.e("registBean", registBean.getMsg() + "");
                if (registBean.getErrCode() == 0) {
                    getView().registSuccess(registBean);
                } else {
                    getView().showToast(registBean.getMsg());
                }
            }
        });
    }

    public void changePassWord(String userName, String captcha, String password) {
        getView().showProgressDialog();
        getModel().changePassWord(userName, captcha, password).subscribe(new Subscriber<RegistBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(RegistBean registBean) {
                Log.e("changePassWord", registBean.getErrCode() + "");
                Log.e("changePassWord", registBean.getMsg() + "");
                if (registBean.getErrCode() == 0) {
                    getView().changePassWord(registBean);
                } else {
                    getView().showToast(registBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
