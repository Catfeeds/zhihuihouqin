package com.moe.wl.ui.home.presenter;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.model.AffirmOrderModel;
import com.moe.wl.ui.home.view.AffirmOrderView;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AffirmOrderPresenter extends MvpRxPresenter<AffirmOrderModel, AffirmOrderView> {

    public void orderinfo() {
        getView().showProgressDialog();
        Observable request = getModel().orderinfo();
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("接口请求错误："+e);
                getView().setData();
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().setData();
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
