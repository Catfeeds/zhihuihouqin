package com.moe.wl.ui.login.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.login.bean.PositionListBean;
import com.moe.wl.ui.login.model.PositionListModel;
import com.moe.wl.ui.login.view.PositionListView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class PositionListPresenter extends MvpRxPresenter<PositionListModel, PositionListView> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        getView().showProgressDialog();
        getModel().getPositionList().subscribe(new Subscriber<PositionListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(PositionListBean positionListBean) {
                if (positionListBean==null)
                    return;
                if (positionListBean.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (positionListBean.getErrCode() == 0) {
                    getView().getListSucc(positionListBean);
                } else {
                    getView().showToast(positionListBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
