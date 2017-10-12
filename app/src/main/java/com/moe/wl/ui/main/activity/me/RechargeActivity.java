package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.mywidget.BottomRechargeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class RechargeActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_scanner_amount)
    EditText etScannerAmount;
    @BindView(R.id.tv_confirm_pay)
    TextView tvConfirmPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("充值");
        title.setTitleRight("充值记录");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RechargeActivity.this,RechargeRecordActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.tv_confirm_pay)
    public void onViewClicked() {
        String amount = etScannerAmount.getText().toString().trim();
        if(TextUtils.isEmpty(amount)){
            ToastUtil.showToast(this,"请输入充值金额");
            return ;
        }
        BottomRechargeDialog dialog=new BottomRechargeDialog(this,R.style.dialog_style);
        dialog.setAmount(amount);
        dialog.show();
    }
}
