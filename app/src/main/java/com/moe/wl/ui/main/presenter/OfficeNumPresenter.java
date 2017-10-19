package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.DepartsListBean;
import com.moe.wl.ui.main.bean.OfficeslistBean;
import com.moe.wl.ui.main.model.DepartMentModel;
import com.moe.wl.ui.main.model.OfficeNumModel;
import com.moe.wl.ui.main.view.DepartMentView;
import com.moe.wl.ui.main.view.OfficeNumView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeNumPresenter extends MvpRxPresenter<OfficeNumModel, OfficeNumView> {

    public void getDepartList() {
        getView().showProgressDialog();
        Observable request = getModel().getOfficeList();
        getNetWork(request, new Subscriber<OfficeslistBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(OfficeslistBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getOfficeNumResult(bean);
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
