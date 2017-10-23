package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.ChangePayPwdModel;
import com.moe.wl.ui.main.view.ChangePayPwdView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ChangePayPwdPresenter extends MvpRxPresenter<ChangePayPwdModel, ChangePayPwdView> {

    public void modifyCode(String pwd) {
        getView().showProgressDialog();
        Observable request = getModel().getData(pwd);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

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
            public void onNext(ActivityPostBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().modifyCodeResult(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void checkOldPassword(String pwd) {
        getView().showProgressDialog();
        Observable request = getModel().checkOldPassword(pwd);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

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
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().checkOldPasswordResult(bean);
                } else {
                    getView().showToast("你输入的旧支付密码不正确,请重新输入");
                }
            }
        });
    }
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
