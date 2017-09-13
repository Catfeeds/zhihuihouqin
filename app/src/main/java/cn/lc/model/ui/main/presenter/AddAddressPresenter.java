package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.model.AddAddressModel;
import cn.lc.model.ui.main.view.AddAddressView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddAddressPresenter extends MvpRxPresenter<AddAddressModel, AddAddressView> {

    public void addAddress(String realname, String mobile, String address) {
        getView().showProgressDialog();
        Observable request = getModel().addAddress(realname, mobile, address);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().addAddressSucc();
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    public void editAddress(int id, String realname, String mobile, String address) {
        getView().showProgressDialog();
        Observable request = getModel().editAddress(id, realname, mobile, address);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CollectBean listBean) {
                LogUtils.d("编辑地址", listBean.getErrCode() + "  " + listBean.getMsg());
                if (listBean.getErrCode() == 0) {
                    getView().editAddressSucc();
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    public void deleteAddress(int[] id) {
        getView().showProgressDialog();
        Observable request = getModel().deleteAddress(id);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CollectBean listBean) {
                LogUtils.d("删除地址", listBean.getErrCode() + "  " + listBean.getMsg());
                if (listBean.getErrCode() == 0) {
                    getView().deleteAddressSucc();
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
