package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.DepartsListBean;
import com.moe.wl.ui.main.bean.NationslistBean;
import com.moe.wl.ui.main.model.DepartMentModel;
import com.moe.wl.ui.main.model.NationsModel;
import com.moe.wl.ui.main.view.DepartMentView;
import com.moe.wl.ui.main.view.NationsView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class DepartMentPresenter extends MvpRxPresenter<DepartMentModel, DepartMentView> {

    public void getDepartList() {
        getView().showProgressDialog();
        Observable request = getModel().getDepartList();
        getNetWork(request, new Subscriber<DepartsListBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(DepartsListBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getDepartResult(bean);
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
