package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.MyCollectBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.McNoticeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticePresenter extends MvpRxPresenter<McNocticeModel, McNoticeView> {

    public void getCollect(int type) {
        getView().showProgressDialog();
        Observable request = getModel().getData(type);
        getNetWork(request, new Subscriber<MyCollectBean>() {

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
            public void onNext(MyCollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getCollect(bean);
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
