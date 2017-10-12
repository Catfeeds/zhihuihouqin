package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.HasPwdModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.HasPwdView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HasPwdPresenter extends MvpRxPresenter<HasPwdModel, HasPwdView> {

    public void getIsHasPwd() {
        getView().showProgressDialog();
        Observable request = getModel().getData();
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
                    getView().isHasPwd(bean);
                } else if(bean.getErrCode()==1001){
                    getView().isHasPwd(bean);
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
