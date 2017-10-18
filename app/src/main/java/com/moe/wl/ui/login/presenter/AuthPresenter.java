package com.moe.wl.ui.login.presenter;

import android.util.Log;

import com.moe.wl.ui.login.bean.Auth;
import com.moe.wl.ui.login.bean.CarInfo;
import com.moe.wl.ui.login.bean.SubmitAuthBean;
import com.moe.wl.ui.login.view.AuthView;
import com.moe.wl.ui.login.model.AuthModel;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class AuthPresenter extends MvpRxPresenter<AuthModel, AuthView> {


    public void getData(Auth auth,List<CarInfo> list) {
        LogUtil.log("MainPresenter发出请求");
        getView().showProgressDialog();
        getModel().submitAuth(auth,list)
                .subscribe(new Subscriber<SubmitAuthBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(SubmitAuthBean submitAuthBean) {
                if (submitAuthBean.getErrCode() == 0) {
                    getView().authSucc();
                } else if (submitAuthBean.getErrCode() == 1015) {
                    getView().showToast(submitAuthBean.getMsg());
                } else if (submitAuthBean.getErrCode() == 1016) {
                    getView().showToast(submitAuthBean.getMsg());
                } else if (submitAuthBean.getErrCode() == 1017) {
                    getView().showToast(submitAuthBean.getMsg());
                }else{
                    getView().showToast(submitAuthBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
