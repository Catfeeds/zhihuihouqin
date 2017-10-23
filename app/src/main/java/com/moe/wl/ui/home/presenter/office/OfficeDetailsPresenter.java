package com.moe.wl.ui.home.presenter.office;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.bean.office.OfficeDetailsResponse;
import com.moe.wl.ui.home.model.office.OfficeDetailsModel;
import com.moe.wl.ui.home.view.office.OfficeDetailsView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeDetailsPresenter extends MvpRxPresenter<OfficeDetailsModel, OfficeDetailsView> {

    public void officedetails(String id) {
        getView().showProgressDialog();
        Observable request = getModel().officedetails(id);
        getNetWork(request, new Subscriber<OfficeDetailsResponse>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("接口请求错误："+e);
            }

            @Override
            public void onNext(OfficeDetailsResponse mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.errCode==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse==null)
                    return;
                if (mResponse.errCode == 0) {
                    getView().setData(mResponse.getRoomDetail());
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
