package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ServiceOrderActivity;

public class SpPaySuccessActivity extends AppCompatActivity {

    @BindView(R.id.pay_title)
    TitleBar payTitle;
    @BindView(R.id.tv_need_pay)
    TextView tvNeedPay;
    @BindView(R.id.tv_jiaofei_type)
    TextView tvJiaofeiType;
    @BindView(R.id.tv_jiaoyi_time)
    TextView tvJiaoyiTime;
    @BindView(R.id.tv_pay_mothed)
    TextView tvPayMothed;
    @BindView(R.id.tv_jiaoyidanhao)
    TextView tvJiaoyidanhao;
    @BindView(R.id.tv_check_order)
    TextView tvCheckOrder;
    @BindView(R.id.activity_sp_pay_success)
    LinearLayout activitySpPaySuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_pay_success);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付成功");
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        startActivity(intent);
    }
}
