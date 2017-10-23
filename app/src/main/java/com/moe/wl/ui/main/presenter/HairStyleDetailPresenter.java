package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.BarberProductDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.HairStyleDetailModel;
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
            public void onNext(BarberProductDetailBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getDataSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
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
