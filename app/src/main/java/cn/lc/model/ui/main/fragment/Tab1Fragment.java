package cn.lc.model.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.widget.NoSlidingGridView;
import cn.lc.model.ui.main.activity.ActivityRegistration.ActivityRegistrationActivity;
import cn.lc.model.ui.main.activity.DryCleaners.DryCleanersActivity;
import cn.lc.model.ui.main.activity.HealthServerceActivity;
import cn.lc.model.ui.main.activity.Library.LibraryActivity;
import cn.lc.model.ui.main.activity.MoreActivity;
import cn.lc.model.ui.main.activity.OfficeSupplies.OfficeSuppliesActivity;
import cn.lc.model.ui.main.activity.OrderCutHearActivity;
import cn.lc.model.ui.main.activity.property_maintenance.PropertyAintenanceActivity;
import cn.lc.model.ui.main.adapter.HomeAdapter;
import cn.lc.model.ui.main.adapter.HomeNsrlv1Adapter;
import cn.lc.model.ui.main.adapter.HomeNsrlv2Adapter;
import cn.lc.model.ui.main.adapter.HomeNsrlv3Adapter;
import cn.lc.model.ui.main.model.Tab1Model;
import cn.lc.model.ui.main.modelimpl.Tab1ModelImpl;
import cn.lc.model.ui.main.presenter.Tab1Presenter;
import cn.lc.model.ui.main.view.Tab1View;
import cn.lc.model.ui.mywidget.NoSlideRecyclerView;
import cn.lc.model.zxing.android.CaptureActivity;

/**
 * Created by hh on 2016/5/18.
 */
public class Tab1Fragment extends BaseFragment<Tab1Model, Tab1View, Tab1Presenter> {
    private static final int SCANNING_CODE = 1;
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
    NoSlideRecyclerView nsrlv1;
    @BindView(R.id.nsrlv2)
    NoSlideRecyclerView nsrlv2;
    @BindView(R.id.nsrlv3)
    NoSlideRecyclerView nsrlv3;
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


    /*@BindView(R.id.iv_two_dimension_code)
    ImageView ivTwoDimensionCode;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.id_swipe)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private HomeAdapter homeAdapter;*/

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.f_tab_1);
    }


    @Override
    public Tab1Presenter createPresenter() {
        return new Tab1Presenter();
    }

    @Override
    public Tab1Model createModel() {
        return new Tab1ModelImpl();
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        nsrlv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeNsrlv1Adapter homeNsrlv1Adapter = new HomeNsrlv1Adapter();
        nsrlv1.setAdapter(homeNsrlv1Adapter);

        nsrlv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeNsrlv2Adapter homeNsr2v1Adapter = new HomeNsrlv2Adapter();
        nsrlv2.setAdapter(homeNsr2v1Adapter);

        nsrlv3.setLayoutManager(new LinearLayoutManager(getActivity()));
        HomeNsrlv3Adapter homeNsrlv3Adapter = new HomeNsrlv3Adapter(getActivity());
        nsrlv3.setAdapter(homeNsrlv3Adapter);

        HomeAdapter homeAdapter = new HomeAdapter(getActivity(),des,photos);
        gridViewCatogary.setAdapter(homeAdapter);
        //设置刷新
        setRefresh();
        //设置grid的条目点击
        setGridItemClick();
        //初始化轮播图
        initSliderLayout();
        sv.smoothScrollTo(0,20);
        sv.setFocusable(true);

    }

    private void initSliderLayout() {
        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        for (String desc : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(desc)
                    .image(url_maps.get(desc));
            sliderLayout.addSlider(textSliderView);
        }
    }

    private void setGridItemClick() {
        gridViewCatogary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://医疗服务
                        Intent intent = new Intent(getActivity(), HealthServerceActivity.class);
                        getActivity().startActivity(intent);
                        break;
                    case 1://物业维修
                        Intent intent1 = new Intent(getActivity(), PropertyAintenanceActivity.class);
                        getActivity().startActivity(intent1);
                        break;
                    case 2://图书借阅
                        Intent intent2 = new Intent(getActivity(), LibraryActivity.class);
                        getActivity().startActivity(intent2);
                        break;
                    case 3://团队活动
                        Intent intent3 = new Intent(getActivity(), ActivityRegistrationActivity.class);
                        getActivity().startActivity(intent3);
                        break;
                    case 4://预约理发
                        Intent intent4 = new Intent(getActivity(), OrderCutHearActivity.class);
                        getActivity().startActivity(intent4);
                        break;
                    case 5://干洗店
                        Intent intent5 = new Intent(getActivity(), DryCleanersActivity.class);
                        getActivity().startActivity(intent5);
                        break;
                    case 6://办公用品
                        Intent intent6 = new Intent(getActivity(), OfficeSuppliesActivity.class);
                        getActivity().startActivity(intent6);
                        break;
                    case 7://更多
                        Intent intent8 = new Intent(getActivity(), MoreActivity.class);
                        getActivity().startActivity(intent8);
                        break;
                }
            }
        });
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
                refreshlayout.finishRefresh(2000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // TODO: 2017/8/14 0014 下拉刷新
                refreshlayout.finishRefresh(2000);
            }
        });
    }


   /* public void initBanner() {
        sib
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setSource(imgList)
                .startScroll();

    }*/

    @Override
    public void onResume() {
        super.onResume();
//        if (imgList.size() != 0)
//            sib.startScroll();
//        notice.start(3000, 3000);
    }


    @Override
    public void onPause() {
        super.onPause();

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
                }else{
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
        if(requestCode == CAMERA_REQUEST_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //执行扫描
                init();
            }else{
                Toast.makeText(getActivity(), "您已拒绝了访问的权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void init() {
        Intent intent = new Intent(getActivity(),
                CaptureActivity.class);
        startActivityForResult(intent, SCANNING_CODE);

    }
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == SCANNING_CODE && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra("codedContent");
                Bitmap bitmap = data.getParcelableExtra("codedBitmap");

                tvScanningResult.setText("扫描结果： " + content);

            }
        }
    }*/
}
