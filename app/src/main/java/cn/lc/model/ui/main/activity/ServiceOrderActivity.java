package cn.lc.model.ui.main.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.ui.main.adapter.MyPagerAdapter;
import cn.lc.model.ui.main.fragment.CancleFragment;
import cn.lc.model.ui.main.fragment.FinishFragment;
import cn.lc.model.ui.main.fragment.OverOrderFragment;
import cn.lc.model.ui.main.fragment.WaitCommentFragment;
import cn.lc.model.ui.main.fragment.WaitOrderFragment;

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
    private boolean isOpen = false;

    private List<Fragment> fragments;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new WaitOrderFragment());
        fragments.add(new OverOrderFragment());
        fragments.add(new FinishFragment());
        fragments.add(new WaitCommentFragment());
        fragments.add(new CancleFragment());
        initViewpager();
        showPoppupwindow();
    }

    private void showPoppupwindow() {
        llTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("isOpen", isOpen + "");
                ivUpOrDown.setImageResource(R.drawable.drawer_arrow_down);
                View popupView = View.inflate(ServiceOrderActivity.this, R.layout.layout_popup_window, null);
                TextView tvPublicMaintain = (TextView) popupView.findViewById(R.id.tv_office_supplies);
                TextView clearVegetables = (TextView) popupView.findViewById(R.id.tv_clear_vegetables);
                TextView orderWater = (TextView) popupView.findViewById(R.id.tv_order_water);
                TextView workmeal = (TextView) popupView.findViewById(R.id.tv_work_meal);
                TextView healthService = (TextView) popupView.findViewById(R.id.tv_health_service);
                TextView cutHair = (TextView) popupView.findViewById(R.id.tv_cut_hair);
                TextView dryCleaner = (TextView) popupView.findViewById(R.id.tv_dry_cleaner);
                TextView expertsVisit = (TextView) popupView.findViewById(R.id.tv_experts_visit);
                TextView bookOrder = (TextView) popupView.findViewById(R.id.tv_book_order);
                tvPublicMaintain.setOnClickListener(ServiceOrderActivity.this);
                clearVegetables.setOnClickListener(ServiceOrderActivity.this);
                orderWater.setOnClickListener(ServiceOrderActivity.this);
                workmeal.setOnClickListener(ServiceOrderActivity.this);
                healthService.setOnClickListener(ServiceOrderActivity.this);
                cutHair.setOnClickListener(ServiceOrderActivity.this);
                dryCleaner.setOnClickListener(ServiceOrderActivity.this);
                expertsVisit.setOnClickListener(ServiceOrderActivity.this);
                bookOrder.setOnClickListener(ServiceOrderActivity.this);
                popupWindow = new PopupWindow(popupView,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setAnimationStyle(R.style.Popupwindow_animation);
                //让冒泡窗体可以获取焦点
                popupWindow.setFocusable(true);
                //给冒泡窗体提供一个透明的背景
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

                //2.指定冒泡窗体的显示位置
                popupWindow.showAsDropDown(rlTitle, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivUpOrDown.setImageResource(R.drawable.drawer_arrow_up);
                    }
                });


            }
        });
    }

    private void initViewpager() {
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);
        //
        pagerAdapter.setFragments(fragments);
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
