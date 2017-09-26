package cn.lc.model.ui.main.presenter;

import android.util.Log;

import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.model.PostNeedModel;
import cn.lc.model.ui.main.view.PostNeedView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PostNeedPresenter extends MvpRxPresenter<PostNeedModel, PostNeedView> {

    public void post(String realname,String mobile,String remark,String productName,
                           String count) {
        getView().showProgressDialog();
        Observable request = getModel().getData(realname,mobile,remark,productName,count);
        getNetWork(request, new Subscriber<ActivityPostBean>() {

            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().postSuccess(bean);
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
