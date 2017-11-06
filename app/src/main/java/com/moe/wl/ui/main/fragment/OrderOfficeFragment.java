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
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.activity.property_maintenance.PropertyAintenanceActivity;
import com.moe.wl.ui.main.adapter.OrderOfficeAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.OrderOfficeBean;
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
 * 报修订单Fragment
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class OrderOfficeFragment extends BaseFragment2 {

    private List<OrderOfficeBean.ListEntity> data;
    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;

    Unbinder unbinder;
    private OrderOfficeAdapter adapter;
    private int page = 1;
    private int state;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData();
    }

    public static OrderOfficeFragment getInstance(int i) {
        OrderOfficeFragment waitOrderFragment = new OrderOfficeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from", i);
        waitOrderFragment.setArguments(bundle);
        return waitOrderFragment;
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
        adapter.setOnClickListener(new OrderOfficeAdapter.OnClickListener() {
            @Override
            public void onClick(int type, int position) {
                switch (type) {
                    case 0: // 取消订单
                        showAlertDialog("是否取消订单", position);
                        break;

                    case 1: // 联系配送人员
                        showAlertDialog("是否联系商家", position);
                        break;

                    case 2:
                        startActivity(new Intent(getActivity(), PropertyAintenanceActivity.class));
                        break;

                    case 3:
                        OtherUtils.gotoComment(getActivity(), data.get(position).getId(), Constants.PROPERRY);
                        break;

                    case 4: // 删除订单
                        showAlertDialog("是否删除订单", position);
                        break;
                }
            }
        });
    }

    private void showAlertDialog(String s, final int position) {
        new AlertDialog(getActivity()).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.OFFICESUPPLIES);
                        if (data != null && data.size() > 0) {
                            OrderOfficeBean.ListEntity listBean = data.get(position);
                            if (state == 0) {
                                int id = listBean.getId(); // 订单id
                                intent.putExtra("OrderingID", id);
                                startActivity(intent);
                            } else if (state == 1) {
                                //TODO 服务电话
                                String mobile = listBean.getServiceMobile(); // 手机号
                                CallPhoneUtils.callPhone(mobile, getActivity());
                            } else if (state == 4) {
                                // 删除订单
                                deleteOrder(listBean.getId());
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

    private void initRecycler() {
        data = new ArrayList<>();
        Bundle arguments = getArguments();
        state = arguments.getInt("from");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrderOfficeAdapter(getActivity(), data, state);
        recyclerView.setAdapter(adapter);
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
        Observable observable = RetrofitUtils.getInstance().getOfficeOrder(state + 1, page);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderOfficeBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("获取订单出现问题");
                dismissProgressDialog();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }

            @Override
            public void onNext(OrderOfficeBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    if (page == 1) {
                        data.clear();
                        recyclerView.refreshComplete();
                    } else {
                        recyclerView.loadMoreComplete();
                    }
                    data.addAll(orderBean.getList());
                    adapter.notifyDataSetChanged();
                } else if (orderBean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(orderBean.getMsg());
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.OFFICESUPPLIES, orderID);
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
