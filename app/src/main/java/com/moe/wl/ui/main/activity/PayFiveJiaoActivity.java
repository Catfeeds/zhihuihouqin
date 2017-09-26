package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

public class PayFiveJiaoActivity extends AppCompatActivity {

    @BindView(R.id.pay_title)
    TitleBar payTitle;
    @BindView(R.id.iv_balance_pay)
    ImageView ivBalancePay;
    @BindView(R.id.tv_balance_pay)
    TextView tvBalancePay;
    @BindView(R.id.iv_balance_pay_check)
    ImageView ivBalancePayCheck;
    @BindView(R.id.tv_available_balance)
    TextView tvAvailableBalance;
    @BindView(R.id.iv_weixin_pay)
    ImageView ivWeixinPay;
    @BindView(R.id.tv_weixin_pay)
    TextView tvWeixinPay;
    @BindView(R.id.iv_weixin_pay_check)
    ImageView ivWeixinPayCheck;
    @BindView(R.id.iv_alpay)
    ImageView ivAlpay;
    @BindView(R.id.tv_alpay)
    TextView tvAlpay;
    @BindView(R.id.iv_alpay_check)
    ImageView ivAlpayCheck;
    @BindView(R.id.bt_confirm)
    Button btConfirm;
    @BindView(R.id.iv_daijinquan)
    ImageView ivDaijinquan;
    @BindView(R.id.tv_daijinquan)
    TextView tvDaijinquan;
    @BindView(R.id.iv_daijinjuan_check)
    ImageView ivDaijinjuanCheck;
    @BindView(R.id.rl_daijinquan_pay)
    RelativeLayout rlDaijinquanPay;
    @BindView(R.id.activity_pay_five_jiao)
    LinearLayout activityPayFiveJiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_five_jiao);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        payTitle.setBack(true);
        payTitle.setTitle("支付");
    }

    @OnClick({R.id.iv_daijinjuan_check,R.id.iv_balance_pay_check, R.id.iv_weixin_pay_check, R.id.iv_alpay_check, R.id.bt_confirm})
    public void onViewClicked(View view) {
        unCheckPay();
        switch (view.getId()) {
            case R.id.iv_daijinjuan_check://代金券支付
                ivDaijinjuanCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_balance_pay_check://余额支付
                ivBalancePayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_weixin_pay_check://微信支付
                ivWeixinPayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.iv_alpay_check://支付宝支付
                ivAlpayCheck.setImageResource(R.drawable.selected);
                break;
            case R.id.bt_confirm://确认支付，去顶支付成功进入支付成功界面
                Intent intent = new Intent(this, SubmitSuccessActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void unCheckPay() {
        ivDaijinjuanCheck.setImageResource(R.drawable.unselected);
        ivBalancePayCheck.setImageResource(R.drawable.unselected);
        ivWeixinPayCheck.setImageResource(R.drawable.unselected);
        ivAlpayCheck.setImageResource(R.drawable.unselected);
    }

}
