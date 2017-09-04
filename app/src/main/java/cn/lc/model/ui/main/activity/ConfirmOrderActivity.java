package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class ConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
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
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_confirm_order)
    LinearLayout activityConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确认订单");
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        Intent intent=new Intent(this,SubmitSuccessActivity.class);
        startActivity(intent);
    }
}
