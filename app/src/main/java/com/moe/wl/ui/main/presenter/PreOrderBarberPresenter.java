package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.model.PreOderBarberModel;
import com.moe.wl.ui.main.bean.PreOrderBean;
import com.moe.wl.ui.main.view.PreOrderBarberView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class PreOrderBarberPresenter extends MvpRxPresenter<PreOderBarberModel, PreOrderBarberView> {

    public void getData(int id) {
        getView().showProgressDialog();
        LogUtil.log("PreOrderBarberPresenter发出请求");
        Observable login = getModel().getData(id);
        getNetWork(login, new Subscriber<PreOrderBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(PreOrderBean preOrderBean) {
                if(preOrderBean.getErrCode()==0){
                    getView().getBarberInfo(preOrderBean);
                }else{
                    getView().showToast(preOrderBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
