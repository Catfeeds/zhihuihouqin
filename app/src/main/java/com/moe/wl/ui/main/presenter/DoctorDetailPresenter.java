package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.DoctorDetailBean;
import com.moe.wl.ui.main.bean.UserCommentBean;
import com.moe.wl.ui.main.model.DoctorDetailModel;
import com.moe.wl.ui.main.view.DoctorDetailView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorDetailPresenter extends MvpRxPresenter<DoctorDetailModel, DoctorDetailView> {

    public void getData(int id) {
        getView().showProgressDialog();
        Log.e("DoctorDetailPresenter","进行数据处理");
        Observable login = getModel().getData(id);
        getNetWork(login, new Subscriber<DoctorDetailBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(DoctorDetailBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getDoctorDetailSucc(mResponse);
                }else{
                    getView().showToast(mResponse.getMsg());
                    Log.e("errcode",mResponse.getErrCode()+"");
                    Log.e("msg",mResponse.getMsg()+"");
                }
            }
        });
    }
    public void getUserComment(int id,int page,int limit) {
        getView().showProgressDialog();
        Log.e("DoctorDetailPresenter","进行数据处理");
        Observable login = getModel().getCommentList(id,page,limit);
        getNetWork(login, new Subscriber<UserCommentBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(UserCommentBean userCommentBean) {
                if(userCommentBean.getErrCode()==0){
                    getView().getUserCommentListSucc(userCommentBean);
                }else{
                    getView().showToast(userCommentBean.getMsg());
                    Log.e("errcode",userCommentBean.getErrCode()+"");
                    Log.e("msg",userCommentBean.getMsg()+"");
                }
            }
        });
    }
    public void doCollect(int type,int typeid) {
        getView().showProgressDialog();
        Log.e("DoctorDetailPresenter","进行数据处理");
        Observable login = getModel().collect(type,typeid);
        getNetWork(login, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(CollectBean collectBean) {
                if(collectBean.getErrCode()==0){
                    getView().getCollectResult(collectBean);
                }else{
                    Log.e("errcode",collectBean.getErrCode()+"");
                    Log.e("msg",collectBean.getMsg()+"");
                }
            }
        });
    }
    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
