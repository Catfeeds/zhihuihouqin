package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.HistoryPostBean;
import com.moe.wl.ui.main.model.HistoryPostModel;
import com.moe.wl.ui.main.view.HistoryPostView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class HistoryPostPresenter extends MvpRxPresenter<HistoryPostModel, HistoryPostView> {

    public void getHistoryPostInfo(String page,String limit) {
        getView().showProgressDialog();
        Observable request = getModel().getData(page,limit);
        getNetWork(request, new Subscriber<HistoryPostBean>() {

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
            public void onNext(HistoryPostBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                LogUtils.i("bean===="+mResponse);
                if (mResponse.getErrCode() == 0) {
                    getView().getHistoryPostSucc(mResponse);
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
