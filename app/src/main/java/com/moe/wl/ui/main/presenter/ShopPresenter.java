package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.ShopBean;
import com.moe.wl.ui.main.model.ShopModel;
import com.moe.wl.ui.main.view.ShopView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class ShopPresenter extends MvpRxPresenter<ShopModel, ShopView> {


    public void getData() {
        Log.e("ShopPresenter处理了", "--");
        Observable login = getModel().getData();
        getNetWork(login, new Subscriber<ShopBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("错误", e.getMessage());
            }

            @Override
            public void onNext(ShopBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                Log.e("errorCode", mResponse.getErrCode() + "");
                if (mResponse.getErrCode() == 0) {
                    getView().getShopInfo(mResponse);
                } else {
                    Log.e("getMsg", mResponse.getMsg());
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    public void getphotos(int servicetype) {
        Log.e("ShopPresenter处理了", "--");
        Observable login = getModel().getServiceInfo(servicetype);
        getNetWork(login, new Subscriber<BannerResponse>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse bean) {
                getView().getServiceInfo(bean);
 /*               if (bean.getErrCode() == 0) {

                } else {
                    Log.e("getMsg", bean.getMsg());
                    getView().showToast(bean.getMsg());
                }*/
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
