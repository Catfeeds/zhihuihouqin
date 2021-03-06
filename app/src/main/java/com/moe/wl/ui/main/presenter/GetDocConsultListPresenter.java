package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.DexpertnoticeBean;
import com.moe.wl.ui.main.bean.ExpertnoticelistBean;
import com.moe.wl.ui.main.model.GetDocConsultListModel;
import com.moe.wl.ui.main.view.GetDocConsultLisstView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class GetDocConsultListPresenter extends MvpRxPresenter<GetDocConsultListModel, GetDocConsultLisstView> {

    public void getConsultList(String s) {
        getView().showProgressDialog();
        Observable request = getModel().getConsultList(s);
        getNetWork(request, new Subscriber<ExpertnoticelistBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ExpertnoticelistBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getListResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void sendMess(int i,String s) {
        getView().showProgressDialog();
        Observable request = getModel().setMess(i,s);
        getNetWork(request, new Subscriber<DexpertnoticeBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(DexpertnoticeBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().sendResult(bean);
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
