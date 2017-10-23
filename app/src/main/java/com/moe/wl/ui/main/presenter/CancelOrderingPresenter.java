package com.moe.wl.ui.main.presenter;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ReasonBean;
import com.moe.wl.ui.main.model.CancelOrderingModel;
import com.moe.wl.ui.main.view.CancelOrderingView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */

public class CancelOrderingPresenter extends MvpRxPresenter<CancelOrderingModel, CancelOrderingView> {


    // 获取取消订单原因
    public void getReasonList(int serviceType) {
        getView().showProgressDialog();
        Observable request = getModel().getCancelReason(serviceType);
        getNetWork(request, new Subscriber<ReasonBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(ReasonBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getReasonList(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }


    // 取消报修订单
    public void cancelOrder(int serviceType, int oid, String content) {
        Observable observable = getModel().cancelOrder(serviceType, oid, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
