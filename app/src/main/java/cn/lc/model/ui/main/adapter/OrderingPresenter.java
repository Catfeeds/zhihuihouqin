package cn.lc.model.ui.main.adapter;

import android.util.Log;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.SelectTimeBean;
import cn.lc.model.ui.main.model.OrderingModel;
import cn.lc.model.ui.main.view.OrderingView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class OrderingPresenter extends MvpRxPresenter<OrderingModel, OrderingView> {

    public void getData(String userName, String phoneNumber, int sendfoodtimeId, int count, int addressId) {
        getView().showProgressDialog();
        Observable login = getModel().getData(userName, phoneNumber, sendfoodtimeId, count, addressId);
        getNetWork(login, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().createOrderingSucc(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    // 获取取餐时间
    public void getTime() {
        getView().showProgressDialog();
        Observable request = getModel().getTime();
        getNetWork(request, new Subscriber<SelectTimeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(SelectTimeBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getTime(listBean);
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
