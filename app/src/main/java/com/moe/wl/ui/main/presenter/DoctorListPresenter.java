package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.DoctorListBean;
import com.moe.wl.ui.main.model.DoctorListModel;
import com.moe.wl.ui.main.view.DoctorListView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorListPresenter extends MvpRxPresenter<DoctorListModel, DoctorListView> {

    public void getData() {
        getView().showProgressDialog();
        Log.e("DoctorListPresenter","进行数据处理");
        Observable login = getModel().getData();
        getNetWork(login, new Subscriber<DoctorListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(DoctorListBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getDoctorListSucc(mResponse);
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
