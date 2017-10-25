package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayDepositActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_pay)
    TextView tvPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_deposit);
        ButterKnife.bind(this);
        intiTitle();
    }

    private void intiTitle() {
        title.setBack(true);
        title.setTitle("缴纳押金");
    }

    @OnClick(R.id.tv_pay)
    public void onViewClicked() {
        // TODO: 2017/10/16 0016 需要传递ordercode,ordertype
        Intent intent=new Intent(this, PayFiveJiaoActivity.class);
        float pay=50;
        intent.putExtra("pay",pay);
        intent.putExtra("from", Constants.ORDERWATER);
        startActivity(intent);
    }
}
