package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.spfs.SharedPrefHelper;

import mvp.cn.util.ToastUtil;

public class ReserveInfoActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    EditText tvUserPhone;
    @BindView(R.id.tv_confirm)
    Button tvConfirm;
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
        tvUserName.setText(SharedPrefHelper.getInstance().getRealName());
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
        startActivity(intent);
    }
}