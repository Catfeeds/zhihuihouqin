package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ComplainHistoryBean;
import com.moe.wl.ui.main.model.ComplainHistoryModel;
import com.moe.wl.ui.main.view.ComplainHistoryView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class ComplainHistoryPresenter extends MvpRxPresenter<ComplainHistoryModel, ComplainHistoryView> {

    public void getComplainHistoryData(int page, int pageCount) {
//        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable request = getModel().getComplainHistory(page, pageCount);
        getNetWork(request, new Subscriber<ComplainHistoryBean>() {
            @Override
            public void onCompleted() {
//                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ComplainHistoryBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getComplainHistorySucc(mResponse);
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
