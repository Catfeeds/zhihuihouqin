package cn.lc.model.ui.main.presenter;

import android.util.Log;

import java.io.File;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.WuyeHomeBean;
import cn.lc.model.ui.main.model.CollectModel;
import cn.lc.model.ui.main.model.WuyeHomeModel;
import cn.lc.model.ui.main.view.CollectView;
import cn.lc.model.ui.main.view.WuyeHomeView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class WuyeHomePresenter extends MvpRxPresenter<WuyeHomeModel, WuyeHomeView> {

    public void getData(int menditem,String username,int mobile,
                        String invitetime,String serviceplace,String mendcontent,File files) {
        getView().showProgressDialog();
        LogUtil.log("BarberListPresenter发出请求");
        Observable login = getModel().getData(menditem,username,mobile,invitetime,serviceplace,mendcontent,files);
        getNetWork(login, new Subscriber<WuyeHomeBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(WuyeHomeBean wuyeHomeBean) {
                if(wuyeHomeBean.getErrCode()==0){
                    getView().getWuyeHomeResult(wuyeHomeBean);
                }else{
                    getView().showToast(wuyeHomeBean.getMsg());
                }
            }
        });
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
