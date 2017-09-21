package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.SelectTimeBean;
import cn.lc.model.ui.main.model.VegetableOrderMessageModel;
import cn.lc.model.ui.main.view.VegetableOrderMessageView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class VegetableOrderMessagePresenter extends MvpRxPresenter<VegetableOrderMessageModel, VegetableOrderMessageView> {

    public void getVegetableSelectTime() {
        getView().showProgressDialog();
        Log.e("BookDetailPresenter","发出请求");
        Observable request = getModel().getVegetableSelectTime();
        getNetWork(request, new Subscriber<SelectTimeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(SelectTimeBean bean) {
                if(bean.getErrCode()==0){
                    getView().getVegetableSelectTimeSucc(bean);
                }else{
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
