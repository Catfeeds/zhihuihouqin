package com.moe.wl.ui.main.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.utils.ServiceIntentUtils;
import com.moe.wl.framework.widget.NoSlidingGridView;
import com.moe.wl.framework.widget.VpSwipeRefreshLayout;
import com.moe.wl.ui.main.activity.MainSearchAct;
import com.moe.wl.ui.main.activity.WebViewActivity;
import com.moe.wl.ui.main.adapter.HomeAdapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv1Adapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv2Adapter;
import com.moe.wl.ui.main.adapter.HomeNsrlv3Adapter;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.ActivitylistBean;
import com.moe.wl.ui.main.bean.HomePageBean;
import com.moe.wl.ui.main.bean.ListEntity;
import com.moe.wl.ui.main.bean.NotifyChange;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.main.model.HomePageModel;
import com.moe.wl.ui.main.modelimpl.HomePageModelImpl;
import com.moe.wl.ui.main.presenter.HomePagePresenter;
import com.moe.wl.ui.main.view.HomePageView;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;
import com.moe.wl.zxing.android.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;

import static android.app.Activity.RESULT_OK;

//import com.daimajia.slider.library.SliderLayout;

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
    /* @BindView(R.id.h_banner_viewPager)
     SimpleImageBanner sib;*/
    @BindView(R.id.gridView_catogary)
    NoSlidingGridView gridViewCatogary;
    @BindView(R.id.pullToRefreshScrollView)
    ScrollView sv;
    @BindView(R.id.id_swipe)
    VpSwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;
    @BindView(R.id.nsrlv1)
    NoSlideRecyclerView nsrlv1;
    @BindView(R.id.nsrlv2)
    NoSlideRecyclerView nsrlv2;
    @BindView(R.id.nsrlv3)
    NoSlideRecyclerView nsrlv3;
    @BindView(R.id.rl_search)
    RelativeLayout search;//关键词搜索
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.ll_gonggao)
    LinearLayout llGonggao;
    @BindView(R.id.ll_activity)
    LinearLayout llActivity;
    @BindView(R.id.ll_my_order)
    LinearLayout llMyOrder;
    Unbinder unbinder1;

    private String[] des = {"健康档案", "物业维修", "图书馆", "活动报名",
            "美容美发", "洗衣店", "办公用品", "更多"};

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
    private List<ActivitylistBean> activeData; // 活动
    private List<ListEntity> informationData; // 公告
    private List<HomePageBean.BxwxOrderList> bxData; // 报修

    private HomeNsrlv1Adapter adapter1;
    private HomeNsrlv2Adapter adapter2;
    private HomeNsrlv3Adapter adapter3;
    private HomeAdapter homeAdapter;
    private int MOREREQUEST=100;
    private SwipeRefreshLayout.OnRefreshListener listener;


    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        sysColor = R.color.white;
       setContentView(R.layout.f_tab_1);
        EventBus.getDefault().register(this);
        getUserInfo();
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white), true);
    }
    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);

        tvOne.performClick();
        serviceData = new ArrayList<>();
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
                if (!OtherUtils.isAuth()) {
                    // 没有认证
                    OtherUtils.showAuth(getActivity());
                    return;
                }
                if (ServiceIntentUtils.goService(serviceData.get(position).getId()) == null) {
                    return;
                }
                LogUtils.i("serviceData.get(position).getId()=="+serviceData.get(position).getId());
                if(serviceData.get(position).getId()==10001){
                    startActivityForResult(new Intent(getActivity(), ServiceIntentUtils.goService(serviceData.get(position).getId())),MOREREQUEST);
                }else{
                    startActivity(new Intent(getActivity(), ServiceIntentUtils.goService(serviceData.get(position).getId())));
                }
            }
        });
        sv.smoothScrollTo(0, 20);
        sv.setFocusable(true);
        getPresenter().getHomePageData();
    }

    private void getUserInfo() {
        if (SharedPrefHelper.getInstance().getAuthStatus() != 2) {
            Observable observable = RetrofitUtils.getInstance().getUserInfo();
            observable.subscribe(new Subscriber<UserInfoBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                }

                @Override
                public void onNext(UserInfoBean bean) {
                    SharedPrefHelper.getInstance().setAuthStatus(bean.getUserinfo().getAuthStatus());
                }
            });
        }
    }

    private void setRefresh() {
        // 下拉刷新
        swipeRefresh.setColorSchemeResources(new int[]{R.color.blue, R.color.red, R.color.gress_green});//设置刷新进度条颜色
        listener = new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh(){
                getPresenter().getHomePageData();
            }
        };
       /* swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 处理刷新逻辑
                getPresenter().getHomePageData();
            }
        });*/
        swipeRefresh.setOnRefreshListener(listener);
    }
    @Override
    public void getHomePageSucc(HomePageBean bean) {
        swipeRefresh.setRefreshing(false);
        if (bean.getCarouselList() != null) {
            // TODO 轮播图数据
            sliderLayout.removeAllSliders();
            for (int i = 0; i < bean.getCarouselList().size(); i++) {
                String imgs = bean.getCarouselList().get(i).getImgs();
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView.description("").image(imgs);
                sliderLayout.addSlider(textSliderView);
            }
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

    @Override
    public void onPause() {
        super.onPause();
    }

    @OnClick({R.id.iv_two_dimension_code, R.id.rl_search,R.id.tv_one, R.id.tv_two, R.id.tv_three})
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
            case R.id.rl_search://关键词搜索
                UIManager.turnToAct(getActivity(),MainSearchAct.class);
                break;
            case R.id.tv_one:
                check(R.id.tv_one);
                break;
            case R.id.tv_two:
                check(R.id.tv_two);
                break;
            case R.id.tv_three:
                check(R.id.tv_three);
                break;
        }
    }
    public void blackText(){
        tvOne.setTextColor(getResources().getColor(R.color.tv_black));
        tvTwo.setTextColor(getResources().getColor(R.color.tv_black));
        tvThree.setTextColor(getResources().getColor(R.color.tv_black));

        llActivity.setVisibility(View.GONE);
        llGonggao.setVisibility(View.GONE);
        llMyOrder.setVisibility(View.GONE);
    }
    public void check(int id){
        blackText();
        switch (id){
            case R.id.tv_one:
                tvOne.setTextColor(getResources().getColor(R.color.bt));
                llGonggao.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_two:
                tvTwo.setTextColor(getResources().getColor(R.color.bt));
                llActivity.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_three:
                tvThree.setTextColor(getResources().getColor(R.color.bt));
                llMyOrder.setVisibility(View.VISIBLE);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SCANNING_CODE) {
                LogUtils.i("扫描成功了");
                //返回扫描后的结果
                String result = data.getStringExtra("Result");
                LogUtils.i("扫描的结果:=="+result);
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("Result", result);
                startActivity(intent);
            }else if(requestCode==MOREREQUEST){
                LogUtils.i("刷新");
                swipeRefresh.post(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefresh.setRefreshing(true);
                    }
                });
                listener.onRefresh();
            }
        }
    }

    @Override
    public HomePageModel createModel() {
        return new HomePageModelImpl();
    }

    @Override
    public HomePagePresenter createPresenter() {
        return new HomePagePresenter();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NotifyChange event) {
        listener.onRefresh();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
