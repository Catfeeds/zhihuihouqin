package com.moe.wl.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.ui.main.activity.DryCleaners.DryCleanersActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.adapter.DryCleanAdapter;
import com.moe.wl.ui.main.bean.CheckDryOrderBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.mywidget.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.cn.util.CallPhoneUtils;
import rx.Observable;
import rx.Subscriber;

/**
 * 干洗订单Fragment
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class OrderDryFragment extends BaseFragment2 {
    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private DryCleanAdapter dryCleanAdapter;
    private int limit = 20;
    private int page = 1;
    private List<CheckDryOrderBean.ListEntity> listAll;
    private List<CheckDryOrderBean.ListEntity> list;
    private int state;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData();
    }

    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.fragment_wait_order, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void initView() {
        initRecycler();
        setClick();
    }

    private void setClick() {
        dryCleanAdapter.setListener(new DryCleanAdapter.OnClickListener() {
            @Override
            public void onClickListener(int state, int position) {
                switch (state) {
                    case 1:
                        showAlertDialog("是否取消预订", state, position);
                        break;
                    case 2:
                        showAlertDialog("是否拨打电话", state, position);
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), DryCleanersActivity.class));
                        break;
                    case 4:
                        OtherUtils.gotoComment(getActivity(), listAll.get(position).getId(), Constants.DRYCLEANER);
                        break;
                    case 5://删除
                        showAlertDialog("是否删除订单", state, position);
                        break;
                }
            }
        });
    }

    private void showAlertDialog(String s, final int state, final int position) {
        new AlertDialog(getActivity()).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.DRYCLEANER);
                        if (list != null && list.size() > 0) {
                            CheckDryOrderBean.ListEntity listBean = list.get(position);
                            if (listBean != null) {
                                if (state == 1) {
                                    int id = listBean.getId();
                                    intent.putExtra("OrderingID", id);
                                    startActivity(intent);
                                } else if (state == 2) {
                                    //TODO 应该是服务电话
                                    String mobile = listBean.getServiceMobile();
                                    CallPhoneUtils.callPhone(mobile, getActivity());
                                } else if (state == 5) {
                                    // 删除订单
                                    deleteOrder(listBean.getId());
                                }
                            }
                        }

                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    public static OrderDryFragment getInstance(int i) {
        OrderDryFragment orderDryFragment = new OrderDryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from", i);
        orderDryFragment.setArguments(bundle);
        return orderDryFragment;
    }

    private void initRecycler() {
        list = new ArrayList<>();
        listAll = new ArrayList<>();
        Bundle arguments = getArguments();
        state = arguments.getInt("from");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //WaitOrderAdapter waitOrderAdapter = new WaitOrderAdapter(getActivity());
        dryCleanAdapter = new DryCleanAdapter(getActivity());
        recyclerView.setAdapter(dryCleanAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getData();
            }

            @Override
            public void onLoadMore() {
                page++;
                getData();
            }
        });
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    public void getData() {
        Observable observable = RetrofitUtils.getInstance().checkDryOrder(state, page, limit);
        showProgressDialog();
        observable.subscribe(new Subscriber<CheckDryOrderBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("获取订单出现问题    " + e.toString());
                dismissProgressDialog();
            }

            @Override
            public void onNext(CheckDryOrderBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    list = orderBean.getList();
                    if (page == 1) {
                        listAll.clear();
                        recyclerView.refreshComplete();
                    } else {
                        recyclerView.loadMoreComplete();
                    }
                    listAll.addAll(list);
                    dryCleanAdapter.setData(listAll, state);
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.DRYCLEANER, orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    page = 1;
                    getData();
                }
            }
        });
    }

}
