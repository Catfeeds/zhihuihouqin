package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.UpLoadHeaderBean;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.main.model.UserInfoModel;
import com.moe.wl.ui.main.view.UserInfoView;

import java.io.File;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class UserInfoPresenter extends MvpRxPresenter<UserInfoModel, UserInfoView> {

    public void getUserInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getUserInfo();
        getNetWork(request, new Subscriber<UserInfoBean>() {

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
            public void onNext(UserInfoBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getUserInfoSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }
    public void changeUserInfo(int sex,String nickname,String photo,int positionid,
                               String tel,String roomnum,String mobile,String buildNum) {
        getView().showProgressDialog();
        Observable request = getModel().changeUserInfo(sex,nickname,photo,positionid,tel,roomnum,mobile,
                buildNum);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

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
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getChangeResult(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }
    public void upLoadHeader(File file) {
        getView().showProgressDialog();
        Observable request = getModel().upLoadHeader(file);
        getNetWork(request, new Subscriber<UpLoadHeaderBean>() {

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
            public void onNext(UpLoadHeaderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().upLoadHeaderResult(bean);
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
