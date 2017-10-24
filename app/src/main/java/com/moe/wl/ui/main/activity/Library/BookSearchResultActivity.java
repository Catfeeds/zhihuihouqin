package com.moe.wl.ui.main.activity.Library;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.adapter.FmPagerAdapter;
import com.moe.wl.ui.main.fragment.BookSearchResultFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：图书搜索结果页面
 * 作者：Shixhe On 2017/10/24 0024
 */

public class BookSearchResultActivity extends MyBaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_book)
    ViewPager vpBook;

    private String keyword;
    private String type;

    private List<Fragment> fragments;
    private FmPagerAdapter fmPagerAdapter;
    private List<String> tabs = Arrays.asList("上架时间", "热书排名", "好评排名");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search_result);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        titleBar.setTitle("搜索结果");
        titleBar.setBack(true);
        keyword = getIntent().getStringExtra("keyword");
        type = getIntent().getStringExtra("type");
        fragments = new ArrayList<>();
        fmPagerAdapter = new FmPagerAdapter(getSupportFragmentManager());
        vpBook.setAdapter(fmPagerAdapter);
        tab.setupWithViewPager(vpBook);
        for (int i = 1; i < 4; i++) {
            fragments.add(BookSearchResultFragment.getInstant(keyword, type, i));
        }
        fmPagerAdapter.setFragments(fragments, tabs);
    }
}
