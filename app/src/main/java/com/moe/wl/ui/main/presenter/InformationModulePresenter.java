package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.InformationClazzBean;
import com.moe.wl.ui.main.model.InformationModuleModel;
import com.moe.wl.ui.main.view.InformationModuleView;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/13 0013
 */

public class InformationModulePresenter extends MvpRxPresenter<InformationModuleModel, InformationModuleView> {

    // 获取我的信息公告模块
    public void getInformationClass(int user) {
        getView().showProgressDialog();
        Observable request = getModel().getInformationClass(user);
        getNetWork(request, new Subscriber<InformationClazzBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(InformationClazzBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getInformationClassSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    //  获取所有信息公告模块
    public void getAllInformationClass(int user) {
        getView().showProgressDialog();
        Observable request = getModel().getInformationClass(user);
        getNetWork(request, new Subscriber<InformationClazzBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(InformationClazzBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getAllInformationClassSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    // 修改信息公告模块
    public void updataInformationModule(int[] ids) {
        getView().showProgressDialog();
        Observable request = getModel().updataInformationClass(ids);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().updataInformationClassSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
