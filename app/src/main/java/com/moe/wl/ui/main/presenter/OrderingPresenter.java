package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.view.OrderingView;
import com.moe.wl.ui.main.model.OrderingModel;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class OrderingPresenter extends MvpRxPresenter<OrderingModel, OrderingView> {

    public void getData(String phoneNumber, int count, int type, String fixedmealtype, int duration) {
        getView().showProgressDialog();
        Observable login = getModel().getData(phoneNumber, count, type, fixedmealtype, duration);
        getNetWork(login, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().createOrderingSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    // 获取取餐时间
    public void getTime() {
        getView().showProgressDialog();
        Observable request = getModel().getTime();
        getNetWork(request, new Subscriber<SelectTimeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(SelectTimeBean listBean) {
                LogUtils.d(listBean.toString());
                if (listBean.getErrCode() == 0) {
                    getView().getTime(listBean);
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
