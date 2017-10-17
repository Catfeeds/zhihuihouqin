package com.moe.wl.ui.home.activity.saving;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.saving.MyFragmentPagerAdapter;
import com.moe.wl.ui.home.fragment.ElectroRankingFrgment;
import com.moe.wl.ui.home.fragment.WaterRankingFragment;
import com.moe.wl.ui.home.model.saving.RankingModel;
import com.moe.wl.ui.home.modelimpl.saving.RankingModelImpl;
import com.moe.wl.ui.home.presenter.saving.RankingPresenter;
import com.moe.wl.ui.home.view.saving.RankingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.view.hack.HackyViewPager;

/**
 * 消耗排行
 */
public class RankingActivity extends BaseActivity<RankingModel, RankingView, RankingPresenter> implements RankingView, View.OnClickListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.ll_right)
    LinearLayout llRight;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    HackyViewPager viewpager;
    @BindView(R.id.tv_gross)
    TextView tvGross;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_capita)
    TextView tvCapita;
    @BindView(R.id.tv_grossRatio)
    TextView tvGrossRatio;
    @BindView(R.id.tv_areaRatio)
    TextView tvAreaRatio;
    @BindView(R.id.tv_capitaRatio)
    TextView tvCapitaRatio;

    private List<Fragment> list_fragment;     //fragment列表
    private List<String> list_Title;          //tab名的列表
    private MyFragmentPagerAdapter adapter;

    @Override
    public RankingPresenter createPresenter() {
        return new RankingPresenter();
    }

    @Override
    public RankingModel createModel() {
        return new RankingModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_ranking);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

        tabs.addTab(tabs.newTab().setText("用电"), true);//添加 Tab,默认选中
        tabs.addTab(tabs.newTab().setText("用水"), false);//添加 Tab,默认不选中

        tabs.setupWithViewPager(viewpager);

        ElectroRankingFrgment f1 = new ElectroRankingFrgment();
        WaterRankingFragment f2 = new WaterRankingFragment();

        list_Title = new ArrayList<>();
        list_Title.add("用电");
        list_Title.add("用水");
        list_fragment = new ArrayList<>();
        list_fragment.add(f1);
        list_fragment.add(f2);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_Title);
        viewpager.setAdapter(adapter);

    }

    @OnClick({R.id.ll_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    @Override
    public void setData() {
    }

}
