package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.home.model.office.TestModel;
import com.moe.wl.ui.home.modelimpl.office.TestModelImpl;
import com.moe.wl.ui.home.presenter.office.TestPresenter;
import com.moe.wl.ui.home.view.office.TestView;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提交订单成功
 */
public class SubmitSuccActivity extends BaseActivity<TestModel, TestView, TestPresenter> implements TestView {

    //    @BindView(R.id.title)
//    TitleBar title;
    @BindView(R.id.tv_check_order)
    TextView tvCheckOrder;
    @BindView(R.id.ll_back)
    LinearLayout ll_back;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_submit_succ);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
//        title.setBack(true);
//        title.setTitle("提交结果");
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

    private void initData() {
    }

    @OnClick({R.id.tv_check_order, R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.tv_check_order:
                Intent intent = new Intent(this, ServiceOrderActivity.class);
                intent.putExtra("from", Constants.CONFERENCE);
                intent.putExtra("index", 0);
                startActivity(intent);
                break;
        }
    }
}
