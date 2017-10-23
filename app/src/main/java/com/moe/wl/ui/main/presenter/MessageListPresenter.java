package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.MessageListBean;
import com.moe.wl.ui.main.model.MessageListModel;
import com.moe.wl.ui.main.view.MessageListView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MessageListPresenter extends MvpRxPresenter<MessageListModel, MessageListView> {


    public void getMessageList(int messageType, int page) {
        Observable request = getModel().getMessageListSucc(messageType, page);
        getNetWork(request, new Subscriber<MessageListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(MessageListBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getMessageListSucc(mResponse);
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
