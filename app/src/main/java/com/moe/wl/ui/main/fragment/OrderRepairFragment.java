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
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.adapter.OrderRepairAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.OrderRepairBean;
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
public class OrderRepairFragment extends BaseFragment2 {

    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;

    Unbinder unbinder;
    private OrderRepairAdapter adapter;
    private int page = 1;
    private List<OrderRepairBean.OrderlistEntity> data;
    private int state;

    private int serviceType = 1;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData();
    }

    public static OrderRepairFragment getInstance(int i) {
        OrderRepairFragment waitOrderFragment = new OrderRepairFragment();
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
        adapter.setOnClickListener(new OrderRepairAdapter.OnClickListener() {
            @Override
            public void onClick(int type, int position) {
                switch (type) {
                    case 0: // 取消订单
                        showAlertDialog("是否取消订单", position);
                        break;

                    case 1: // 拨打电话
                        showAlertDialog("是否拨打服务电话", position);
                        break;

                    case 2: // 评价
                        break;

                    case 3: // 评价
                        break;

                    case 4: // 删除订单
                        showAlertDialog("是否删除订单", position);
                        break;
                }
            }
        });

        // 支付监听
        adapter.setOnPayClickListener(new OrderRepairAdapter.OnPayClickListener() {
            @Override
            public void onPayClick() {

            }
        });
    }

    int mPosition;

    private void showAlertDialog(String s, int position) {
        mPosition = position;
        new AlertDialog(getActivity()).builder()
//                .setBigMsg(s)
                .setMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.PROPERRY);
                        if (data.size() >= mPosition) {
                            OrderRepairBean.OrderlistEntity listBean = data.get(mPosition);
                            if (state == 0) {
                                int id = listBean.getOrderId();
                                LogUtils.d("id:" + id + "  position:" + mPosition);
                                intent.putExtra("OrderingID", id);
                                intent.putExtra("ServiceType", serviceType);
                                startActivity(intent);
                            } else if (state == 1) {
                                //TODO 服务电话
                                String mobile = listBean.getMenderMobile();
                                CallPhoneUtils.callPhone(mobile, getActivity());
                            } else if (state == 4) {
                                // 删除订单
                                deleteOrder(listBean.getOrderId());
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
        adapter = new OrderRepairAdapter(getActivity(), data, state);
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
        Observable observable = RetrofitUtils.getInstance().getRepairOrder(state + 1, page);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderRepairBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("获取订单出现问题");
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderRepairBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    if (page == 1) {
                        data.clear();
                        recyclerView.refreshComplete();
                    } else {
                        recyclerView.loadMoreComplete();
                    }
                    data.addAll(orderBean.getOrderlist());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteRepairsOrder(orderID);
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
