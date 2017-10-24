package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class ReserveInfoActivity extends MyBaseActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    EditText tvUserPhone;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.activity_reserve_info)
    LinearLayout activityReserveInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_info);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("预定信息");
        if ("".equals(SharedPrefHelper.getInstance().getRealName()) || SharedPrefHelper.getInstance().getRealName() == null) {
            tvUserName.setText(SharedPrefHelper.getInstance().getNickname());
        } else {
            tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
        }
        tvUserPhone.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        tvUserPhone.setSelection(SharedPrefHelper.getInstance().getPhoneNumber().length());
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        if (tvUserPhone.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "手机号不能为空！");
            return;
        }
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        intent.putExtra("PhoneNumber", tvUserPhone.getText().toString().trim());
        intent.putExtra("Data", getIntent().getSerializableExtra("Data"));
        intent.putExtra("TimeID", getIntent().getIntExtra("TimeID", 0));
        intent.putExtra("Time", getIntent().getStringExtra("Time"));
        startActivity(intent);
    }
}
