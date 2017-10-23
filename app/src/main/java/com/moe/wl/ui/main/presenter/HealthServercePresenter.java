package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.bean.HealthServerceHomeBean;
import com.moe.wl.ui.main.model.HealthServerceModel;
import com.moe.wl.ui.main.view.HealthServerceView;

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
            public void onNext(HealthServerceHomeBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if(mResponse.getErrCode()==0){
                   // List<InfolistBean> infolist = hshBean.getInfolist();
                    getView().success(mResponse);
                }else{
                    Log.e("错误信息1",mResponse.getMsg());
                    Log.e("错误信息2",mResponse.getErrCode()+"");
                }
            }
        });
    }
}