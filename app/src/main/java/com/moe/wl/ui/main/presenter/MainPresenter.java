package com.moe.wl.ui.main.presenter;

import com.moe.wl.ui.main.model.MainModel;
import com.moe.wl.ui.main.view.MainView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MainPresenter extends MvpRxPresenter<MainModel, MainView> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        Observable login = getModel().login("", "");
        getNetWork(login, new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object loginBean) {

            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
