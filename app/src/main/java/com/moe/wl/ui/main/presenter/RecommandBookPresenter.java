package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.RecommandBookBean;
import com.moe.wl.ui.main.model.RecommandBookModel;
import com.moe.wl.ui.main.view.RecommandBookView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RecommandBookPresenter extends MvpRxPresenter<RecommandBookModel, RecommandBookView> {

    public void getData(String title,String author,String publisher,String remark) {
        getView().showProgressDialog();
        Log.e("RecommandBookPresenter","发出请求");
        Observable login = getModel().getData(title,author,publisher,remark);
        getNetWork(login, new Subscriber<RecommandBookBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(RecommandBookBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().getRecommandResult(mResponse);
                }else{
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
