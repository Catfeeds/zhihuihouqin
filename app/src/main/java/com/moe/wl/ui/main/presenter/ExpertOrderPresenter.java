package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ExpertOrderBean;
import com.moe.wl.ui.main.model.ExpertOrderModel;
import com.moe.wl.ui.main.view.ExpertOrderView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 专家评论
 * Created by hh on 2017/5/12.
 */

public class ExpertOrderPresenter extends MvpRxPresenter<ExpertOrderModel, ExpertOrderView> {

    public void submitExpertOrder(int id, int timeID) {
        getView().showProgressDialog();
        Observable request = getModel().submitExpertOrder(id, timeID);
        getNetWork(request, new Subscriber<ExpertOrderBean>() {
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
            public void onNext(ExpertOrderBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().submitExpertOrderSucc(mResponse);
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
