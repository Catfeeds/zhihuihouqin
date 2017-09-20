package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ShopCarInfoBean;
import cn.lc.model.ui.main.bean.SpDetailBean;
import cn.lc.model.ui.main.model.SpDetailModel;
import cn.lc.model.ui.main.view.SpDetailView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpDetailPresenter extends MvpRxPresenter<SpDetailModel, SpDetailView> {

    public void getSpDetail(String id) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id);
        getNetWork(request, new Subscriber<SpDetailBean>() {

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
            public void onNext(SpDetailBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getSpDetailSucc(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    public void getCollectInfo(int i, int j) {
        Observable observable = RetrofitUtils.getInstance().getHealthInfoColect(i, j);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("出现异常" + e.getMessage());
            }

            @Override
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getCollectResult(bean);
                } else {
                    LogUtils.i("错误信息" + bean.getMsg());
                }
            }
        });
    }

    public void getShopCarInfo(String id) {
        Observable observable = RetrofitUtils.getInstance().getSpShopCarInfo(id);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<ShopCarInfoBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i(e.getMessage());
            }

            @Override
            public void onNext(ShopCarInfoBean bean) {
                if(bean.getErrCode()==0){
                    getView().getShopCarInfo(bean);
                }else{
                    LogUtils.i(bean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
