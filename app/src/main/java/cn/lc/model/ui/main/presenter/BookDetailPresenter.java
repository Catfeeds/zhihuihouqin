package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BookDetailBean;
import cn.lc.model.ui.main.model.BarberDetailModel;
import cn.lc.model.ui.main.model.BookDetailModel;
import cn.lc.model.ui.main.view.BarberDetailView;
import cn.lc.model.ui.main.view.BookDetailView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class BookDetailPresenter extends MvpRxPresenter<BookDetailModel, BookDetailView> {

    public void getData(int id) {
        getView().showProgressDialog();
        Log.e("BookDetailPresenter","发出请求");
        Observable login = getModel().getData(id);
        getNetWork(login, new Subscriber<BookDetailBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(BookDetailBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getBookDetailSucc(listBean);
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
