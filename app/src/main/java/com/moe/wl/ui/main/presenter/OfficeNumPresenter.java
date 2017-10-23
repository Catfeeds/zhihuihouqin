package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.OfficeslistBean;
import com.moe.wl.ui.main.model.OfficeNumModel;
import com.moe.wl.ui.main.view.OfficeNumView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeNumPresenter extends MvpRxPresenter<OfficeNumModel, OfficeNumView> {

    public void getOfficeNum() {
        getView().showProgressDialog();
        Observable request = getModel().getOfficeList();
        getNetWork(request, new Subscriber<OfficeslistBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(OfficeslistBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getOfficeNumResult(mResponse);
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
