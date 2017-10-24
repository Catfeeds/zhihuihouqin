package com.moe.wl.ui.main.activity.me;

import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RechargeRecordAdapter;
import com.moe.wl.ui.main.bean.FindChargeOrderBean;
import com.moe.wl.ui.main.model.RechargeModel;
import com.moe.wl.ui.main.modelimpl.RechargeModelImpl;
import com.moe.wl.ui.main.presenter.RechargePresenter;
import com.moe.wl.ui.main.view.RechargeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RechargeRecordActivity extends BaseActivity<RechargeModel,RechargeView,RechargePresenter> implements RechargeView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.recycler)
    XRecyclerView recycler;
    @BindView(R.id.activity_recharge_record)
    LinearLayout activityRechargeRecord;
    private RechargeRecordAdapter adapter;
    private int page=1;
    private int limit=10;
    private List<FindChargeOrderBean.ListBean> listAll;

    @Override
    public RechargePresenter createPresenter() {
        return new RechargePresenter();
    }

    @Override
    public RechargeModel createModel() {
        return new RechargeModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_recharge_record);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        listAll = new ArrayList<>();
        //获取充值记录
        getPresenter().getData( page+"",limit+"");
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RechargeRecordAdapter(this);
        recycler.setAdapter(adapter);
        recycler.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                getPresenter().getData(page + "", limit + "");
                recycler.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                LogUtils.i(page+"==============");
                getPresenter().getData(page + "", limit + "");
                recycler.loadMoreComplete();
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("充值记录");

    }

    @Override
    public void rechargeResult(FindChargeOrderBean bean) {
        if (bean != null) {
            List<FindChargeOrderBean.ListBean> list = bean.getList();
            if(page==1){
                listAll.clear();
            }
            listAll.addAll(list);
            adapter.setData(listAll);
        }
    }
}