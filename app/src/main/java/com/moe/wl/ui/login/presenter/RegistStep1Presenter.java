package com.moe.wl.ui.login.presenter;

import android.util.Log;

import com.moe.wl.framework.widget.bean.BindPhoneBean;
import com.moe.wl.ui.login.bean.CaptchaBean;
import com.moe.wl.ui.login.model.RegistStep1Model;
import com.moe.wl.ui.login.view.RegistStep1View;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep1Presenter extends MvpRxPresenter<RegistStep1Model, RegistStep1View> {


    public void getData(String s1, int i) {
        LogUtil.log("MainPresenter发出请求");
        getModel().getCaptcha(s1, i).subscribe(new Subscriber<CaptchaBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(CaptchaBean captchaBean) {
                if (captchaBean.errCode == 0) {
                    //成功
                    getView().showToast("请求成功");
                    getView().success(captchaBean);
                } else if (captchaBean.errCode == 2) {
                } else {
                    getView().showToast(captchaBean.msg);
                }
            }
        });
    }

    public void bindPhone(String loginType, String userName, String thirdNum, String isRegister, String password, String captcha) {
        getView().showProgressDialog();
        getModel().bindPhone(loginType, userName, thirdNum, isRegister, password, captcha).subscribe(new Subscriber<BindPhoneBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(BindPhoneBean bindPhoneBean) {
                if (bindPhoneBean.getErrCode() == 0) {
                    getView().bindResult(bindPhoneBean);
                } else {
                    getView().showToast(bindPhoneBean.getMsg());
                }
            }
        });
    }

    /* public void CheckCode(String mobile,String code) {
         // getView().showProgressDialog();
         getModel(). checkCode(mobile,code).subscribe(new Subscriber<RegisterResponse>() {
             @Override
             public void onCompleted() {
                 //   getView().dismissProgressDialog();
                 LogUtils.d("验证成功-----------");
             }

             @Override
             public void onError(Throwable e) {
                 LogUtils.d("验证失敗----------" + e.toString());
             }

             @Override
             public void onNext(RegisterResponse mResponse) {
                 LogUtils.d("code-------------------------"+mResponse.errCode);
                 LogUtils.d("msg-------------------------"+mResponse.msg);
                 if (mResponse == null)
                     return;
                 if (mResponse.errCode == 0) {
                     getView().CheckCodeSucc(mResponse);
                 } else {
                     getView().showToast(mResponse.msg);
                 }

             }
         });
     }*/
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
