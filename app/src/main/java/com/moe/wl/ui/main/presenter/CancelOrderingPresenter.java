package com.moe.wl.ui.main.presenter;

import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ReasonBean;
import com.moe.wl.ui.main.model.CancelOrderingModel;
import com.moe.wl.ui.main.view.CancelOrderingView;

import mvp.cn.rx.MvpRxPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/12 0012
 */

public class CancelOrderingPresenter extends MvpRxPresenter<CancelOrderingModel, CancelOrderingView> {


    // 获取取消订单原因
    public void getReasonList(int serviceType) {
        getView().showProgressDialog();
        Observable request = getModel().getCancelReason(serviceType);
        getNetWork(request, new Subscriber<ReasonBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Throwable", e.getMessage());
                getView().dismissProgressDialog();
            }

            @Override
            public void onNext(ReasonBean bean) {
                if (bean.getErrCode() == 0) {
                    getView().getReasonList(bean);
                } else {
                    getView().showToast(bean.getMsg());
                }
            }
        });
    }


    // 取消报修订单
    public void cancelRepairsOrder(int old, String content) {
        Observable observable = getModel().cancelRepairOrder(old, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消办公用品订单
    public void cancelOfficeOrder(int old, int[] s, String s1) {
        Observable observable = getModel().cancelOfficeOrder(old, s, s1);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消订餐订单
    public void cancelMealOrder(int old, int[] s, String s1) {
        Observable observable = getModel().cancelMealOrder(old, s, s1);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消理发订单
    public void cancelHaircutsOrder(int old, String content) {
        Observable observable = getModel().cancelHaircutsOrder(old, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消订水订单
    public void cancelWaterOrder(int old, int[] s, String s1) {
        Observable observable = getModel().cancelWaterOrder(old, s, s1);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消医疗订单
    public void cancelMedicalOrder(int old, String content) {
        Observable observable = getModel().cancelMedicalOrder(old, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消专家订单
    public void cancelExpertsOrder(int old, String content) {
        Observable observable = getModel().cancelExpertsOrder(old, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消干洗订单
    public void cancelDryOrder(int old, int[] s, String s1) {
        Observable observable = getModel().cancelDryOrder(old, s, s1);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    // 取消图书订单
    public void cancelBookOrder(int old, String content) {
        Observable observable = getModel().cancelBookOrder(old, content);
        getView().showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                LogUtils.i("提交删除订单出现问题");
            }

            @Override
            public void onNext(CollectBean o) {
                if (o.getErrCode() == 0) {
                    getView().cancelOrder(o);
                } else {
                    LogUtils.i(o.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
