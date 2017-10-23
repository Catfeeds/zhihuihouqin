package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.InformationBean;
import com.moe.wl.ui.main.model.InformationModel;
import com.moe.wl.ui.main.view.InformationView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationPresenter extends MvpRxPresenter<InformationModel, InformationView> {

    public void getInformation(int typeId, int isRecommend, String keyword, int page) {
//        getView().showProgressDialog();
        Observable request = getModel().getInformationData(typeId, isRecommend, keyword, page);
        getNetWork(request, new Subscriber<InformationBean>() {

            @Override
            public void onCompleted() {
//                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(InformationBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (getView() == null) {
                    LogUtils.d("返回了" + "View为空！");
                    onCompleted();
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getInformationSucc(mResponse);
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
