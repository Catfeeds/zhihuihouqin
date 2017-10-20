package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistSuccessActivity extends AppCompatActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_to_write_info)
    TextView tvToWriteInfo;
    @BindView(R.id.activity_regist_success)
    LinearLayout activityRegistSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_success);
        ButterKnife.bind(this);
        initTitle();
        showhintDialog();
    }

    private void showhintDialog() {
        new AlertDialog(RegistSuccessActivity.this).builder().setTitle("提示")
                .setMsg("注册完成后,需继续填写个人身份信息审核后,帐号方能使用")
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegistSuccessActivity.this, IdentityActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }

    private void initTitle() {
        mTitleBar.setBack(true);
        mTitleBar.setTitle("注册成功");
    }

    @OnClick(R.id.tv_to_write_info)
    public void onViewClicked() {
        Intent intent = new Intent(RegistSuccessActivity.this, IdentityActivity.class);
        startActivity(intent);
    }
}
