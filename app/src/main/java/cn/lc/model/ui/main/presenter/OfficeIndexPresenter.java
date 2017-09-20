package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.OfficeIndexBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.OfficeIndexModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.OfficeIndexView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class OfficeIndexPresenter extends MvpRxPresenter<OfficeIndexModel, OfficeIndexView> {

    public void getIndexInfo() {
        getView().showProgressDialog();
        Observable request = getModel().getIndex();
        getNetWork(request, new Subscriber<OfficeIndexBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(OfficeIndexBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getIndexInfo(bean);
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
