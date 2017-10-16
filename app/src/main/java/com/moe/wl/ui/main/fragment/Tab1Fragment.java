package com.moe.wl.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.ui.main.adapter.HomeNsrlv1Adapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv2Adapter;
import com.moe.wl.ui.main.presenter.HomePagePresenter;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.utils.ServiceIntentUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.ui.main.adapter.HomeAdapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv3Adapter;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.HomePageBean;
import com.moe.wl.ui.main.bean.InformationBean;
import com.moe.wl.ui.main.model.HomePageModel;
import com.moe.wl.ui.main.modelimpl.HomePageModelImpl;
import com.moe.wl.ui.main.view.HomePageView;
import com.moe.wl.zxing.android.CaptureActivity;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab1Fragment extends BaseFragment<HomePageModel, HomePageView, HomePagePresenter> implements HomePageView {
    private static final int SCANNING_CODE = 1001;
    private static final int CAMERA_REQUEST_CODE = 10;
    @BindView(R.id.iv_two_dimension_code)
    ImageView ivTwoDimensionCode;
    /*@BindView(R.id.iv_search)
    ImageView ivSearch;*/
    /*@BindView(R.id.h_banner_viewPager)
    SimpleImageBanner hBannerViewPager;*/
    @BindView(R.id.slider_layout)
    SliderLayout sliderLayout;

    @BindView(R.id.gridView_catogary)
    NoSlidingGridView gridViewCatogary;
    @BindView(R.id.pullToRefreshScrollView)
    ScrollView sv;
    @BindView(R.id.id_swipe)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.nsrlv1)
    RecyclerView nsrlv1;
    @BindView(R.id.nsrlv2)
    RecyclerView nsrlv2;
    @BindView(R.id.nsrlv3)
    RecyclerView nsrlv3;
    @BindView(R.id.rl_search)
    RelativeLayout search;//关键词搜索

    private String[] des = {"医疗服务", "物业维修", "图书馆", "活动报名",
            "预约理发", "干洗店", "办公用品", "更多"};

    private int[] photos = {R.drawable.health_service,
            R.drawable.property_maintenance,
            R.drawable.library,
            R.drawable.enrollment,
            R.drawable.reserva_haircut,
            R.drawable.dry_cleaners,
            R.drawable.office_supplies,
            R.drawable.more};

    private List<HomePageBean.ServiceListEntity> serviceData; // 服务
//    private List<HomePageBean.CarouselListEntity> roastingData; // 轮播图
    private List<ActivityHomeBean.ActivitylistBean> activeData; // 活动
    private List<InformationBean.PageEntity.ListEntity> informationData; // 公告
    private List<HomePageBean.BxwxOrderList> bxData; // 报修

    private HomeNsrlv1Adapter adapter1;
    private HomeNsrlv2Adapter adapter2;
    private HomeNsrlv3Adapter adapter3;
    private HomeAdapter homeAdapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab_1);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

        serviceData = new ArrayList<>();
//        roastingData = new ArrayList<>();
        activeData = new ArrayList<>();
        informationData = new ArrayList<>();
        bxData = new ArrayList<>();

        // 公告
        adapter1 = new HomeNsrlv1Adapter(getActivity(), informationData);
        nsrlv1.setLayoutManager(new NoScrollLinearLayoutManager(getActivity()));
        nsrlv1.setAdapter(adapter1);

        // 报修
        adapter2 = new HomeNsrlv2Adapter(getActivity(), bxData);
        nsrlv2.setLayoutManager(new NoScrollLinearLayoutManager(getActivity()));
        nsrlv2.setAdapter(adapter2);

        // 活动
        adapter3 = new HomeNsrlv3Adapter(getActivity(), activeData);
        nsrlv3.setLayoutManager(new NoScrollLinearLayoutManager(getActivity()));
        nsrlv3.setAdapter(adapter3);

        // 服务
        homeAdapter = new HomeAdapter(getActivity(), serviceData);
        gridViewCatogary.setAdapter(homeAdapter);

        //设置刷新
        setRefresh();

        //设置grid的条目点击
        gridViewCatogary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ServiceIntentUtils.goService(serviceData.get(position).getId())==null){
                    return;
                }
                startActivity(new Intent(getActivity(), ServiceIntentUtils.goService(serviceData.get(position).getId())));
            }
        });
        sv.smoothScrollTo(0, 20);
        sv.setFocusable(true);
        getPresenter().getHomePageData();
    }

    // 轮播图数据
    private void initSliderLayout(HashMap<String, String> map) {
        sliderLayout.removeAllSliders();
        for (String desc : map.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(desc)
                    .image(map.get(desc));
            sliderLayout.addSlider(textSliderView);
        }
    }

    private void setRefresh() {
        //设置 Header 为 Material风格
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //设置 Footer 为 球脉冲Scale
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Translate));
        refreshLayout.setOnRefreshListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                // TODO: 2017/8/14 0014 加载更多
                getPresenter().getHomePageData();
                refreshlayout.finishRefresh(2000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // TODO: 2017/8/14 0014 下拉刷新
                getPresenter().getHomePageData();
                refreshlayout.finishRefresh(2000);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void getHomePageSucc(HomePageBean bean) {
        if (bean.getCarouselList() != null) {
            // TODO 轮播图数据
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < bean.getCarouselList().size(); i++) {
                map.put("", bean.getCarouselList().get(i).getImgs());
            }
            initSliderLayout(map);
        }
        if (bean.getServiceList() != null) {
            // TODO 填充服务数据
            serviceData.clear();
            serviceData.addAll(bean.getServiceList());
            HomePageBean.ServiceListEntity entity = new HomePageBean.ServiceListEntity();
            entity.setId(10001);
            serviceData.add(entity);
            homeAdapter.notifyDataSetChanged();
        }
        if (bean.getNoticeList() != null) {
            // TODO 公告
            informationData.clear();
            informationData.addAll(bean.getNoticeList());
            adapter1.notifyDataSetChanged();
        }
        if (bean.getBxwxOrderList() != null) {
            bxData.clear();
            bxData.addAll(bean.getBxwxOrderList());
            adapter2.notifyDataSetChanged();
        }
        if (bean.getActivityList() != null) {
            activeData.clear();
            activeData.addAll(bean.getActivityList());
            adapter3.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.iv_two_dimension_code, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_two_dimension_code://二维码扫描
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请开启摄像机权限
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                            CAMERA_REQUEST_CODE);//自定义的code
                } else {
                    init();
                }
                break;
            case R.id.iv_search://关键词搜索
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //执行扫描
                init();
            } else {
                Toast.makeText(getActivity(), "您已拒绝了访问的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 跳转二维码扫描
    private void init() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, SCANNING_CODE);
    }

    @Override
    public HomePageModel createModel() {
        return new HomePageModelImpl();
    }

    @Override
    public HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }
}