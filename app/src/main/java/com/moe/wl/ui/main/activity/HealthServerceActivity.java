package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.HealthServiceGridAdapter;
import com.moe.wl.ui.main.adapter.HealthServiceRvAdapter;
import com.moe.wl.ui.main.bean.HealthServerceHomeBean;
import com.moe.wl.ui.main.model.HealthServerceModel;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.modelimpl.HealthServerceModelImpl;
import com.moe.wl.ui.main.presenter.HealthServercePresenter;
import com.moe.wl.ui.main.view.HealthServerceView;

//import android.support.v7.widget.DividerItemDecoration;


/**
 * 医疗服务主页面
 * Created by 我的电脑 on 2017/8/15 0015.
 */

public class HealthServerceActivity extends BaseActivity<HealthServerceModel, HealthServerceView, HealthServercePresenter> implements HealthServerceView {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;
    @BindView(R.id.iv_serverce)
    ImageView ivServerce;
    @BindView(R.id.serverce_gridview)
    NoSlidingGridView gridView;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.rv_serverce)
    RecyclerView rvServerce;

    private HealthServiceGridAdapter gridAdapter;
    private HealthServiceRvAdapter rvAdapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_health_serverce);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData();
        initTitle();
        initGridView();
        initRecycler();
        clickMore();
    }

    @Override
    public void success(HealthServerceHomeBean hshomeBean) {
        if (hshomeBean != null) {
            GlideLoading.getInstance().loadImgUrlNyImgLoader(this, hshomeBean.getPicture(), ivServerce);
            rvAdapter.setData(hshomeBean.getInfolist(), 1);
        }
    }

    private void clickMore() {
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthServerceActivity.this, MoreHealthConsultActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecycler() {
        rvAdapter = new HealthServiceRvAdapter(this);
        rvServerce.setLayoutManager(new NoScrollLinearLayoutManager(this));
        rvServerce.setAdapter(rvAdapter);
    }

    private void initGridView() {
        gridAdapter = new HealthServiceGridAdapter(this);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://预约挂号
                        Intent intent = new Intent(HealthServerceActivity.this, RegistrationActivity.class);
                        startActivity(intent);
                        break;

                    case 1://健康档案
                        break;

                    case 2://专家坐诊
                        Intent intent3 = new Intent(HealthServerceActivity.this, ExpertsVisitActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }

    private void initTitle() {
        titleBar.setTitle("医疗服务");
        titleBar.setBack(true);
    }

    @Override
    public HealthServercePresenter createPresenter() {
        return new HealthServercePresenter();
    }

    @Override
    public HealthServerceModel createModel() {
        return new HealthServerceModelImpl();
    }

}