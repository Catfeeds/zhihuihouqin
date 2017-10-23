package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.DryToCommentModel;
import com.moe.wl.ui.main.view.DryToCommentView;

import java.io.File;

import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DryToCommentPresenter extends MvpRxPresenter<DryToCommentModel, DryToCommentView> {

    public void getData(int oid, double stars, String content, String isAnonymous, File imgFile) {
        getView().showProgressDialog();
        LogUtil.log("DryToCommentPresenter发出请求");
        Observable login = getModel().dryToComment(oid,stars,content,isAnonymous,imgFile);
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
            public void onNext(CollectBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                    getView().commentSucc(mResponse);
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
