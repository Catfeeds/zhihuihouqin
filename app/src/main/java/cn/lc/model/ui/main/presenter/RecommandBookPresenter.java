package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.RecommandBookBean;
import cn.lc.model.ui.main.model.RecommandBookModel;
import cn.lc.model.ui.main.view.RecommandBookView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class RecommandBookPresenter extends MvpRxPresenter<RecommandBookModel, RecommandBookView> {

    public void getData(String title,String author,String publisher,String remark) {
        getView().showProgressDialog();
        Log.e("RecommandBookPresenter","发出请求");
        Observable login = getModel().getData(title,author,publisher,remark);
        getNetWork(login, new Subscriber<RecommandBookBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(RecommandBookBean listBean) {
                if(listBean.getErrCode()==0){
                    getView().getRecommandResult(listBean);
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
