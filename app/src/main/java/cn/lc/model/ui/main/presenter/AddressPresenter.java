package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.view.AddressView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddressPresenter extends MvpRxPresenter<AddressModel, AddressView> {

    public void getAddress() {
        getView().showProgressDialog();
        Observable request = getModel().getAddress();
        getNetWork(request, new Subscriber<AddressBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(AddressBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().addressData(bean);
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
