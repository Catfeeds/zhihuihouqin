package cn.lc.model.ui.main.presenter;

import android.util.Log;

import java.io.File;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.model.CollectModel;
import cn.lc.model.ui.main.model.DryToCommentModel;
import cn.lc.model.ui.main.view.CollectView;
import cn.lc.model.ui.main.view.DryToCommentView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class DryToCommentPresenter extends MvpRxPresenter<DryToCommentModel, DryToCommentView> {

    public void getData(int oid, double stars, String content, String isAnonymous, File imgFile) {
        getView().showProgressDialog();
        LogUtil.log("DryToCommentPresenter发出请求");
        Observable login = getModel().dryToComment(oid,stars,content,isAnonymous,imgFile);
        getNetWork(login, new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable",e.getMessage());
            }

            @Override
            public void onNext(CollectBean collectBean) {
                if(collectBean.getErrCode()==0){
                    getView().commentSucc(collectBean);
                }else{
                    getView().showToast(collectBean.getMsg());
                }
            }
        });
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
