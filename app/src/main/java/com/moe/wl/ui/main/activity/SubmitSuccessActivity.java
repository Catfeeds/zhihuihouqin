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
            ivSubmitOrPay.setImageResource(R.drawable.selected);
        } else {
            titleBar.setTitle("提交成功");
            ivSubmitOrPay.setImageResource(R.drawable.unselected);
        }
    }

    @OnClick(R.id.tv_check_order)
    public void onViewClicked() {
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", 0);
        intent.putExtra("from", getIntent().getIntExtra("from", 0));
        switch (getIntent().getIntExtra("from", 0)) {
            case Constants.PROPERRY:// 物业维修
                intent.putExtra("state", orderRepairs);
                break;

            case Constants.OFFICESUPPLIES:// 办公用品
                intent.putExtra("state", orderOfficeSupplies);
                break;

            case Constants.ORDERMEAL:// 订餐订单
                intent.putExtra("state", orderFood);
                break;

            case Constants.HAIRCUTS:// 理发订单
                intent.putExtra("state", orderHaircuts);
                break;

            case Constants.ORDERWATER:// 订水订单
                intent.putExtra("state", orderWater);
                break;

            case Constants.MEDICAL:// 医疗订单
                intent.putExtra("state", orderMedical);
                break;

            case Constants.EXPERTS:// 专家坐诊
                intent.putExtra("state", orderExperts);
                break;

            case Constants.DRYCLEANER://洗衣店
                intent.putExtra("state", orderDryCleaner);
                break;

            case Constants.BOOK: // 图书馆
                intent.putExtra("state", orderBook);
                break;

            case Constants.VEGETABLE: // 净菜
                intent.putExtra("state", orderVegetable);
                break;

            case Constants.CONFERENCE: // 会议室
                intent.putExtra("state", orderConference);
                break;

            default:
                intent.putExtra("state", orderRepairs);
                break;
        }
        startActivity(intent);
    }

    // 我的报修 标题
    private static final String orderRepairs = "待接单,已接单,已完成,待评价,已取消";
    // 办公用品 标题
    private static final String orderOfficeSupplies = "待发货,配送中,已完成,待评价,已取消";
    // 我的订餐 标题
    private static final String orderFood = "已下单,已完成,待评价,已取消";
    // 理发订单 标题
    private static final String orderHaircuts = "已预约,服务中,已完成,待评价,已取消";
    // 订水订单 标题
    private static final String orderWater = "已下单,配送中,已完成,待评价,已取消";
    // 医疗订单 标题
    private static final String orderMedical = "已预约,服务中,已完成,待评价,已取消";
    // 专家坐诊 标题
    private static final String orderExperts = "已预约,服务中,已完成,待评价,已取消";
    //  洗衣店 标题
    private static final String orderDryCleaner = "已下单,服务中,已完成,待评价,已取消";
    // 图书订单 标题
    private static final String orderBook = "已预订,已借阅,已归还,待评价,已取消";
    // 净菜订单 标题
    private static final String orderVegetable = "已下单,已完成,待评价,已取消";
    // 会议室
    private static final String orderConference = "待服务,服务中,已完成,待评价,已取消";
}
