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

/**
 * 我的/我的收藏页面
 */

public class MyCollectActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_collect)
    ViewPager vpCollect;
    @BindView(R.id.activity_my_collect)
    LinearLayout activityMyCollect;
    private List<String> tabs = Arrays.asList("公告", "办公", "理发作品", "图书", "医生", "活动", "发型师" ,"健康资讯", "专家", "营养套餐");
    // 1: 公告，2：办公，3：理发作品，4：图书，5：医生，6：活动，7：发型师 //8:健康资讯 9专家 10营养套餐
    private List<Fragment> fragments;
    private boolean isEdit=false;
    private McNoticeFragment notice;
    private McNoticeFragment office;
    private McNoticeFragment barberProduct;
    private McNoticeFragment book;
    private McNoticeFragment experts;
    private McNoticeFragment activity;
    private McNoticeFragment barber;

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
//1: 公告，2：办公，3：理发作品，4：图书，5：专家，6：活动，7：发型师
        //公告
        notice = McNoticeFragment.getInstance("1");
        //公告
        office = McNoticeFragment.getInstance("2");
        //公告
        barberProduct = McNoticeFragment.getInstance("3");
        //公告
        book = McNoticeFragment.getInstance("4");
        //公告
        experts = McNoticeFragment.getInstance("5");
        //公告
        activity = McNoticeFragment.getInstance("6");
        //公告
        barber = McNoticeFragment.getInstance("7");

        //添加fragment 到集合当中
        fragments.add(notice);
        fragments.add(office);
        fragments.add(barberProduct);
        fragments.add(book);
        fragments.add(experts);
        fragments.add(activity);
        fragments.add(barber);


    }

    private void initTitle() {
        title.setTitle("我的收藏");
        title.setBack(true);
        title.setTitleRight("编辑");
        title.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2017/10/9 0009 编辑
                isEdit = !isEdit;
                if(isEdit){//是正在编辑，可删除
                    title.setTitleRight("删除");
                }else {
                    title.setTitleRight("编辑");
                }
                showCancel(isEdit);

            }
        });
        FmPagerAdapter pagerAdapter = new FmPagerAdapter(getSupportFragmentManager());
        vpCollect.setAdapter(pagerAdapter);
        tab.setupWithViewPager(vpCollect);
        pagerAdapter.setFragments(fragments, tabs);
    }

    private void showCancel(boolean isEdit) {
        for(Fragment f:fragments){
            if(f instanceof McNoticeFragment){
                ((McNoticeFragment) f).setIsEdit(isEdit);
            }
        }
//        notice.setIsEdit(isEdit);
//        barberProduct.setIsEdit(isEdit);

    }

}
