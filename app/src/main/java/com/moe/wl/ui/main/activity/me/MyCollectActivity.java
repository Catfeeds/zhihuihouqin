package com.moe.wl.ui.main.activity.me;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.FmPagerAdapter;
import com.moe.wl.ui.main.fragment.McNoticeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCollectActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_collect)
    ViewPager vpCollect;
    @BindView(R.id.activity_my_collect)
    LinearLayout activityMyCollect;
    private List<String> tabs = Arrays.asList("公告", "办公用品", "理发作品", "图书", "专家",
            "活动", "发型师");
    // type收藏类型 ： 1: 公告，2：办公，3：作品，4：图书，5：医生，6：活动，7：发型师
    // 8:健康资讯 9专家 10营养套餐 11办公用品
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

        fragments.add(McNoticeFragment.getInstance(1));
        fragments.add(McNoticeFragment.getInstance(11));
        fragments.add(McNoticeFragment.getInstance(3));
        fragments.add(McNoticeFragment.getInstance(4));
        fragments.add(McNoticeFragment.getInstance(9));
        fragments.add(McNoticeFragment.getInstance(6));
        fragments.add(McNoticeFragment.getInstance(7));

    }

    private void initTitle() {
        title.setTitle("我的收藏");
        title.setBack(true);
        title.setTitleRight("编辑");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/10/9 0009 编辑
            }
        });
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(getSupportFragmentManager());
        vpCollect.setAdapter(pagerAdapter);
        tab.setupWithViewPager(vpCollect);
        pagerAdapter.setFragments(fragments, tabs);
    }

}
