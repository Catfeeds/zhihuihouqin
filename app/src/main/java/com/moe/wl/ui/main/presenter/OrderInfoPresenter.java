package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ApppointmentInfo;
import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.model.OrderInfoModel;
import com.moe.wl.ui.main.view.OrderInfoView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderInfoPresenter extends MvpRxPresenter<OrderInfoModel, OrderInfoView> {

    public void getOrderTime() {
        getView().showProgressDialog();
        Observable request = getModel().getOrderTime();
        getNetWork(request, new Subscriber<OrderWaterTimeBean>() {

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
            public void onNext(OrderWaterTimeBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getTimeSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void generateOrder(List<ApppointmentInfo> timeList, String realname, String mobile, int addressId , String sendTime,
                              Object[] arr, String remark) {
        getView().showProgressDialog();
        Observable request = getModel().generateOrder(timeList,realname,mobile,addressId,sendTime,arr,remark);
        getNetWork(request, new Subscriber<GenerateOrderWaterBean>() {

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
            public void onNext(GenerateOrderWaterBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().generateOrderSucc(bean);
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
