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
import com.moe.wl.ui.main.adapter.OrderHairCutAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.OrderHairCutBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

/**
 * 理发订单Fragment
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class OrderHairCutFragment extends BaseFragment2 {

    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;

    Unbinder unbinder;
    private OrderHairCutAdapter adapter;
    private int page = 1;
    private List<OrderHairCutBean.OrderlistEntity> data;
    private int state;

    private int serviceType = 6;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData();
    }

    public static OrderHairCutFragment getInstance(int i) {
        OrderHairCutFragment fragment = new OrderHairCutFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from", i);
        fragment.setArguments(bundle);
        return fragment;
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
        adapter.setOnClickListener(new OrderHairCutAdapter.OnClickListener() {
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

    }

    private void showAlertDialog(String s, final int position) {
        new AlertDialog(getActivity()).builder()
//                .setBigMsg(s)
                .setMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.HAIRCUTS);
                        if (data.size() >= position) {
                            OrderHairCutBean.OrderlistEntity listBean = data.get(position);
                            if (state == 0) {
                                // TODO 取消理发订单
                                int id = listBean.getOrderid();
                                LogUtils.d("id:" + id + "  position:" + position);
                                intent.putExtra("OrderingID", id);
                                intent.putExtra("ServiceType", serviceType);
                                startActivity(intent);
                            } else if (state == 1) {
                                // TODO 完成理发服务

                            } else if (state == 4) {
                                // TODO 删除
                                deleteOrder(listBean.getOrderid());
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
        adapter = new OrderHairCutAdapter(getActivity(), data, state);
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
        Observable observable = RetrofitUtils.getInstance().getHairCutOrder(state + 1, page);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderHairCutBean>() {
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
            public void onNext(OrderHairCutBean orderBean) {
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
        Observable observable = RetrofitUtils.getInstance().deleteHairCutOrder(orderID);
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