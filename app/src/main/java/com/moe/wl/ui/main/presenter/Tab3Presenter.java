package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.view.Tab3View;
import com.moe.wl.ui.main.bean.MessageBean;
import com.moe.wl.ui.main.model.Tab3Model;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab3Presenter extends MvpRxPresenter<Tab3Model, Tab3View> {


    public void getData() {
        Observable request = getModel().getData();
        getNetWork(request, new Subscriber<MessageBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(MessageBean bean) {
                Log.e("errorCode", bean.getErrCode() + "");
                if (bean.getErrCode() == 0) {
                    getView().getDataSucc(bean);
                } else {
                    Log.e("getMsg", bean.getMsg());
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
