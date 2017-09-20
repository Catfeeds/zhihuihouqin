package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.ShopCarModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.ShopCarView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ShopCarPresenter extends MvpRxPresenter<ShopCarModel, ShopCarView> {

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
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().shopCar(bean);
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
