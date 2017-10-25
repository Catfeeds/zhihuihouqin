package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.MedicalDetailBean;
import com.moe.wl.ui.main.model.MedicalDetailModel;
import com.moe.wl.ui.main.view.CollectView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class CollectPresenter extends MvpRxPresenter<MedicalDetailModel, CollectView> {

    public void getData(int type, int entityid) {
        getView().showProgressDialog();
        Observable login = getModel().getData(type, entityid);
        getNetWork(login, new Subscriber<CollectBean>() {
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
            public void onNext(CollectBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getCollectResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    public void submitComment(int id, String content) {
        getView().showProgressDialog();
        Observable login = getModel().submitComment(id, content);
        getNetWork(login, new Subscriber<CollectBean>() {
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
            public void onNext(CollectBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().submitCommentSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    // 获取详情
    public void getDetail(int id) {
        getView().showProgressDialog();
        Observable login = getModel().getDetail(id);
        getNetWork(login, new Subscriber<MedicalDetailBean>() {
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
            public void onNext(MedicalDetailBean mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.getErrCode() == 2) {
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getDetailSucc(mResponse);
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
