package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.OtherUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.bean.ExpertDetailBean;
import cn.lc.model.ui.main.bean.ExpertOrderBean;
import cn.lc.model.ui.main.model.ExpertOrderModel;
import cn.lc.model.ui.main.modelimpl.ExpertOrderModelImpl;
import cn.lc.model.ui.main.presenter.ExpertOrderPresenter;
import cn.lc.model.ui.main.view.ExpertOrderView;
import mvp.cn.util.ToastUtil;

public class ConfirmOrderActivity extends BaseActivity<ExpertOrderModel, ExpertOrderView, ExpertOrderPresenter> implements ExpertOrderView {


    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.reservation_num)
    TextView reservationNum;
    @BindView(R.id.iv_doc_photo)
    ImageView ivDocPhoto;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_doctor_position)
    TextView tvDoctorPosition;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tv_star_num)
    TextView tvStarNum;
    @BindView(R.id.tv_seeing)
    TextView tvSeeing;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;

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
        tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
        tvUserPhone.setText(getIntent().getStringExtra("PhoneNumber"));
        tvDoctorName.setText(entity.getRealname());
        tvDoctorPosition.setText(entity.getPositionname());
        tvSeeing.setText(entity.getConsultcount() + "");
        tvHospital.setText(entity.getHospitalName());
        OtherUtils.ratingBarColor(ratingBar);
        ratingBar.setRating((float) entity.getScore());
        tvStarNum.setText(entity.getScore() + "");
        if (entity.getRemaincount() != null)
            reservationNum.setText(Integer.parseInt(entity.getRemaincount()) + 1 + "/" + entity.getInvitetotalcount());
        else
            reservationNum.setText(entity.getRemaincount() + "/" + entity.getInvitetotalcount());
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
