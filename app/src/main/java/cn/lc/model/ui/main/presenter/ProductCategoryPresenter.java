package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.ProductCategoryBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.ProductCategoryModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.ProductCategoryView;
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
            public void onNext(ProductCategoryBean bean) {
                LogUtils.i("获取bean"+bean.getErrCode());
                if (bean.getErrCode() == 0) {
                    getView().getSpCategory(bean);
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
