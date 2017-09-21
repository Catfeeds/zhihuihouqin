package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.ExpertOrderBean;
import cn.lc.model.ui.main.model.ExpertOrderModel;
import cn.lc.model.ui.main.view.ExpertOrderView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 专家评论
 * Created by hh on 2017/5/12.
 */

public class ExpertOrderPresenter extends MvpRxPresenter<ExpertOrderModel, ExpertOrderView> {

    public void submitExpertOrder(int id, int timeID) {
        getView().showProgressDialog();
        Observable request = getModel().submitExpertOrder(id, timeID);
        getNetWork(request, new Subscriber<ExpertOrderBean>() {
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
            public void onNext(ExpertOrderBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().submitExpertOrderSucc(bean);
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
