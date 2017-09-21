package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.ExpertDetailBean;
import cn.lc.model.ui.main.model.ExpertDetailModel;
import cn.lc.model.ui.main.view.ExpertDetailView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class ExpertDetailPresenter extends MvpRxPresenter<ExpertDetailModel, ExpertDetailView> {

    public void getExpertDetail() {
        getView().showProgressDialog();
        Observable request = getModel().getExpertDetail();
        getNetWork(request, new Subscriber<ExpertDetailBean>() {
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
            public void onNext(ExpertDetailBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getExpertDetailSucc(bean);
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
