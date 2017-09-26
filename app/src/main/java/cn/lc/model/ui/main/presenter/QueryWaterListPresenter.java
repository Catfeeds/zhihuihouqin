package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.QueryWaterListBean;
import cn.lc.model.ui.main.bean.QueryWaterTypeBean;
import cn.lc.model.ui.main.model.OrderHomeModel;
import cn.lc.model.ui.main.model.QueryWaterListModel;
import cn.lc.model.ui.main.view.OrderHomeView;
import cn.lc.model.ui.main.view.QueryWaterListView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class QueryWaterListPresenter extends MvpRxPresenter<QueryWaterListModel, QueryWaterListView> {

    public void queryWaterType(int id,int page,int limit) {
        getView().showProgressDialog();
        Observable request = getModel().queryWaterList(id,page,limit);
        getNetWork(request, new Subscriber<QueryWaterListBean>() {

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
            public void onNext(QueryWaterListBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().queryWaterListSucc(bean);
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
