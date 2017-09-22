package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.SpOrderBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.SpOrderModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.SpOrderView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpOrderPresenter extends MvpRxPresenter<SpOrderModel, SpOrderView> {

    public void getOrder(String addressid,String expectedTime,String remark,String productList,
                           String skuid,String count) {
        getView().showProgressDialog();
        Observable request = getModel().getData(addressid,expectedTime,remark,productList,skuid,count);
        getNetWork(request, new Subscriber<SpOrderBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(SpOrderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getOrderInfoSucc(bean);
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
