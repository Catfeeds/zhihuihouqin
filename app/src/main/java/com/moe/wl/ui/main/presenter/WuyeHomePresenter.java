package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.RepairItmeBean;
import com.moe.wl.ui.main.model.WuyeHomeModel;
import com.moe.wl.ui.main.view.WuyeHomeView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class WuyeHomePresenter extends MvpRxPresenter<WuyeHomeModel, WuyeHomeView> {

    public void getData(int menditem, String username, String mobile,
                        String invitetime, String serviceplace, String mendcontent, List<String> files) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        files.remove(files.size() - 1);
        Observable login = getModel().getData(menditem, username, mobile, invitetime, serviceplace, mendcontent, files);
        getNetWork(login, new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getWuyeHomeResult(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    public void getRepairItem() {
        getView().showProgressDialog();
        Observable login = getModel().getRepairItem();
        getNetWork(login, new Subscriber<RepairItmeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(RepairItmeBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getRepairItemSucc(bean);
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
