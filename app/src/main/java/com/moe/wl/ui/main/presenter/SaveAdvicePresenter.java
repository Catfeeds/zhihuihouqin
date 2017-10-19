package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.SaveAdviceModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.SaveAdviceView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SaveAdvicePresenter extends MvpRxPresenter<SaveAdviceModel, SaveAdviceView> {

    public void saveAdvice(String content) {
        getView().showProgressDialog();
        Observable request = getModel().saveAdvice(content);
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
                    getView().saveAdviceResult(bean);
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
