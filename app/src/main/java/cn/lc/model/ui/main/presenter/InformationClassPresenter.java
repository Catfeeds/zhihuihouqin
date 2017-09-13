package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.InformationClazzBean;
import cn.lc.model.ui.main.model.InformationClassModel;
import cn.lc.model.ui.main.view.InformationClassView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationClassPresenter extends MvpRxPresenter<InformationClassModel, InformationClassView> {

    public void getInformationClass(int user) {
        getView().showProgressDialog();
        Observable request = getModel().getUserInformationClass(user);
        getNetWork(request, new Subscriber<InformationClazzBean>() {

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
            public void onNext(InformationClazzBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getInformationClassSucc(listBean);
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
