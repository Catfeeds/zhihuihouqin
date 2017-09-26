package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.BarberWorkListBean;
import com.moe.wl.ui.main.model.BarberPoductListModel;
import com.moe.wl.ui.main.view.BarberProductListView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberProductListPresenter extends MvpRxPresenter<BarberPoductListModel, BarberProductListView> {

    public void getData(String id,String page,String limit) {
        getView().showProgressDialog();
        LogUtil.log("BarberProductListPresenter发出请求");
        Observable login = getModel().getData(id,page,limit);
        getNetWork(login, new Subscriber<BarberWorkListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(BarberWorkListBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getBarberListSucc(listBean);
                }else{
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
