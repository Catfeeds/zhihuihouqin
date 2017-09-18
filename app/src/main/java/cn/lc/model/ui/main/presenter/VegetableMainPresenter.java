package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.VegetableBean;
import cn.lc.model.ui.main.model.VegetableMainModel;
import cn.lc.model.ui.main.view.VegetableMainView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class VegetableMainPresenter extends MvpRxPresenter<VegetableMainModel, VegetableMainView> {

    public void getVegetableData(int id, String keyword) {
        getView().showProgressDialog();
        Observable request = getModel().getVegetableData(id, keyword);
        getNetWork(request, new Subscriber<VegetableBean>() {

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
            public void onNext(VegetableBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getVegetableDataSucc(listBean);
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
