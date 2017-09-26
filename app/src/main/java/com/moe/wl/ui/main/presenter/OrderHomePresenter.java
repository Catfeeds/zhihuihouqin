package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.QueryWaterTypeBean;
import com.moe.wl.ui.main.model.OrderHomeModel;
import com.moe.wl.ui.main.view.OrderHomeView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderHomePresenter extends MvpRxPresenter<OrderHomeModel, OrderHomeView> {

    public void queryWaterType() {
        getView().showProgressDialog();
        Observable request = getModel().getWaterType();
        getNetWork(request, new Subscriber<QueryWaterTypeBean>() {

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
            public void onNext(QueryWaterTypeBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().queryTypeSucc(bean);
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
