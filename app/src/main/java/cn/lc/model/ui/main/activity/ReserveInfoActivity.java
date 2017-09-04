package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class ReserveInfoActivity extends AppCompatActivity {

    @BindView(R.id.reserve_info_title)
    TitleBar titleBar;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.activity_reserve_info)
    RelativeLayout activityReserveInfo;

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
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        Intent intent = new Intent(this, ConfirmOrderActivity.class);
        startActivity(intent);
    }
}
