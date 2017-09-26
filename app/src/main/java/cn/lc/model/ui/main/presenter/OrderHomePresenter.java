package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.QueryWaterTypeBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.OrderHomeModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.OrderHomeView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OrderHomePresenter extends MvpRxPresenter<OrderHomeModel, OrderHomeView> {

    public void queryWaterType() {
        getView().showProgressDialog();
        Observable request = getModel().getWaterType();
        getNetWork(request, new Subscriber<QueryWaterTypeBean>() {

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
            public void onNext(QueryWaterTypeBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().queryTypeSucc(bean);
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
