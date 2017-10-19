package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.AlipayBean;
import com.moe.wl.ui.main.bean.ChargeOrderBean;
import com.moe.wl.ui.main.bean.FindRemainBean;
import com.moe.wl.ui.main.bean.LastCardNumBean;
import com.moe.wl.ui.main.bean.WeixinBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MealsRechargeModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.MealsRechargeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MealsRechargePresenter extends MvpRxPresenter<MealsRechargeModel, MealsRechargeView> {

    public void getLastCardNum() {
        getView().showProgressDialog();
        Observable request = getModel().findLastCardNum();
        getNetWork(request, new Subscriber<LastCardNumBean>() {

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
            public void onNext(LastCardNumBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getfindLastCardNumResult(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void getFindRemain() {
        getView().showProgressDialog();
        Observable request = getModel().findRemain();
        getNetWork(request, new Subscriber<FindRemainBean>() {

            @Override
            public void onCompleted() {
                LogUtils.i("获得余额成功了");
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(FindRemainBean bean) {
                LogUtils.i("获取余额");
                if (bean.getErrCode() == 0) {
                    getView().getFindRemain(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void generateChargeOrder(String money,int paytype,String cardNum) {
        getView().showProgressDialog();
        Observable request = getModel().generateChargeOrder(money,paytype,cardNum);
        getNetWork(request, new Subscriber<ChargeOrderBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(ChargeOrderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().generateChargeOrder(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void aliPay(String orderid,String ordercode,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordercode,ordertype,paytype);
        getNetWork(request, new Subscriber<AlipayBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(AlipayBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().aliPay(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void weiXinPay(String orderid,String ordercode,String ordertype,int paytype) {
        getView().showProgressDialog();
        Observable request = getModel().pay(orderid,ordercode,ordertype,paytype);
        getNetWork(request, new Subscriber<WeixinBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable=====", e.getMessage());
            }

            @Override
            public void onNext(WeixinBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().weiXinPay(bean);
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
