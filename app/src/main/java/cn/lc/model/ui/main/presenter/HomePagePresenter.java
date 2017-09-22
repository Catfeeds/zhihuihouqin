package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.HomePageBean;
import cn.lc.model.ui.main.model.HomePageModel;
import cn.lc.model.ui.main.view.HomePageView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class HomePagePresenter extends MvpRxPresenter<HomePageModel, HomePageView> {

    public void getHomePageData() {
        getView().showProgressDialog();
        Observable request = getModel().getHomePageData();
        getNetWork(request, new Subscriber<HomePageBean>() {
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
            public void onNext(HomePageBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getHomePageSucc(bean);
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
