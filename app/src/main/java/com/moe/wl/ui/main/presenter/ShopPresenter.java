package com.moe.wl.ui.main.presenter;

import android.util.Log;

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

            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误", e.getMessage());
            }

            @Override
            public void onNext(ShopBean shopBean) {
                Log.e("errorCode",shopBean.getErrCode()+"");
                if (shopBean.getErrCode() == 0) {
                    getView().getShopInfo(shopBean);
                } else {
                    Log.e("getMsg",shopBean.getMsg());
                    getView().showToast(shopBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
