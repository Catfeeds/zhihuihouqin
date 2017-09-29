package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.BarberProductDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.HairStyleDetailModel;
import com.moe.wl.ui.main.view.AddressView;
import com.moe.wl.ui.main.view.HairStyleDetailView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HairStyleDetailPresenter extends MvpRxPresenter<HairStyleDetailModel,HairStyleDetailView> {

    public void getdata(int workid) {
        getView().showProgressDialog();
        Observable request = getModel().getData(workid);
        getNetWork(request, new Subscriber<BarberProductDetailBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BarberProductDetailBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getDataSucc(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void collect(int type,int i) {
        getView().showProgressDialog();
        Observable request = getModel().collect(type,i);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().collectSucc(bean);
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
