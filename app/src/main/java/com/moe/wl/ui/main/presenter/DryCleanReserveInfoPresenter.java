package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ClothBean;
import com.moe.wl.ui.main.bean.JieYueBean;
import com.moe.wl.ui.main.model.DryCleanReserveInfoModel;
import com.moe.wl.ui.main.view.DryCleanReserveInfoView;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DryCleanReserveInfoPresenter extends MvpRxPresenter<DryCleanReserveInfoModel, DryCleanReserveInfoView> {

    public void getData(String page, String limit) {
        getView().showProgressDialog();
        LogUtil.log("DryCleanReserveInfoPresenter发出请求");
        Observable login = getModel().getData(page, limit);
        getNetWork(login, new Subscriber<ClothBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ClothBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().OrderDryCleaner(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });

    }

    public void getCommitResult(String mobile,double sum, String expectarrivaItme,
                                String clothList) {
        Observable observable = getModel().CommitData(mobile,sum,expectarrivaItme, clothList);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<JieYueBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交干洗订单出现问题");
            }

            @Override
            public void onNext(JieYueBean o) {
                if(o.getErrCode()==0){
                    getView().commitSucc(o);
                }else{
                    getView().showToast(o.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
