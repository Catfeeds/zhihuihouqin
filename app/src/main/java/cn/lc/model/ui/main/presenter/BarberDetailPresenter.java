package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BarberListBean;
import cn.lc.model.ui.main.model.BarberDetailModel;
import cn.lc.model.ui.main.model.BarberListModel;
import cn.lc.model.ui.main.view.BarberDetailView;
import cn.lc.model.ui.main.view.BarberListView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class BarberDetailPresenter extends MvpRxPresenter<BarberDetailModel, BarberDetailView> {

    public void getData(int id) {
        getView().showProgressDialog();
        LogUtil.log("BarberDetailPresenter发出请求");
        Observable login = getModel().getData(id);
        getNetWork(login, new Subscriber<BarberDetailBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(BarberDetailBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getBarberDetailSucc(listBean);
                }else{
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
