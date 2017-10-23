package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ProductCategoryBean;
import com.moe.wl.ui.main.model.ProductCategoryModel;
import com.moe.wl.ui.main.view.ProductCategoryView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ProductCategoryPresenter extends MvpRxPresenter<ProductCategoryModel, ProductCategoryView> {

    public void getSpCategory(String s1,String s2,String s3) {
        getView().showProgressDialog();
        Observable request = getModel().getSpCategory(s1,s2,s3);
        getNetWork(request, new Subscriber<ProductCategoryBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ProductCategoryBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                LogUtils.i("获取bean"+mResponse.getErrCode());
                if (mResponse.getErrCode() == 0) {
                    getView().getSpCategory(mResponse);
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
