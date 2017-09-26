package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

import com.moe.wl.ui.main.adapter.FmPagerAdapter;

public class MyCollectActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_bianji)
    TextView tvBianji;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_collect)
    ViewPager vpCollect;
    @BindView(R.id.activity_my_collect)
    LinearLayout activityMyCollect;
    private List<String> tabs = Arrays.asList("公告", "办公用品", "作品", "图书", "医生",
            "专家", "活动", "发型师");
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        ButterKnife.bind(this);
        initFragment();
        initTitle();

    }

    private void initFragment() {
        fragments = new ArrayList<>();
      /*  fragments.add(new McNoticeFragment());
        fragments.add(new McOfficeFragment());
        fragments.add(new McWorksFragment());
        fragments.add(new McBookFragment());
        fragments.add(new McDoctorFragment());
        fragments.add(new McExpertsFragment());
        fragments.add(new McActFragment());
        fragments.add(new McBarberFragment());*/
    }

    private void initTitle() {
        title.setTitle("我的收藏");
        title.setBack(true);
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(getSupportFragmentManager());
        vpCollect.setAdapter(pagerAdapter);
        pagerAdapter.setFragments(fragments, tabs);
    }

    @OnClick(R.id.tv_bianji)
    public void onViewClicked() {
    }
}
