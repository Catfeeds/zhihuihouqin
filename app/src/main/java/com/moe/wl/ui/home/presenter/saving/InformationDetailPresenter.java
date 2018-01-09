package com.moe.wl.ui.home.presenter.saving;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;
import com.moe.wl.ui.home.model.saving.InformationDetailModel;
import com.moe.wl.ui.home.model.saving.StatisticsModel;
import com.moe.wl.ui.home.view.saving.InformationDetailView;
import com.moe.wl.ui.home.view.saving.StatisticsView;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.InformationDetailBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;


public class InformationDetailPresenter extends MvpRxPresenter<InformationDetailModel, InformationDetailView> {

    public void getDetail(int id) {
        getView().showProgressDialog();
        Observable request = getModel().informationDetail(id);
        getNetWork(request, new Subscriber<InforMationDetailBean>() {

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
            public void onNext(InforMationDetailBean bean) {
                if (bean==null)
                    return;
                if (bean.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (bean.getErrCode() == 0) {
                    getView().getDetail(bean);
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
