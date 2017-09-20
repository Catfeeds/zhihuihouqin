package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.SpAllCommentCountBean;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.SpCommentCountModel;
import cn.lc.model.ui.main.view.AddressView;
import cn.lc.model.ui.main.view.SpCommentCountView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpCommentCountPresenter extends MvpRxPresenter<SpCommentCountModel, SpCommentCountView> {

    public void getData(String id) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id);
        getNetWork(request, new Subscriber<SpAllCommentCountBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(SpAllCommentCountBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getCommentSucc(bean);
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
