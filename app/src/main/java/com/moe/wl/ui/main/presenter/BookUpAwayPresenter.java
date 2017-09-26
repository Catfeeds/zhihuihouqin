package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.ui.main.bean.SearchBookListBean;
import com.moe.wl.ui.main.model.BookUpAwayModel;
import com.moe.wl.ui.main.view.BookUpAwayView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class BookUpAwayPresenter extends MvpRxPresenter<BookUpAwayModel, BookUpAwayView> {


    public void getData(String s,String s1,String s2) {
        Log.e("BookUpAwayPresenter","处理数据");
        getView().showProgressDialog();
        getModel().getBookList(s,s1,s2).subscribe(new Subscriber<SearchBookListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(SearchBookListBean booklistBean) {
                if(booklistBean.getErrCode()==0){
                    getView().getBookListSucc(booklistBean);
                }else{
                    Log.e("SearchBookListBean",booklistBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
