package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.McNoticeListResponse;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.view.McNoticeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticePresenter extends MvpRxPresenter<McNocticeModel, McNoticeView> {

    public void findUserFavorList(String type) {
        getView().showProgressDialog();
        Observable request = getModel().findUserFavorList(type);
        getNetWork(request, new Subscriber<McNoticeListResponse>() {

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
            public void onNext(McNoticeListResponse mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.errCode==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.errCode == 0) {
                    getView().getCollect(mResponse.getPage().getList());
                } else {
                    getView().showToast(mResponse.msg);
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
