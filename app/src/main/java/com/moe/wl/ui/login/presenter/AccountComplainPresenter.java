package com.moe.wl.ui.login.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.login.model.AccountComplainModel;
import com.moe.wl.ui.login.view.AccountComplainView;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class AccountComplainPresenter extends MvpRxPresenter<AccountComplainModel, AccountComplainView> {

    public void getData(String userName, String password, String idcard, String mobile, String realname, String reason) {
        getView().showProgressDialog();
        getModel().submitAccountComplain(userName, password, idcard, mobile, realname, reason)
                .subscribe(new Subscriber<CollectBean>() {
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
                    public void onNext(CollectBean submitAuthBean) {
                        if (submitAuthBean==null)
                            return;
                        if (submitAuthBean.getErrCode()==2){
                            getView().reLogin(Constants.LOGIN_ERROR);
                            return;
                        }
                        if (submitAuthBean.getErrCode() == 0) {
                            getView().complainSucc();
                        } else {
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
