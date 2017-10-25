package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.BarberWorkListBean;
import com.moe.wl.ui.main.bean.InformationClazzBean;
import com.moe.wl.ui.main.bean.OfficeCollect;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.view.McNoticeView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticePresenter extends MvpRxPresenter<McNocticeModel, McNoticeView> {

    public void findUserFavorList(String type) {
        getView().showProgressDialog();
        Observable request = getModel().findUserFavorList(type);
       if(type.equals("1")) {
           getNetWork(request, new Subscriber<InformationClazzBean>() {
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
               public void onNext(InformationClazzBean mResponse) {
                   if (mResponse == null)
                       return;
                   if (mResponse.getErrCode() == 2) {
                       getView().reLogin(Constants.LOGIN_ERROR);
                       return;
                   }
                   if (mResponse.getErrCode() == 0) {
                       getView().getCollect1(mResponse.getNoticeTypeList());
                   } else {
                       getView().showToast(mResponse.getMsg());
                   }
               }
           });
       }else if(type.equals("2")) {
           getNetWork(request, new Subscriber<OfficeCollect>() {

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
               public void onNext(OfficeCollect mResponse) {
                   if (mResponse == null)
                       return;
                   if (mResponse.getErrCode() == 2) {
                       getView().reLogin(Constants.LOGIN_ERROR);
                       return;
                   }
                   if (mResponse.getErrCode() == 0) {
                        getView().getCollect2(mResponse.getList());
                   } else {
                       getView().showToast(mResponse.getMsg());
                   }
               }
           });
       }else if("3".equals(type)){//理发收藏
           getNetWork(request, new Subscriber<BarberWorkListBean>() {

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
               public void onNext(BarberWorkListBean mResponse) {
                   if (mResponse == null)
                       return;
                   if (mResponse.getErrCode() == 2) {
                       getView().reLogin(Constants.LOGIN_ERROR);
                       return;
                   }
                   if (mResponse.getErrCode() == 0) {
                       getView().getCollect3(mResponse.getWorklist());
                   } else {
                       getView().showToast(mResponse.getMsg());
                   }
               }
           });
       }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
