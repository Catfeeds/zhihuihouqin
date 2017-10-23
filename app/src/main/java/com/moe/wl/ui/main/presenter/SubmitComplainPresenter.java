package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.LabellingBean;
import com.moe.wl.ui.main.model.SubmitComplainModel;
import com.moe.wl.ui.main.view.SubmitComplainView;

import java.util.ArrayList;

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
            public void onNext(CollectBean mResponse) {
                if (mResponse==null)
                    return;
                if (mResponse.getErrCode()==2){
                    getView().reLogin(Constants.LOGIN_ERROR);
                    return;
                }
                Log.e("errorCode", mResponse.getErrCode() + "");
                if (mResponse.getErrCode() == 0) {
                    getView().submitComplainSucc(mResponse);
                } else {
                    Log.e("getMsg", mResponse.getMsg());
                    getView().showToast(mResponse.getMsg());
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
