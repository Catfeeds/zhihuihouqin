package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.UserWalletBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MyPurseModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.MyPurseView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MyPursePresenter extends MvpRxPresenter<MyPurseModel, MyPurseView> {

    public void getInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getInfo();
        getNetWork(request, new Subscriber<UserWalletBean>() {

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
            public void onNext(UserWalletBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getInfoSucc(bean);
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
