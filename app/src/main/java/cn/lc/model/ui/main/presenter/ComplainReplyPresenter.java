package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ComplainReplyBean;
import cn.lc.model.ui.main.model.ComplainReplyModel;
import cn.lc.model.ui.main.view.ComplainReplyView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class ComplainReplyPresenter extends MvpRxPresenter<ComplainReplyModel, ComplainReplyView> {

    public void getComplainReply(int id, int page) {
        getView().showProgressDialog();
        Observable request = getModel().getComplainReply(id, page);
        getNetWork(request, new Subscriber<ComplainReplyBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ComplainReplyBean listBean) {
                if (listBean.getErrCode() == 0) {
                    getView().getComplainReply(listBean);
                } else {
                    getView().showToast(listBean.getMsg());
                }
            }
        });
    }

    public void feedbackMessage(int id, String content) {
        getView().showProgressDialog();
        Observable request = getModel().feedbackMessage(id, content);
        getNetWork(request, new Subscriber<CollectBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(CollectBean listBean) {
                LogUtils.d("编辑地址", listBean.getErrCode() + "  " + listBean.getMsg());
                if (listBean.getErrCode() == 0) {
                    getView().feedbackMessage();
                } else {
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
