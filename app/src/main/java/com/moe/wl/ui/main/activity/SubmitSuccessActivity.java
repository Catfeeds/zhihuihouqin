package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private boolean isPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbumit_success);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        ButterKnife.bind(this);
        initTitle();
        //设置字体加粗
        tvSubmitSuccess.setTypeface(Typeface.DEFAULT_BOLD);
        Intent intent = getIntent();
        isPay = intent.getBooleanExtra("isPay", false);
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
            ivSubmitOrPay.setImageResource(R.drawable.submit_success);
        } else {
            titleBar.setTitle("提交成功");
            ivSubmitOrPay.setImageResource(R.drawable.submit_success);
        }
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", 0);
        intent.putExtra("from", getIntent().getIntExtra("from", 0));
        switch (getIntent().getIntExtra("from", 0)) {
            case Constants.PROPERRY:// 物业维修
                intent.putExtra("state", Constants.orderRepairs);
                break;

            case Constants.OFFICESUPPLIES:// 办公用品
                intent.putExtra("state", Constants.orderOfficeSupplies);
                break;

            case Constants.ORDERMEAL:// 订餐订单
                intent.putExtra("state", Constants.orderFood);
                break;

            case Constants.HAIRCUTS:// 理发订单
                intent.putExtra("state", Constants.orderHaircuts);
                break;

            case Constants.ORDERWATER:// 订水订单
                intent.putExtra("state", Constants.orderWater);
                break;

            case Constants.MEDICAL:// 医疗订单
                intent.putExtra("state", Constants.orderMedical);
                break;

            case Constants.EXPERTS:// 专家坐诊
                intent.putExtra("state", Constants.orderExperts);
                break;

            case Constants.DRYCLEANER://洗衣店
                intent.putExtra("state", Constants.orderDryCleaner);
                break;

            case Constants.BOOK: // 图书馆
                intent.putExtra("state", Constants.orderBook);
                break;

            case Constants.VEGETABLE: // 净菜
                intent.putExtra("state", Constants.orderVegetable);
                break;

            case Constants.CONFERENCE: // 会议室
                intent.putExtra("state", Constants.orderConference);
                break;

            case Constants.VISITORS: // 来访人员
                intent.putExtra("state", Constants.orderVisitors);
                break;

            default:
                intent.putExtra("state", Constants.orderRepairs);
                break;
        }
        startActivity(intent);
        finish();
    }
}
