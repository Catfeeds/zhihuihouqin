package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.AffirmOrderModel;
import com.moe.wl.ui.home.modelimpl.AffirmOrderModelImpl;
import com.moe.wl.ui.home.presenter.AffirmOrderPresenter;
import com.moe.wl.ui.home.view.AffirmOrderView;

import butterknife.ButterKnife;

/**
 * 确认订单信息
 */
public class AffirmOrderActivity extends BaseActivity<AffirmOrderModel, AffirmOrderView, AffirmOrderPresenter> implements View.OnClickListener,AffirmOrderView {

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
        getPresenter().orderinfo();

    }

    @Override
    public AffirmOrderPresenter createPresenter() {
        return new AffirmOrderPresenter();
    }

    @Override
    public AffirmOrderModel createModel() {
        return new AffirmOrderModelImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_submit:
                startActivity(new Intent(this,SubmitSuccActivity.class));
                break;
        }
    }

    private void initData() {


    }


    @Override
    public void setData() {

    }
}
