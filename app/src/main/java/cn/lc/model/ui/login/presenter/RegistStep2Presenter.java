package cn.lc.model.ui.login.presenter;

import cn.lc.model.ui.login.model.RegistStep2Model;
import cn.lc.model.ui.login.view.RegistStep2View;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2Presenter extends MvpRxPresenter<RegistStep2Model, RegistStep2View> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        getModel().login("", "").subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object loginBean) {

            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
