package com.moe.wl.ui.home.activity.office;

import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.SubscribeInfoView;

import butterknife.ButterKnife;

/**
 * 提交订单成功
 */
public class SubmitSuccActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener,SubscribeInfoView {

    private LinearLayout ll_back;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_submit_succ);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);

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
        }
    }

    private void initData() {


    }


    @Override
    public void submit() {
        
    }

}
