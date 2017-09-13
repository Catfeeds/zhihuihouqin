package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.InformationBean;
import cn.lc.model.ui.main.model.InformationModel;
import cn.lc.model.ui.main.view.InformationView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationPresenter extends MvpRxPresenter<InformationModel, InformationView> {

    public void getInformation(int typeId, int isRecommend, String keyword, int page) {
        getView().showProgressDialog();
        Observable request = getModel().getInformationData(typeId, isRecommend, keyword, page);
        getNetWork(request, new Subscriber<InformationBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(InformationBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getInformationSucc(listBean);
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
