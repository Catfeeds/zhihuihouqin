package com.moe.wl.ui.main.presenter;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.main.model.Tab4Model;
import com.moe.wl.ui.main.view.Tab4View;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab4Presenter extends MvpRxPresenter<Tab4Model, Tab4View> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        getModel().getData().subscribe(new Subscriber<UserInfoBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.d(e.getMessage());
            }

            @Override
            public void onNext(UserInfoBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getUserInfo(bean);
                } else if(bean.getErrCode() == 2){
                    getView().reLogin(Constants.LOGIN_ERROR);
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
