package cn.lc.model.ui.main.presenter;

import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ReasonBean;
import cn.lc.model.ui.main.model.CancelOrderingModel;
import cn.lc.model.ui.main.view.CancelOrderingView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */

public class CancelOrderingPresenter extends MvpRxPresenter<CancelOrderingModel, CancelOrderingView> {


    // 获取取消订单原因
    public void getReasonList() {
        getView().showProgressDialog();
        Observable request = getModel().getReasonList();
        getNetWork(request, new Subscriber<ReasonBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(ReasonBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getReasonList(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }


    // 取消订单
    public void cancelOrdering(int id, int[] reason, String content) {
        getView().showProgressDialog();
        Observable request = getModel().cancelOrder(id, reason, content);
        getNetWork(request, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().cancelOrder(bean);
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
