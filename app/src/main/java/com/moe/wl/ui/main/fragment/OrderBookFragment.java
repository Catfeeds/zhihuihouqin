package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.BookOrderAdapter;
import com.moe.wl.ui.main.bean.BookOrderListBean;
import com.moe.wl.ui.mywidget.AlertDialog;

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

    private void setClick() {
        bookOrderAdapter.setListener(new BookOrderAdapter.OnClickListener() {
            @Override
            public void onClickListener(int state, int position) {
                switch (state) {
                    case 0:
                        break;
                    case 1:
                        showAlertDialog("是否取消订单", state, position);
                        break;
                    case 2:
                        showAlertDialog("是否拨打服务电话", state, position);
                        break;
                    case 3:
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
                getData(state);
            }

            @Override
            public void onLoadMore() {
                page++;
                getData(state);
            }
        });

        getData(state);
    }

    public void getData(int typeId) {
        Observable observable = RetrofitUtils.getInstance().bookOrderList(typeId + 1 + "", page, limit);
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
            }

            @Override
            public void onNext(BookOrderListBean orderBean) {
                if (page == 1) {
                    orderList.clear();
                    recyclerView.refreshComplete();
                } else {
                    recyclerView.loadMoreComplete();
                }
                if (orderBean.getErrCode() == 0) {
                    LogUtils.d("获取数据成功");
                    orderList = orderBean.getOrderlist();
                    bookOrderAdapter.setData(orderList, state);
                } else {
                    LogUtils.d("BookOrderListBean结果出现问题");
                    ToastUtil.showToast(getActivity(), orderBean.getMsg());
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
                        /*Intent intent = new Intent(getActivity(), CancelOrderingActivity.class);
                        intent.putExtra("from", Constants.ORDERWATER);
                        if(list!=null&&list.size()>0){
                            CheckDryOrderBean.PageBean.ListBean listBean = list.get(state - 1);
                            if(listBean!=null){
                                if(state==1){
                                    int id = listBean.getId();
                                    intent.putExtra("OrderingID",id);
                                    startActivity(intent);
                                }else if(state==2){
                                    //todo 应该是服务电话
                                    String mobile = listBean.getMobile();
                                    CallPhoneUtils.callPhone(mobile,getActivity());
                                }
                            }else{
                                LogUtils.i("listBean为空了");

                            }
                        }*/

                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //EventBus.getDefault().unregister(this);
    }
}
