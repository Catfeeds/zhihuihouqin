package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.SubscribeInfoView;

import butterknife.ButterKnife;

/**
 * 填写预订信息
 */
public class SubscribeInfoActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener,SubscribeInfoView {

    private LinearLayout ll_back;
    private TextView tv_finish;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_finish.setOnClickListener(this);

        initData();

    }

    @Override
    public SubscribeInfoPresenter createPresenter() {
        return new SubscribeInfoPresenter();
    }

    @Override
    public SubscribeInfoModel createModel() {
        return new SubscribeInfoModelImpl();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_finish:
                getPresenter().subscribeInfo();
                break;
        }
    }

    private void initData() {


    }


    @Override
    public void submit() {
        startActivity(new Intent(this,AffirmOrderActivity.class));
        finish();
    }

}
