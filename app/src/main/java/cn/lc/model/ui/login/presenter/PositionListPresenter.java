package cn.lc.model.ui.login.presenter;

import android.util.Log;

import cn.lc.model.ui.login.bean.PositionListBean;
import cn.lc.model.ui.login.model.PositionListModel;
import cn.lc.model.ui.login.view.PositionListView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class PositionListPresenter extends MvpRxPresenter<PositionListModel, PositionListView> {


    public void getData() {
        LogUtil.log("MainPresenter发出请求");
        getView().showProgressDialog();
        getModel().getPositionList().subscribe(new Subscriber<PositionListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(PositionListBean positionListBean) {
                if (positionListBean.getErrCode() == 0) {
                    getView().getListSucc(positionListBean);
                } else {
                    getView().showToast(positionListBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
