package com.moe.wl.ui.main.presenter;

import android.util.Log;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.BarberCollect;
import com.moe.wl.ui.main.bean.BarberProductCollect;
import com.moe.wl.ui.main.bean.BookCollect;
import com.moe.wl.ui.main.bean.HealthServerceHomeBean;
import com.moe.wl.ui.main.bean.InforMationCollect;
import com.moe.wl.ui.main.bean.OfficeCollect;
import com.moe.wl.ui.main.model.McNocticeModel;
import com.moe.wl.ui.main.view.McNoticeView;

import java.util.List;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class McNoticePresenter extends MvpRxPresenter<McNocticeModel, McNoticeView> {
    //删除我的收藏
    public void deleteFavorList(List type) {
        Observable request = getModel().deleteFavorList(type);

            getNetWork(request, new Subscriber<ActivityPostBean>() {

                @Override
                public void onCompleted() {
                    getView().dismissProgressDialog();
                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.i("删除收藏"+e.getMessage());
                }

                @Override
                public void onNext(ActivityPostBean bean) {
                    if(bean.getErrCode()==0){
                        getView().getDetleteResult(bean);
                    }else if(bean.getErrCode()==2){
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }else{
                        getView().showToast(bean.getMsg());
                    }
                }
            });
    }
    public void findUserFavorList(String type) {
        Observable request = getModel().findUserFavorList(type);
        if (type.equals("1")) {
            getNetWork(request, new Subscriber<InforMationCollect>() {
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
                public void onNext(InforMationCollect mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect1(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if (type.equals("2")) {
            getNetWork(request, new Subscriber<OfficeCollect>() {

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
                public void onNext(OfficeCollect mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect2(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if ("3".equals(type)) {//理发收藏
            getNetWork(request, new Subscriber<BarberProductCollect>() {
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
                public void onNext(BarberProductCollect mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect3(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if ("4".equals(type)) {//图书收藏
            getNetWork(request, new Subscriber<BookCollect>() {

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
                public void onNext(BookCollect mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect4(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if ("5".equals(type)) {//专家收藏
            getNetWork(request, new Subscriber<HealthServerceHomeBean>() {

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
                public void onNext(HealthServerceHomeBean mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect5(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if ("6".equals(type)) {//活动收藏
            getNetWork(request, new Subscriber<ActivityHomeBean>() {

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
                public void onNext(ActivityHomeBean mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect6(mResponse.getActivitylist());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        } else if ("7".equals(type)) {//理发师收藏
            getNetWork(request, new Subscriber<BarberCollect>() {
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
                public void onNext(BarberCollect mResponse) {
                    if (mResponse == null)
                        return;
                    if (mResponse.getErrCode() == 2) {
                        getView().reLogin(Constants.LOGIN_ERROR);
                        return;
                    }
                    if (mResponse.getErrCode() == 0) {
                        getView().getCollect7(mResponse.getList());
                    } else {
                        getView().showToast(mResponse.getMsg());
                    }
                }
            });
        }
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
