package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RechargeRecordAdapter;
import com.moe.wl.ui.main.bean.FindChargeOrderBean;
import com.moe.wl.ui.main.model.RechargeModel;
import com.moe.wl.ui.main.modelimpl.RechargeModelImpl;
import com.moe.wl.ui.main.presenter.RechargePresenter;
import com.moe.wl.ui.main.view.RechargeView;

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
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RechargeRecordAdapter(this);
        recycler.setAdapter(adapter);
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("充值记录");
        //获取充值记录
        getPresenter().getData();
    }

    @Override
    public void rechargeResult(FindChargeOrderBean bean) {
        if (bean != null) {
            FindChargeOrderBean.PageBean page = bean.getPage();
            List<FindChargeOrderBean.PageBean.ListBean> list = page.getList();
            adapter.setData(list);
        }
    }
}