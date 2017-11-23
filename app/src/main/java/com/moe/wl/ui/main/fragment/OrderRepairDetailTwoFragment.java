package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.moe.wl.R;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.adapter.OrderRepairDetailStateAdapter;
import com.moe.wl.ui.main.bean.OrderRepairsDetailTwoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：物业报修详情TWO
 * 作者：Shixhe On 2017/10/11 0011
 */

public class OrderRepairDetailTwoFragment extends BaseFragment2 {

    @BindView(R.id.list_view)
    ListView listView;
    Unbinder unbinder;
    private OrderRepairsDetailTwoBean data;

    private OrderRepairDetailStateAdapter adapter;

    @Override
    public View setLayout() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_repair_detail_two, null);
        return view;
    }

    @Override
    public void initView() {
        getData(getArguments().getInt("OrderID"));
    }

    private void setUI() {
        if (data == null) {
            ToastUtil.showToast(getActivity(), "报修订单数据错误！");
            getActivity().finish();
        }
        adapter = new OrderRepairDetailStateAdapter(getActivity(), data.getDetail());
        listView.setAdapter(adapter);
    }

    // 获取订单详情
    private void getData(int orderID) {
        Observable observable = RetrofitUtils.getInstance().orderRepairsDetailTwo(orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<OrderRepairsDetailTwoBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(OrderRepairsDetailTwoBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    data = orderBean;
                    setUI();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
