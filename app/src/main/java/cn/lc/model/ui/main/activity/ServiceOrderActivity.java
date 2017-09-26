package cn.lc.model.ui.main.activity;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.contant.Constants;
import cn.lc.model.ui.main.adapter.MyPagerAdapter;
import cn.lc.model.ui.main.fragment.BookFragment;
import cn.lc.model.ui.main.fragment.WaitOrderFragment;
import cn.lc.model.ui.mywidget.OrderPop;

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
    private PopupWindow popupWindow;
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
            case Constants.DRYCLEANER://干洗店
                fragments.add(WaitOrderFragment.getInstance(1));
                fragments.add(WaitOrderFragment.getInstance(2));
                fragments.add(WaitOrderFragment.getInstance(3));
                fragments.add(WaitOrderFragment.getInstance(4));
                fragments.add(WaitOrderFragment.getInstance(5));
                break;
            case Constants.BOOK://图书馆
                fragments.add(BookFragment.getInstance(0));
                fragments.add(BookFragment.getInstance(1));
                fragments.add(BookFragment.getInstance(2));
                fragments.add(BookFragment.getInstance(3));
                fragments.add(BookFragment.getInstance(4));
                break;
            case Constants.PROPERRY://物业维修
                break;

        }
        initViewpager();
        viewPager.setCurrentItem(index);
        showPoppupwindow();
    }

    private void showPoppupwindow() {

        pop = new OrderPop(ServiceOrderActivity.this);
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
                break;
            case R.id.tv_office_supplies:
                break;
            case R.id.tv_clear_vegetables:
                break;
            case R.id.tv_order_water:
                break;
            case R.id.tv_work_meal:
                break;
            case R.id.tv_health_service:
                break;
            case R.id.tv_cut_hair:
                break;
            case R.id.tv_dry_cleaner:
                break;
            case R.id.tv_experts_visit:
                break;
            case R.id.tv_book_order:
                break;
        }
    }
}
