package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ApppointmentInfo;
import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.UserDepositBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.MyDepositModel;
import com.moe.wl.ui.main.view.MyDepositView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MyDepositPresenter extends MvpRxPresenter<MyDepositModel, MyDepositView> {

    public void getDepositInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getDepositInfo();
        getNetWork(request, new Subscriber<UserDepositBean>() {

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
            public void onNext(UserDepositBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getUserDepositResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    public void generateOrder(List<ApppointmentInfo> timeList, String realname, String mobile, int addressId, int sendTime,
                              Object[] arr, String remark) {
        getView().showProgressDialog();
        Observable request = getModel().generateOrder(timeList,realname, mobile, addressId, sendTime + "", arr, remark);
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

    public void generateChargeWalletOrder(double s, int s1, int s2) {
        getView().showProgressDialog();
        Observable request = getModel().generateChargeWalletOrder(s, s1, s2);
        getNetWork(request, new Subscriber<WalletOrderBean>() {

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
            public void onNext(WalletOrderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getOrderResult(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void backDeposit() {
        getView().showProgressDialog();
        Observable observable = getModel().getBackDeposit();
        getNetWork(observable, new Subscriber<ActivityPostBean>() {

            @Override
            public void onCompleted() {
                LogUtils.i("退还押金完成了");
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable==", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                LogUtils.i("返回押金的onnext方法"+bean.getErrCode());
                if (bean.getErrCode() == 0) {
                    getView().backDepositResult(bean);
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
