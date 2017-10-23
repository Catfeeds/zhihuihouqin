package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.ExpertCommentBean;
import com.moe.wl.ui.main.model.ExpertCommentModel;
import com.moe.wl.ui.main.view.ExpertCommentView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 专家评论
 * Created by hh on 2017/5/12.
 */

public class ExpertCommentPresenter extends MvpRxPresenter<ExpertCommentModel, ExpertCommentView> {

    public void getExpertComment(int id, int page, int pageSize) {
        getView().showProgressDialog();
        Observable request = getModel().getExpertCommentList(id, page, pageSize);
        getNetWork(request, new Subscriber<ExpertCommentBean>() {
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
            public void onNext(ExpertCommentBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getExpertCommentListSucc(mResponse);
                } else {
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
