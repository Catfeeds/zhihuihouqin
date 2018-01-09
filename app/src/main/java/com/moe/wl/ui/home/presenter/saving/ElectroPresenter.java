package com.moe.wl.ui.home.presenter.saving;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.EnergyCostQueryBean;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;
import com.moe.wl.ui.home.model.saving.ElectroModel;
import com.moe.wl.ui.home.model.saving.InformationDetailModel;
import com.moe.wl.ui.home.view.saving.ElectroView;
import com.moe.wl.ui.home.view.saving.InformationDetailView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;


public class ElectroPresenter extends MvpRxPresenter<ElectroModel, ElectroView> {

    public void getDetail(String time,int dateType,int energyType ,int bid) {
        getView().showProgressDialog();
        Observable request = getModel().getInfo(time,dateType,energyType,bid);
        getNetWork(request, new Subscriber<EnergyCostQueryBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.d("接口请求错误："+e);
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(EnergyCostQueryBean bean) {
                if (bean==null)
                    return;
                if (bean.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (bean.getErrCode() == 0) {
                    getView().getInfo(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
