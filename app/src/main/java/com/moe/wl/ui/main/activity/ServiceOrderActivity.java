package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.ui.main.adapter.MyPagerAdapter;
import com.moe.wl.ui.main.fragment.OrderBookFragment;
import com.moe.wl.ui.main.fragment.OrderConferenceFragment;
import com.moe.wl.ui.main.fragment.OrderDryFragment;
import com.moe.wl.ui.main.fragment.OrderExpertFragment;
import com.moe.wl.ui.main.fragment.OrderHairCutFragment;
import com.moe.wl.ui.main.fragment.OrderMealFragment;
import com.moe.wl.ui.main.fragment.OrderMedicalFragment;
import com.moe.wl.ui.main.fragment.OrderOfficeFragment;
import com.moe.wl.ui.main.fragment.OrderRepairFragment;
import com.moe.wl.ui.main.fragment.OrderVegetableFragment;
import com.moe.wl.ui.main.fragment.WaterFragment;
import com.moe.wl.ui.mywidget.OrderPop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceOrderActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.iv_up_or_down)
    ImageView ivUpOrDown;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.service_orider_viewpager)
    ViewPager viewPager;
    @BindView(R.id.view)
    View view;

    private List<Fragment> fragments;
    //    private PopupWindow popupWindow;
    private List<String> states;
    private int from;

    private OrderPop pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        from = intent.getIntExtra("from", -1);
        int index = intent.getIntExtra("index", 0);
        String state = intent.getStringExtra("state");
        String[] arr = state.split(",");
        states = Arrays.asList(arr);
        fragments = new ArrayList<>();
        switch (from) {
            case Constants.PROPERRY:// 物业维修
                fragments.add(OrderRepairFragment.getInstance(0));
                fragments.add(OrderRepairFragment.getInstance(1));
                fragments.add(OrderRepairFragment.getInstance(2));
                fragments.add(OrderRepairFragment.getInstance(3));
                fragments.add(OrderRepairFragment.getInstance(4));
                break;

            case Constants.OFFICESUPPLIES:// 办公用品
                fragments.add(OrderOfficeFragment.getInstance(0));
                fragments.add(OrderOfficeFragment.getInstance(1));
                fragments.add(OrderOfficeFragment.getInstance(2));
                fragments.add(OrderOfficeFragment.getInstance(3));
                fragments.add(OrderOfficeFragment.getInstance(4));
                break;

            case Constants.ORDERMEAL:// 订餐订单
                fragments.add(OrderMealFragment.getInstance(0));
                fragments.add(OrderMealFragment.getInstance(2));
                fragments.add(OrderMealFragment.getInstance(3));
                fragments.add(OrderMealFragment.getInstance(4));
                break;

            case Constants.HAIRCUTS:// 理发订单
                fragments.add(OrderHairCutFragment.getInstance(0));
                fragments.add(OrderHairCutFragment.getInstance(1));
                fragments.add(OrderHairCutFragment.getInstance(2));
                fragments.add(OrderHairCutFragment.getInstance(3));
                fragments.add(OrderHairCutFragment.getInstance(4));
                break;

            case Constants.ORDERWATER:// 订水订单
                fragments.add(WaterFragment.getInstance(0));
                fragments.add(WaterFragment.getInstance(1));
                fragments.add(WaterFragment.getInstance(2));
                fragments.add(WaterFragment.getInstance(3));
                fragments.add(WaterFragment.getInstance(4));
                break;

            case Constants.MEDICAL:// 医疗订单
                fragments.add(OrderMedicalFragment.getInstance(0));
                fragments.add(OrderMedicalFragment.getInstance(1));
                fragments.add(OrderMedicalFragment.getInstance(2));
                fragments.add(OrderMedicalFragment.getInstance(3));
                fragments.add(OrderMedicalFragment.getInstance(4));
                break;

            case Constants.EXPERTS:// 专家坐诊
                fragments.add(OrderExpertFragment.getInstance(0));
                fragments.add(OrderExpertFragment.getInstance(1));
                fragments.add(OrderExpertFragment.getInstance(2));
                fragments.add(OrderExpertFragment.getInstance(3));
                fragments.add(OrderExpertFragment.getInstance(4));
                break;

            case Constants.DRYCLEANER://干洗店
                fragments.add(OrderDryFragment.getInstance(1));
                fragments.add(OrderDryFragment.getInstance(2));
                fragments.add(OrderDryFragment.getInstance(3));
                fragments.add(OrderDryFragment.getInstance(4));
                fragments.add(OrderDryFragment.getInstance(5));
                break;

            case Constants.BOOK: // 图书馆
                fragments.add(OrderBookFragment.getInstance(0));
                fragments.add(OrderBookFragment.getInstance(1));
                fragments.add(OrderBookFragment.getInstance(2));
                fragments.add(OrderBookFragment.getInstance(3));
                fragments.add(OrderBookFragment.getInstance(4));
                break;

            case Constants.VEGETABLE: // 净菜
                fragments.add(OrderVegetableFragment.getInstance(0));
                fragments.add(OrderVegetableFragment.getInstance(2));
                fragments.add(OrderVegetableFragment.getInstance(3));
                fragments.add(OrderVegetableFragment.getInstance(4));
                break;

            case Constants.CONFERENCE: // 会议室
                fragments.add(OrderConferenceFragment.getInstance(0));
                fragments.add(OrderConferenceFragment.getInstance(1));
                fragments.add(OrderConferenceFragment.getInstance(2));
                fragments.add(OrderConferenceFragment.getInstance(3));
                fragments.add(OrderConferenceFragment.getInstance(4));
                break;
        }
        initViewpager();
        viewPager.setCurrentItem(index);
        showPoppupwindow();
    }

    private void showPoppupwindow() {
        pop = new OrderPop(ServiceOrderActivity.this, this);
        llTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.showAsDropDown(view, 0, 0);
                ivUpOrDown.setImageResource(R.drawable.drawer_arrow_down);
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ivUpOrDown.setImageResource(R.drawable.drawer_arrow_up);
            }
        });
    }

    private void initViewpager() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        //
        pagerAdapter.setFragments(fragments, states);
    }

    @OnClick({R.id.back, R.id.ll_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ll_title:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_public_maintain:
                goServiceActivity(0, Constants.PROPERRY, orderRepairs);
                break;
            case R.id.tv_office_supplies:
                goServiceActivity(0, Constants.OFFICESUPPLIES, orderOfficeSupplies);
                break;
            case R.id.tv_clear_vegetables:
                goServiceActivity(0, Constants.VEGETABLE, orderVegetable);
                break;
            case R.id.tv_order_water:
                goServiceActivity(0, Constants.ORDERWATER, orderWater);
                break;
            case R.id.tv_work_meal:
                goServiceActivity(0, Constants.ORDERMEAL, orderFood);
                break;
            case R.id.tv_health_service:
                goServiceActivity(0, Constants.MEDICAL, orderMedical);
                break;
            case R.id.tv_cut_hair:
                goServiceActivity(0, Constants.HAIRCUTS, orderHaircuts);
                break;
            case R.id.tv_dry_cleaner:
                goServiceActivity(0, Constants.DRYCLEANER, orderDryCleaner);
                break;
            case R.id.tv_experts_visit:
                goServiceActivity(0, Constants.EXPERTS, orderExperts);
                break;
            case R.id.tv_book_order:
                goServiceActivity(0, Constants.BOOK, orderBook);
                break;
            case R.id.tv_office:
                goServiceActivity(0, Constants.CONFERENCE, orderConference);
                break;
        }
    }

    /**
     * 跳转到ServiceOrder页面
     *
     * @param index 角标
     * @param from  类别
     * @param state 上方标签
     */
    private void goServiceActivity(int index, int from, String state) {
        pop.dismiss();
        Intent intent = new Intent(this, ServiceOrderActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("from", from);
        intent.putExtra("state", state);
        startActivity(intent);
        finish();
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
