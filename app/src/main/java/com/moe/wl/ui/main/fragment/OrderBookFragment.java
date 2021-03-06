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
import com.moe.wl.ui.main.activity.Library.LibraryActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.adapter.BookOrderAdapter;
import com.moe.wl.ui.main.bean.BookOrderListBean;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.mywidget.AlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 图书订单Fragment
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class OrderBookFragment extends BaseFragment2 {
    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private int state;
    private int page = 1;
    private int limit = 20;
    //    List listAll = new ArrayList<>();
    private BookOrderAdapter bookOrderAdapter;
    private List<BookOrderListBean.OrderlistBean> orderList;

    @Override
    public View setLayout() {
        View view = View.inflate(getActivity(), R.layout.fragment_wait_order, null);
        unbinder = ButterKnife.bind(this, view);
        //EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void initView() {
        initRecycler();
        setClick();
    }

    // 点击完成
    private void toFinish(int id, final int position) {
        Observable observable = RetrofitUtils.getInstance().finishOrder(Constants.BOOK, id);
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
                    ToastUtil.showToast(getActivity(), "完成订单");
//                    data.remove(position);
//                    adapter.notifyDataSetChanged();
                } else {
                    ToastUtil.showToast(getActivity(), orderBean.getMsg());
                }
            }
        });
    }

    private void setClick() {
        bookOrderAdapter.setListener(new BookOrderAdapter.OnClickListener() {
            @Override
            public void onClickListener(int state, int position) {
                switch (state) {
                    case 0:
                        showAlertDialog("是否取消订单", state, position);
                        break;
                    case 1:
                        toFinish(orderList.get(position).getOrderid(), position);
                        break;
                    case 2:
                        OtherUtils.gotoComment(getActivity(), orderList.get(position).getOrderid(), Constants.BOOK);
                        break;
                    case 3:
                        startActivity(new Intent(getActivity(), LibraryActivity.class));
                        break;
                    case 4:
                        showAlertDialog("是否删除订单", state, position);
                        break;
                }
            }
        });
    }

    public static OrderBookFragment getInstance(int i) {
        OrderBookFragment orderBookFragment = new OrderBookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from", i);
        orderBookFragment.setArguments(bundle);
        return orderBookFragment;
    }

    private void initRecycler() {
        orderList = new ArrayList<>();
        Bundle arguments = getArguments();
        state = arguments.getInt("from");
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookOrderAdapter = new BookOrderAdapter(getActivity());
        recyclerView.setAdapter(bookOrderAdapter);

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

    public void getData() {
        Observable observable = RetrofitUtils.getInstance().bookOrderList(state + 1 + "", page, limit);
        showProgressDialog();
        observable.subscribe(new Subscriber<BookOrderListBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("获取订单出现问题");
                dismissProgressDialog();
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
            }

            @Override
            public void onNext(BookOrderListBean orderBean) {
                recyclerView.refreshComplete();
                recyclerView.loadMoreComplete();
                if (page == 1) {
                    orderList.clear();
                    recyclerView.refreshComplete();
                } else {
                    recyclerView.loadMoreComplete();
                }
                if (orderBean.getErrCode() == 0) {
                    orderList.addAll(orderBean.getOrderlist());
                    bookOrderAdapter.setData(orderList, state);
                }  else if (orderBean.getErrCode() == 2) {
                    reLogin(Constants.LOGIN_ERROR);
                } else {
                    showToast(orderBean.getMsg());
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
                        if (orderList != null && orderList.size() > 0) {
                            BookOrderListBean.OrderlistBean listBean = orderList.get(position);
                            if (listBean != null) {
                                if (state == 0) {
                                    Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                                    intent.putExtra("from", Constants.BOOK);
                                    intent.putExtra("OrderingID", listBean.getOrderid());
                                    startActivity(intent);
                                } else if (state == 1) {
//                                    String mobile = listBean.get;
//                                    CallPhoneUtils.callPhone(mobile, getActivity());
                                } else if (state == 4) {
                                    // 删除订单
                                    deleteOrder(listBean.getOrderid());
                                }
                            } else {
                                LogUtils.d("listBean为空了");
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

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.BOOK, orderID);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
