package cn.lc.model.ui.login.presenter;

import android.util.Log;

import cn.lc.model.ui.login.bean.RegistBean;
import cn.lc.model.ui.login.model.RegistStep2Model;
import cn.lc.model.ui.login.view.RegistStep2View;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2Presenter extends MvpRxPresenter<RegistStep2Model, RegistStep2View> {

    public void getData(String userName, String captcha, String password) {
        LogUtil.log("RegistStep2发出请求");
        getView().showProgressDialog();
        getModel().regist(userName, captcha, password).subscribe(new Subscriber<RegistBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(RegistBean registBean) {
                if (registBean == null) {
                    return;
                }
                if(registBean.getErrCode()==0){
                    getView().registSuccess(registBean);
                } else{
                    getView().showToast(registBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
