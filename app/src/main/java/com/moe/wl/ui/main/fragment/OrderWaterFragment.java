package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.ui.main.adapter.OrderWaterAdapter;
import com.moe.wl.ui.main.bean.OrderWaterSumAndcount;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.main.model.QueryWaterListModel;
import com.moe.wl.ui.main.modelimpl.QueryWaterListModelImpl;
import com.moe.wl.ui.main.presenter.QueryWaterListPresenter;
import com.moe.wl.ui.main.view.QueryWaterListView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 我的电脑 on 2017/9/26 0026.
 */

public class OrderWaterFragment extends BaseFragment<QueryWaterListModel, QueryWaterListView,
        QueryWaterListPresenter> implements QueryWaterListView {
    @BindView(R.id.rv_water)
    XRecyclerView rvWater;
    private OrderWaterAdapter waterAdapter;
    private int page = 1;
    private int limit = 20;
    private boolean isRefresh;
    private int id;
    private List<QueryWaterListBean.PageBean.ListBean> mList;
    private int needdeposit;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.order_list);
    }

    @Override
    public void initView(View v) {
        mList = new ArrayList<>();
        initWaterRecycler();
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            needdeposit = bundle.getInt("needdeposit");
            getPresenter().queryWaterType(id, page, limit);
        }
    }

    public static OrderWaterFragment getInstance(int id,int needdeposit) {
        OrderWaterFragment fragment = new OrderWaterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        bundle.putInt("needdeposit",needdeposit);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initWaterRecycler() {
        rvWater.setLayoutManager(new LinearLayoutManager(getActivity()));
        waterAdapter = new OrderWaterAdapter(getActivity());
        rvWater.setAdapter(waterAdapter);
        rvWater.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvWater.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvWater.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                page = 1;
                getPresenter().queryWaterType(id, page, limit);
                rvWater.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                page++;
                getPresenter().queryWaterType(id, page, limit);
                rvWater.loadMoreComplete();
            }
        });
        rvWater.setPullRefreshEnabled(false);
    }

    @Override
    public void queryWaterListSucc(QueryWaterListBean bean) {
        if (bean != null) {
            final List<QueryWaterListBean.PageBean.ListBean> list = bean.getPage().getList();
            if (isRefresh) {
                mList.clear();
            }
            mList.addAll(list);
            waterAdapter.setData(mList);
            waterAdapter.setListener(new OrderWaterAdapter.OnClickListener() {
                @Override
                public void onClickListener(QueryWaterListBean.PageBean.ListBean bean, int id, boolean isAdd) {
//                    int count=0;
//                    int sum=0;
//                    for (int i = 0; i < list.size(); i++) {
//                        QueryWaterListBean.PageBean.ListBean listBean = list.get(i);
//                        int count1 = listBean.getCount();
//                        int price = listBean.getPrice();
//                        sum+=count1*price;
//                        count+=count1;
//                        LogUtils.d("sum:" + sum + "  count:" + count);
//                    }
                    bean.setNeeddeposit(needdeposit);
                    EventBus.getDefault().post(new OrderWaterSumAndcount(id, bean, isAdd));
                }
            });
        }
    }

    @Override
    public QueryWaterListModel createModel() {
        return new QueryWaterListModelImpl();
    }

    @Override
    public QueryWaterListPresenter createPresenter() {
        return new QueryWaterListPresenter();
    }

}
