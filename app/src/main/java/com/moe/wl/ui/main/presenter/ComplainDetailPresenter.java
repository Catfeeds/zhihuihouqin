package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ComplainDetailBean;
import com.moe.wl.ui.main.model.ComplainDetailModel;
import com.moe.wl.ui.main.view.ComplainDetailView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ComplainDetailPresenter extends MvpRxPresenter<ComplainDetailModel, ComplainDetailView> {

    public void getComplainDetail(int id) {
        getView().showProgressDialog();
        Observable request = getModel().getComplainDetail(id);
        getNetWork(request, new Subscriber<ComplainDetailBean>() {

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
            public void onNext(ComplainDetailBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getComplainDetail(bean);
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
