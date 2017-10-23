package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CreateorderBean;
import com.moe.wl.ui.main.bean.Itemid;
import com.moe.wl.ui.main.bean.Order;
import com.moe.wl.ui.main.bean.PreOrderBean;
import com.moe.wl.ui.main.model.NowOrderBarberModel;
import com.moe.wl.ui.main.view.PreOrderBarberView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class NowOrderBarberPresenter extends MvpRxPresenter<NowOrderBarberModel, PreOrderBarberView> {

    public void order() {
        getView().showProgressDialog();
        Observable request = getModel().orderBarber();
        getNetWork(request, new Subscriber<PreOrderBean>() {

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
            public void onNext(PreOrderBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getBarberInfo(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void createOrder(Order order, List<Itemid> list) {
        getView().showProgressDialog();
        Observable request = getModel().createOrder(order,list);
        getNetWork(request, new Subscriber<CreateorderBean>() {

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
            public void onNext(CreateorderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().createOrederResult(bean);
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
