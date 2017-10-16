package com.moe.wl.ui.home.activity.saving;

import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.saving.ComparisonModel;
import com.moe.wl.ui.home.modelimpl.saving.ComparisonModelImpl;
import com.moe.wl.ui.home.presenter.saving.ComparisonPresenter;
import com.moe.wl.ui.home.view.saving.ComparisonView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 消耗对比
 */
public class ComparisonActivity extends BaseActivity<ComparisonModel, ComparisonView, ComparisonPresenter> implements ComparisonView ,View.OnClickListener{

    @BindView(R.id.ll_back)
    LinearLayout llBack;


    @Override
    public ComparisonPresenter createPresenter() {
        return new ComparisonPresenter();
    }

    @Override
    public ComparisonModel createModel() {
        return new ComparisonModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_comparison);
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
