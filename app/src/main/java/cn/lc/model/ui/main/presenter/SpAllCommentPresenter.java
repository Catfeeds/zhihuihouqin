package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.SpAllCommentBean;
import cn.lc.model.ui.main.model.SpAllCommentModel;
import cn.lc.model.ui.main.view.SpAllCommentView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpAllCommentPresenter extends MvpRxPresenter<SpAllCommentModel, SpAllCommentView> {

    public void getAllComment(String id,String type,String page,String limit) {
        getView().showProgressDialog();
        Observable request = getModel().getData(id,type,page,limit);
        getNetWork(request, new Subscriber<SpAllCommentBean>() {

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
            public void onNext(SpAllCommentBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getcommentSucc(bean);
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
