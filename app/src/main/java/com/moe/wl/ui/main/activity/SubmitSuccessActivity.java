package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

public class SubmitSuccessActivity extends AppCompatActivity {

    @BindView(R.id.heath_serverce_title)
    TitleBar titleBar;
    @BindView(R.id.activity_sbumit_success)
    LinearLayout activitySbumitSuccess;
    @BindView(R.id.tv_submit_success)
    TextView tvSubmitSuccess;
    @BindView(R.id.tv_check_order)
    TextView tvCheckOrder;
    @BindView(R.id.iv_submit_or_pay)
    ImageView ivSubmitOrPay;
    @BindView(R.id.tv_book_submit_des)
    TextView tvBookSubmitDes;
    private boolean isPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbumit_success);
        ButterKnife.bind(this);
        initTitle();
        //设置字体加粗
        tvSubmitSuccess.setTypeface(Typeface.DEFAULT_BOLD);
        processShow();
    }

    private void processShow() {
     /*   Intent intent = getIntent();
        isPay = intent.getBooleanExtra("bookSubmit", false);*/

    }

    private void initTitle() {

        titleBar.setBack(true);
        if (isPay) {
            titleBar.setTitle("支付成功");
            ivSubmitOrPay.setImageResource(R.drawable.selected);
        } else {
            titleBar.setTitle("提交成功");
            ivSubmitOrPay.setImageResource(R.drawable.unselected);
        }
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        startActivity(intent);
    }
}
