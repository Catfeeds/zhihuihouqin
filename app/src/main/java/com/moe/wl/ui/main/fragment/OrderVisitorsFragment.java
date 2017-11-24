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
import com.moe.wl.ui.main.activity.me.LaiFangActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.adapter.OrderVisitorsAdapter;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.OrderVisitorsListBean;
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
 * 会议室订单Fragment
 * Created by 我的电脑 on 2017/8/17 0017.
 */
public class OrderVisitorsFragment extends BaseFragment2 {

    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;

    Unbinder unbinder;
    private OrderVisitorsAdapter adapter;
    private int page = 1;
    private List<OrderVisitorsListBean.OrderlistEntity> data;
    private int state;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        getData();
    }

    public static OrderVisitorsFragment getInstance(int i) {
        OrderVisitorsFragment waitOrderFragment = new OrderVisitorsFragment();
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
        adapter.setOnClickListener(new OrderVisitorsAdapter.OnClickListener() {
            @Override
            public void onClick(int type, int position) {
                switch (type) {
                    case 0: // 取消订单
                        showAlertDialog("是否取消订单", position);
                        break;

                    case 1: // 拨打电话
//                        showAlertDialog("是否拨打服务电话", position);
                        break;

                    case 2: // 再来一单
                        startActivity(new Intent(getActivity(), LaiFangActivity.class));
                        break;

                    case 3: // 评价
                        OtherUtils.gotoComment(getActivity(), data.get(position).getId(), Constants.VISITORS);
                        break;

                    case 4: // 删除订单
                        showAlertDialog("是否删除订单", position);
                        break;
                }
            }
        });
        adapter.setOnSureClickListener(new OrderVisitorsAdapter.OnSureClickListener() {
            @Override
            public void onClick(int position, int id) {
                commitOrder(position, id);
            }
        });
    }

    // 审核通过这个订单
    private void commitOrder(final int position, int id) {
        Observable observable = RetrofitUtils.getInstance().commitOrder(id);
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
                    showToast("确认订单成功");
                    data.get(position).setVisitchecked(1);
                    adapter.notifyDataSetChanged();
                } else if (orderBean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(orderBean.getMsg());
                }
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
                        intent.putExtra("from", Constants.VISITORS);
                        if (data.size() >= mPosition) {
                            OrderVisitorsListBean.OrderlistEntity listBean = data.get(mPosition);
                            if (state == 0) {
                                int id = listBean.getId();
                                LogUtils.d("id:" + id + "  position:" + mPosition);
                                intent.putExtra("OrderingID", id);
                                startActivity(intent);
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
        adapter = new OrderVisitorsAdapter(getActivity(), data, state);
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

    /**
     * 获取数据
     */
    public void getData() {
        Observable observable = RetrofitUtils.getInstance().getVisitorsOrder(state + 1, page);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderVisitorsListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }

            @Override
            public void onNext(OrderVisitorsListBean orderBean) {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
                if (orderBean.getErrCode() == 0) {
                    if (page == 1) {
                        data.clear();
                    }
                    data.addAll(orderBean.getOrderlist());
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
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.VISITORS, orderID);
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
