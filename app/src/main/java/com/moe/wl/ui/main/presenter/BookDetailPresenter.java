package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.BookDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.view.BookDetailView;
import com.moe.wl.ui.main.model.BookDetailModel;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class BookDetailPresenter extends MvpRxPresenter<BookDetailModel, BookDetailView> {

    public void getData(int type,int id) {
        getView().showProgressDialog();
        Log.e("BookDetailPresenter","发出请求");
        Observable login = getModel().getData(type,id);
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
            public void onNext(CollectBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().collectSucc(listBean);
                }else{
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
