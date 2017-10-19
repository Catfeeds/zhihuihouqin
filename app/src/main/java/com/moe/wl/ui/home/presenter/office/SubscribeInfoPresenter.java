package com.moe.wl.ui.home.presenter.office;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.office.SubscribeInfoResponse;
import com.moe.wl.ui.home.model.office.SubscribeInfoModel;
import com.moe.wl.ui.home.view.office.SubscribeInfoView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SubscribeInfoPresenter extends MvpRxPresenter<SubscribeInfoModel, SubscribeInfoView> {

    public void subscribeInfo(String id) {
        getView().showProgressDialog();
        Observable request = getModel().subscribeInfo(id);
        getNetWork(request, new Subscriber<SubscribeInfoResponse>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().showProgressDialog();
                LogUtils.d("接口请求错误："+e);
            }

            @Override
            public void onNext(SubscribeInfoResponse mResponse) {
                if (mResponse==null){
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().setData(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
