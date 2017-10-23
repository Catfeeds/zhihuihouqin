package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.model.VegetableOrderMessageModel;
import com.moe.wl.ui.main.view.VegetableOrderMessageView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class VegetableOrderMessagePresenter extends MvpRxPresenter<VegetableOrderMessageModel, VegetableOrderMessageView> {

    public void getVegetableSelectTime() {
        getView().showProgressDialog();
        Log.e("BookDetailPresenter","发出请求");
        Observable request = getModel().getVegetableSelectTime();
        getNetWork(request, new Subscriber<SelectTimeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(SelectTimeBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getVegetableSelectTimeSucc(mResponse);
                }else{
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
