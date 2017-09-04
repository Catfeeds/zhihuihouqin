package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.home.bean.LoginBean;
import cn.lc.model.ui.login.model.LoginModel;
import cn.lc.model.ui.login.view.LoginView;
import cn.lc.model.ui.main.bean.HealthServerceHomeBean;
import cn.lc.model.ui.main.model.HealthServerceModel;
import cn.lc.model.ui.main.view.HealthServerceView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public class HealthServercePresenter extends MvpRxPresenter<HealthServerceModel, HealthServerceView> {
    public void getData() {
        LogUtil.log("LoginPresenter发出请求");
        getModel().getData().subscribe(new Subscriber<HealthServerceHomeBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("异常信息为",e.getMessage());
            }

            @Override
            public void onNext(HealthServerceHomeBean hshBean) {
                if(hshBean.getErrCode()==0){
                    getView().success(hshBean);
                }else{
                    Log.e("错误信息1",hshBean.getMsg());
                    Log.e("错误信息2",hshBean.getErrCode()+"");
                }
            }
        });
    }
}