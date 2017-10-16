package com.moe.wl.ui.home.activity.saving;

import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.saving.StatisticsModel;
import com.moe.wl.ui.home.modelimpl.saving.StatisticsModelImpl;
import com.moe.wl.ui.home.presenter.saving.StatisticsPresenter;
import com.moe.wl.ui.home.view.saving.StatisticsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消耗统计
 */
public class StatisticsActivity extends BaseActivity<StatisticsModel, StatisticsView, StatisticsPresenter> implements StatisticsView ,View.OnClickListener{

    @BindView(R.id.ll_back)
    LinearLayout llBack;


    @Override
    public StatisticsPresenter createPresenter() {
        return new StatisticsPresenter();
    }

    @Override
    public StatisticsModel createModel() {
        return new StatisticsModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_statistics);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
    }

    @OnClick({R.id.ll_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    @Override
    public void setData() {

    }
}
