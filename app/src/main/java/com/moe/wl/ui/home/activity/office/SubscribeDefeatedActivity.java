package com.moe.wl.ui.home.activity.office;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.office.TestModel;
import com.moe.wl.ui.home.modelimpl.office.TestModelImpl;
import com.moe.wl.ui.home.presenter.office.TestPresenter;
import com.moe.wl.ui.home.view.office.TestView;

import butterknife.ButterKnife;

/**
 * 预订失败
 */
public class SubscribeDefeatedActivity extends BaseActivity<TestModel, TestView, TestPresenter> implements View.OnClickListener,TestView {

    private LinearLayout ll_back;
    private TextView tv_submit;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_affirm_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(this);

        initData();

    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public TestModel createModel() {
        return new TestModelImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_submit:
                break;
        }
    }

    private void initData() {


    }


}
