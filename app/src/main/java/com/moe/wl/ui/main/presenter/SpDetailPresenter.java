package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ShopCarInfoBean;
import com.moe.wl.ui.main.bean.SpDetailBean;
import com.moe.wl.ui.main.model.SpDetailModel;
import com.moe.wl.ui.main.view.SpDetailView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpDetailPresenter extends MvpRxPresenter<SpDetailModel, SpDetailView> {

    public void getSpDetail(String id) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id);
        getNetWork(request, new Subscriber<SpDetailBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(SpDetailBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                if (mResponse.getErrCode() == 0) {
                    getView().getSpDetailSucc(mResponse);
                } else {
                    getView().showToast(mResponse.getMsg());
                }
            }
        });
    }

    public void getCollectInfo(int i, int j) {
        Observable observable = RetrofitUtils.getInstance().getHealthInfoColect(i, j);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("出现异常" + e.getMessage());
            }

            @Override
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getCollectResult(bean);
                } else {
                    LogUtils.i("错误信息" + bean.getMsg());
                }
            }
        });
    }

    public void getShopCarInfo(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpShopCarInfo(id);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<ShopCarInfoBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i(e.getMessage());
            }

            @Override
            public void onNext(ShopCarInfoBean bean) {
                if(bean.getErrCode()==0){
                    getView().getShopCarInfo(bean);
                }else{
                    LogUtils.i(bean.getMsg());
                }
            }
        });
    }
    public void shopCar(String s,String count) {
        getView().showProgressDialog();
        Observable request = getModel().shopCar(s,count);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getShopCar(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
