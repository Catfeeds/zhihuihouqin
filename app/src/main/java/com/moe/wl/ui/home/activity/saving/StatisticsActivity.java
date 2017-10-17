package com.moe.wl.ui.home.activity.saving;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.home.adapter.saving.MyFragmentPagerAdapter;
import com.moe.wl.ui.home.fragment.ElectroStatistcsFrgment;
import com.moe.wl.ui.home.fragment.WaterStatistcsFragment;
import com.moe.wl.ui.home.model.saving.StatisticsModel;
import com.moe.wl.ui.home.modelimpl.saving.StatisticsModelImpl;
import com.moe.wl.ui.home.presenter.saving.StatisticsPresenter;
import com.moe.wl.ui.home.view.saving.MenuPopwindow;
import com.moe.wl.ui.home.view.saving.StatisticsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.view.hack.HackyViewPager;

/**
 * 消耗统计
 */
public class StatisticsActivity extends BaseActivity<StatisticsModel, StatisticsView, StatisticsPresenter> implements StatisticsView, View.OnClickListener {

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
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_electro)
    TextView tvElectro;
    @BindView(R.id.tv_water)
    TextView tvWater;

    private List<Fragment> list_fragment;     //fragment列表
    private List<String> list_Title;          //tab名的列表
    private MyFragmentPagerAdapter adapter;
    private MenuPopwindow popwindow;

    @Override
    public StatisticsPresenter createPresenter() {
        return new StatisticsPresenter();
    }

    @Override
    public StatisticsModel createModel() {
        return new StatisticsModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_statistics);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {

        tabs.addTab(tabs.newTab().setText("用电"), true);//添加 Tab,默认选中
        tabs.addTab(tabs.newTab().setText("用水"), false);//添加 Tab,默认不选中

        tabs.setupWithViewPager(viewpager);

        ElectroStatistcsFrgment f1 = new ElectroStatistcsFrgment();
        WaterStatistcsFragment f2 = new WaterStatistcsFragment();

        list_Title = new ArrayList<>();
        list_Title.add("用电");
        list_Title.add("用水");
        list_fragment = new ArrayList<>();
        list_fragment.add(f1);
        list_fragment.add(f2);

        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), list_fragment, list_Title);
        viewpager.setAdapter(adapter);

    }


    @OnClick({R.id.ll_back,R.id.ll_right})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_right:
                if (popwindow==null){
                    popwindow=new MenuPopwindow(this, new String[]{"按年查看", "按月查看","按日查看"}, new MenuPopwindow.MyOnClick() {
                        @Override
                        public void click(String s) {
                            tvRight.setText(s);
                        }
                    });
                }
                popwindow.showPopupWindow(this,llRight);
                break;

        }
    }


    @Override
    public void setData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
