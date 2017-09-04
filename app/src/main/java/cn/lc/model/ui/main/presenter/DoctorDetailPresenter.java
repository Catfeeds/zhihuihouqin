package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.bean.DoctorListBean;
import cn.lc.model.ui.main.model.DoctorDetailModel;
import cn.lc.model.ui.main.model.DoctorListModel;
import cn.lc.model.ui.main.view.DoctorDetailView;
import cn.lc.model.ui.main.view.DoctorListView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorDetailPresenter extends MvpRxPresenter<DoctorDetailModel, DoctorDetailView> {

    public void getData(int id) {
        getView().showProgressDialog();
        Log.e("DoctorDetailPresenter","进行数据处理");
        Observable login = getModel().getData(id);
        getNetWork(login, new Subscriber<DoctorDetailBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(DoctorDetailBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getDoctorDetailSucc(listBean);
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
