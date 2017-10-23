package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.CollectModel;
import com.moe.wl.ui.main.view.CollectView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class CollectPresenter extends MvpRxPresenter<CollectModel, CollectView> {

    public void getData(int type,int entityid) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable login = getModel().getData(type,entityid);
        getNetWork(login, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(CollectBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getCollectResult(mResponse);
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
