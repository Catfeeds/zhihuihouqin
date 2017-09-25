package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.MessageListBean;
import cn.lc.model.ui.main.model.MessageListModel;
import cn.lc.model.ui.main.view.MessageListView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class MessageListPresenter extends MvpRxPresenter<MessageListModel, MessageListView> {


    public void getMessageList(int messageType, int page) {
        Observable request = getModel().getMessageListSucc(messageType, page);
        getNetWork(request, new Subscriber<MessageListBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(MessageListBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getMessageListSucc(bean);
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
