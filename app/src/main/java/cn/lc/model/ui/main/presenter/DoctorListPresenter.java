package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.DoctorListBean;
import cn.lc.model.ui.main.model.DoctorListModel;
import cn.lc.model.ui.main.view.DoctorListView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorListPresenter extends MvpRxPresenter<DoctorListModel, DoctorListView> {

    public void getData() {
        getView().showProgressDialog();
        Log.e("DoctorListPresenter","进行数据处理");
        Observable login = getModel().getData();
        getNetWork(login, new Subscriber<DoctorListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(DoctorListBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getDoctorListSucc(listBean);
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
