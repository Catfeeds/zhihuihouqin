package com.moe.wl.ui.home.activity.saving;

import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.saving.RankingModel;
import com.moe.wl.ui.home.modelimpl.saving.RankingModelImpl;
import com.moe.wl.ui.home.presenter.saving.RankingPresenter;
import com.moe.wl.ui.home.view.saving.RankingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消耗排行
 */
public class RankingActivity extends BaseActivity<RankingModel, RankingView, RankingPresenter> implements RankingView,View.OnClickListener{

    @BindView(R.id.ll_back)
    LinearLayout llBack;


    @Override
    public RankingPresenter createPresenter() {
        return new RankingPresenter();
    }

    @Override
    public RankingModel createModel() {
        return new RankingModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_ranking);
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
