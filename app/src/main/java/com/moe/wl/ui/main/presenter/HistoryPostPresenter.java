package com.moe.wl.ui.main.presenter;

import android.util.Log;

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
            public void onNext(HistoryPostBean bean) {
                LogUtils.i("bean===="+bean);
                if (bean.getErrCode() == 0) {
                    getView().getHistoryPostSucc(bean);
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
