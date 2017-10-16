package com.moe.wl.ui.home.activity.office;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.home.model.SubscribeInfoModel;
import com.moe.wl.ui.home.modelimpl.SubscribeInfoModelImpl;
import com.moe.wl.ui.home.presenter.SubscribeInfoPresenter;
import com.moe.wl.ui.home.view.SubscribeInfoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 填写预订信息
 */
public class SubscribeInfoActivity extends BaseActivity<SubscribeInfoModel, SubscribeInfoView, SubscribeInfoPresenter> implements View.OnClickListener, SubscribeInfoView {


    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_startDate)
    TextView tvStartDate;
    @BindView(R.id.tv_endDate)
    TextView tvEndDate;
    @BindView(R.id.tv_startHour)
    TextView tvStartHour;
    @BindView(R.id.tv_startMinute)
    TextView tvStartMinute;
    @BindView(R.id.tv_endHour)
    TextView tvEndHour;
    @BindView(R.id.tv_endMinute)
    TextView tvEndMinute;
    @BindView(R.id.lv_equipment)
    NoSlidingGridView lvEquipment;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.et_leader)
    EditText etLeader;
    @BindView(R.id.tv_enclosure)
    TextView tvEnclosure;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.ll_startDate)
    LinearLayout llStartDate;
    @BindView(R.id.ll_endDate)
    LinearLayout llEndDate;
    @BindView(R.id.ll_startTime)
    LinearLayout llStartTime;
    @BindView(R.id.ll_endTime)
    LinearLayout llEndTime;
    @BindView(R.id.ll_type)
    LinearLayout llType;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_subscribe);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

    }


    @Override
    public SubscribeInfoPresenter createPresenter() {
        return new SubscribeInfoPresenter();
    }

    @Override
    public SubscribeInfoModel createModel() {
        return new SubscribeInfoModelImpl();
    }

    @OnClick({R.id.ll_back,R.id.ll_startDate,R.id.ll_endDate,R.id.ll_startTime,R.id.ll_endTime,
            R.id.ll_type,R.id.tv_enclosure,R.id.tv_finish})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_startDate:  //开始日期

                break;
            case R.id.ll_endDate:  //结束日期

                break;
            case R.id.ll_startTime:  //开始时间

                break;
            case R.id.ll_endTime:  //结束时间

                break;
            case R.id.ll_type:  //会议类型

                break;
            case R.id.tv_enclosure:  //附件上传

                break;
            case R.id.tv_finish:
                getPresenter().subscribeInfo();
                break;
        }
    }


    @Override
    public void submit() {
        startActivity(new Intent(this, AffirmOrderActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
