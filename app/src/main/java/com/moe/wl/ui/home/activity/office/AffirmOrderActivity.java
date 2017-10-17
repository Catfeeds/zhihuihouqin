package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.model.office.AffirmOrderModel;
import com.moe.wl.ui.home.modelimpl.office.AffirmOrderModelImpl;
import com.moe.wl.ui.home.presenter.office.AffirmOrderPresenter;
import com.moe.wl.ui.home.view.office.AffirmOrderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.moe.wl.R.id.ll_back;
import static com.moe.wl.R.id.tv_submit;

/**
 * 确认订单信息
 */
public class AffirmOrderActivity extends BaseActivity<AffirmOrderModel, AffirmOrderView, AffirmOrderPresenter> implements View.OnClickListener, AffirmOrderView {

    @BindView(ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_people)
    TextView tvPeople;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_startTime)
    TextView tvStartTime;
    @BindView(R.id.tv_endTime)
    TextView tvEndTime;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_leader)
    TextView tvLeader;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(tv_submit)
    TextView tvSubmit;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_affirm_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

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




    @OnClick({R.id.ll_back,R.id.tv_submit})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case tv_submit:
                startActivity(new Intent(this, SubmitSuccActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void setData() {

    }

}
