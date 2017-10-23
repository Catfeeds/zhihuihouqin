package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.LibraryHomeBean;
import com.moe.wl.ui.main.model.LibraryHomeModel;
import com.moe.wl.ui.main.view.LibraryHomeView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class LibraryHomePresenter extends MvpRxPresenter<LibraryHomeModel, LibraryHomeView> {

    public void getData(int position) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable login = getModel().getData(position);
        getNetWork(login, new Subscriber<LibraryHomeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(LibraryHomeBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getLibraryHomeSucc(mResponse);
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
