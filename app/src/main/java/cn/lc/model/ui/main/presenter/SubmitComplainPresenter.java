package cn.lc.model.ui.main.presenter;

import android.util.Log;

import java.util.ArrayList;

import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.LabellingBean;
import cn.lc.model.ui.main.model.SubmitComplainModel;
import cn.lc.model.ui.main.view.SubmitComplainView;
import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class SubmitComplainPresenter extends MvpRxPresenter<SubmitComplainModel, SubmitComplainView> {

    // 提交意见投诉
    public void submitComplainSucc(int id, String complaintContent, String suggestContent, int anonymous, ArrayList<String> paths) {
        getView().showProgressDialog();
        paths.remove(paths.size() - 1);
        LogUtils.d("文件长度：" + paths.size());
        Observable request = getModel().submitComplain(id, complaintContent, suggestContent, anonymous, paths);
        getNetWork(request, new Subscriber<CollectBean>() {
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
            public void onNext(CollectBean bean) {
                Log.e("errorCode", bean.getErrCode() + "");
                if (bean.getErrCode() == 0) {
                    getView().submitComplainSucc(bean);
                } else {
                    Log.e("getMsg", bean.getMsg());
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }

    // 获取投诉标签
    public void getLabelling() {
        getView().showProgressDialog();
        Observable request = getModel().getLabelling();
        getNetWork(request, new Subscriber<LabellingBean>() {
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
            public void onNext(LabellingBean bean) {
                Log.e("errorCode", bean.getErrCode() + "");
                if (bean.getErrCode() == 0) {
                    getView().getLabelling(bean);
                } else {
                    Log.e("getMsg", bean.getMsg());
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
