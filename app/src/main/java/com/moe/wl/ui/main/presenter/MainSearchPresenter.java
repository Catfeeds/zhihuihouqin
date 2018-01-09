package com.moe.wl.ui.main.presenter;

import android.util.DisplayMetrics;
import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.HomeSearchBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.MainSearchModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.MainSearchView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class MainSearchPresenter extends MvpRxPresenter<MainSearchModel, MainSearchView> {

    public void homeSearch(String keyword) {
        getView().showProgressDialog();
        Observable request = getModel().homeSearch(keyword);
        getNetWork(request, new Subscriber<HomeSearchBean>() {

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
            public void onNext(HomeSearchBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().homeSearch(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
