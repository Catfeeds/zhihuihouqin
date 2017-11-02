package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.RechargeOrderRecordAdapter;
import com.moe.wl.ui.main.bean.GetChargeOrderBean;
import com.moe.wl.ui.main.model.RechargeOrderModel;
import com.moe.wl.ui.main.modelimpl.RechargeOrderModelImpl;
import com.moe.wl.ui.main.presenter.RechargeOrderPresenter;
import com.moe.wl.ui.main.view.RechargeOrderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by 我的电脑 on 2017/11/2 0002.
 */

public class RechargeOrderFragment extends BaseFragment<RechargeOrderModel, RechargeOrderView, RechargeOrderPresenter> implements RechargeOrderView {

    @BindView(R.id.recycler)
    XRecyclerView recycler;
    Unbinder unbinder;
    private int type;
    private List<GetChargeOrderBean.ListBean> datas;
    private int page;
    private int limit = 10;
    private RechargeOrderRecordAdapter adapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_recharge_order);
    }

    public static RechargeOrderFragment getInstance(int type) {
        RechargeOrderFragment fragment = new RechargeOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initView(View v) {
        datas = new ArrayList<>();
        initRecycler();
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getInt("type");
            getPresenter().getOrder(1, limit, type);
        }
    }

    @Override
    public RechargeOrderModel createModel() {
        return new RechargeOrderModelImpl();
    }

    @Override
    public RechargeOrderPresenter createPresenter() {
        return new RechargeOrderPresenter();
    }

    @Override
    public void getOrder(GetChargeOrderBean bean) {
        if (bean != null) {
            List<GetChargeOrderBean.ListBean> list = bean.getList();
            if(page==1){
                datas.clear();
            }
            datas.addAll(list);
            adapter.setData(type,datas);
        }
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter = new RechargeRecordAdapter(this);
        adapter = new RechargeOrderRecordAdapter(getActivity());
        recycler.setAdapter(adapter);
        recycler.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getOrder(page, limit, type);
                recycler.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                LogUtils.i(page + "==============");
                getPresenter().getOrder(page, limit, type);
                recycler.loadMoreComplete();
            }
        });
    }
}
