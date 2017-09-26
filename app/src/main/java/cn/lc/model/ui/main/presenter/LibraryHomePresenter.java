package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.model.LibraryHomeModel;
import cn.lc.model.ui.main.view.LibraryHomeView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class LibraryHomePresenter extends MvpRxPresenter<LibraryHomeModel, LibraryHomeView> {

    public void getData(int position) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable login = getModel().getData(position);
        getNetWork(login, new Subscriber<LibraryHomeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(LibraryHomeBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getLibraryHomeSucc(listBean);
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
