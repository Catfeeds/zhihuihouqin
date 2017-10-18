package com.moe.wl.ui.home.presenter.office;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.office.SubscribeTimeResponse;
import com.moe.wl.ui.home.model.office.SubscribeTimeModel;
import com.moe.wl.ui.home.view.office.SubscribeTimeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SubscribeTimePresenter extends MvpRxPresenter<SubscribeTimeModel, SubscribeTimeView> {

    public void findAvailableEquipment(String roomid,String date) {
        getView().showProgressDialog();
        Observable request = getModel().findAvailableEquipment(roomid,date);
        getNetWork(request, new Subscriber<SubscribeTimeResponse>() {

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
            public void onNext(SubscribeTimeResponse mResponse) {
                if (mResponse.getErrCode() == 0) {
                    getView().setData(mResponse.getAppointmentList());
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
