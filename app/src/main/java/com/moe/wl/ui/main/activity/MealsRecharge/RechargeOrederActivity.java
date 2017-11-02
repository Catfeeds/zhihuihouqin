package com.moe.wl.ui.main.activity.MealsRecharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.utils.TabIndicator;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RechargeOrderAdapter;
import com.moe.wl.ui.main.fragment.RechargeOrderFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeOrederActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.iv_wen)
    ImageView ivWen;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.activity_recharge_oreder)
    LinearLayout activityRechargeOreder;
    private List<String> tabname = Arrays.asList("正在进行", "历史订单");
    private List<Fragment> fragments;
    private RechargeOrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        setContentView(R.layout.activity_recharge_oreder);
        ButterKnife.bind(this);
        title.setBack(true);
        title.setTitle("充值订单");
        fragments = new ArrayList<>();
        fragments.add(RechargeOrderFragment.getInstance(1));//充值中
        fragments.add(RechargeOrderFragment.getInstance(0));//交易关闭
        orderAdapter = new RechargeOrderAdapter(getSupportFragmentManager());
        vp.setAdapter(orderAdapter);
        orderAdapter.setData(tabname, fragments);
        tab.setupWithViewPager(vp);
        tab.post(new Runnable() {
            @Override
            public void run() {
                TabIndicator.setIndicator(tab, 60, 60);//设定指示器的宽
            }
        });
    }

    @OnClick(R.id.iv_wen)
    public void onViewClicked() {
        Intent intent=new Intent(this,RechargeContentAct.class);
        startActivity(intent);
    }
}