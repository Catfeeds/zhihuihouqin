package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.ui.main.bean.BookOrderListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.BookOrderAdapter;
import com.moe.wl.ui.mywidget.AlertDialog;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 我的电脑 on 2017/9/15 0015.
 */

public class BookFragment extends BaseFragment2 {
    @BindView(R.id.rv_wait_order_fragment)
    XRecyclerView recyclerView;
    Unbinder unbinder;
    private int state;
    private int page;
    private int limit=10;
    List listAll=new ArrayList<>();
    private BookOrderAdapter bookOrderAdapter;

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
            public void onClickListener(int state,int position) {
                switch (state){
                    case 0:
                        break;
                    case 1:
                        showAlertDialog("是否取消订单",state,position);
                        break;
                    case 2:
                        showAlertDialog("是否拨打服务电话",state,position);
                        break;
                    case 3:
                        break;


                }
            }
        });
    }
    public static BookFragment getInstance(int i){
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("from",i);
        bookFragment.setArguments(bundle);
        return bookFragment;
    }
    private void initRecycler() {
        Bundle arguments = getArguments();
        state = arguments.getInt("from");
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        bookOrderAdapter = new BookOrderAdapter(getActivity());
        recyclerView.setAdapter(bookOrderAdapter);
        switch (state){
            case 0:
                getData(0+"");
                break;
            case 1:
                getData(1+"");
                break;
            case 2:
                getData(2+"");
                break;
            case 3:
                getData(3+"");
                break;
        }

    }
    public void getData(String typeid) {
        Observable observable = RetrofitUtils.getInstance().bookOrderList(typeid);
        showProgressDialog();
        observable.subscribe(new Subscriber<BookOrderListBean>() {
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
            public void onNext(BookOrderListBean orderBean) {
                if(orderBean.getErrCode()==0){
                    LogUtils.i("获取数据成功");
                    if(orderBean!=null){
                        List<BookOrderListBean.OrderlistBean> orderlist = orderBean.getOrderlist();
                        bookOrderAdapter.setData(orderlist,state);
                    }
                }else{
                    LogUtils.i("BookOrderListBean结果出现问题");
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
                        intent.putExtra("from", Constants.DRYCANCEL);
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
