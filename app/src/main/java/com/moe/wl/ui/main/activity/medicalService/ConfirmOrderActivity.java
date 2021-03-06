package com.moe.wl.ui.main.activity.medicalService;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.SubmitSuccessActivity;
import com.moe.wl.ui.main.bean.ExpertDetailBean;
import com.moe.wl.ui.main.bean.ExpertOrderBean;
import com.moe.wl.ui.main.model.ExpertOrderModel;
import com.moe.wl.ui.main.modelimpl.ExpertOrderModelImpl;
import com.moe.wl.ui.main.presenter.ExpertOrderPresenter;
import com.moe.wl.ui.main.view.ExpertOrderView;
import com.moe.wl.ui.mywidget.StarBar;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class ConfirmOrderActivity extends BaseActivity<ExpertOrderModel, ExpertOrderView, ExpertOrderPresenter> implements ExpertOrderView {


    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    EditText tvUserPhone;
    @BindView(R.id.reservation_num)
    TextView reservationNum;
    @BindView(R.id.iv_doc_photo)
    ImageView ivDocPhoto;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_position)
    TextView tvDoctorPosition;
    @BindView(R.id.ratingBar)
    StarBar ratingBar;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.tv_seeing)
    TextView tvSeeing;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.time)
    TextView time;

    private ExpertDetailBean.ExpertEntity entity;

    private int timeID = 0;
    private int id = 0;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_confirm_order);
    }

    @Override
    public void initView() {
        initTitle();
    }

    private void initTitle() {
        timeID = getIntent().getIntExtra("TimeID", 0);
        entity = (ExpertDetailBean.ExpertEntity) getIntent().getSerializableExtra("Data");
        id = entity.getId();
        titleBar.setBack(true);
        titleBar.setTitle("确认订单");
        GlideLoading.getInstance().loadImgUrlHeader(this, entity.getPhoto(), ivDocPhoto, R.mipmap.ic_default_square);
        if ("".equals(SharedPrefHelper.getInstance().getRealName()) || SharedPrefHelper.getInstance().getRealName() == null) {
            tvUserName.setText(SharedPrefHelper.getInstance().getNickname());
        } else {
            tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
        }
        time.setText(getIntent().getStringExtra("Time"));
        tvUserPhone.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        tvUserPhone.setSelection(SharedPrefHelper.getInstance().getPhoneNumber().length());
        tvDoctorName.setText(entity.getRealname());
        tvDoctorPosition.setText(entity.getPositionname());
        tvSeeing.setText(entity.getConsultcount() + "");
        tvHospital.setText(entity.getHospitalName());
        ratingBar.setIntegerMark(false);
        ratingBar.setStarMark((float) entity.getScore());
        tvStarNum.setText(entity.getScore() + "");
        reservationNum.setText((entity.getInvitetotalcount() - entity.getRemaincount() + 1) + "/" + entity.getInvitetotalcount());
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                submit();
                break;
        }
    }

    private void submit() {
        if (id == 0) {
            ToastUtil.showToast(this, "数据错误，请重试！");
            return;
        }
        if (timeID == 0) {
            ToastUtil.showToast(this, "预约时间出错，请重试！");
            return;
        }
        getPresenter().submitExpertOrder(id, timeID);
    }

    @Override
    public void submitExpertOrderSucc(ExpertOrderBean bean) {
        Intent intent = new Intent(this, SubmitSuccessActivity.class);
        intent.putExtra("from", Constants.EXPERTS);
        intent.putExtra("index", 0);
        startActivity(intent);
        finish();
    }

    @Override
    public ExpertOrderPresenter createPresenter() {
        return new ExpertOrderPresenter();
    }

    @Override
    public ExpertOrderModel createModel() {
        return new ExpertOrderModelImpl();
    }
}
