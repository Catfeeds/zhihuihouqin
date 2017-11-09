package com.moe.wl.ui.main.activity.ActivityRegistration;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.HomeNsrlv3Adapter;
import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.model.BannerModel;
import com.moe.wl.ui.main.modelimpl.BannerModelImpl;
import com.moe.wl.ui.main.presenter.BannerPresenter;
import com.moe.wl.ui.main.view.BannerView;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import rx.Observable;
import rx.Subscriber;

/**
 * 活动报名
 */
public class ActivityRegistrationActivity extends BaseActivity<BannerModel, BannerView, BannerPresenter> implements BannerView {

    @BindView(R.id.activity_title)
    TitleBar activityTitle;
    @BindView(R.id.view_title)
    View viewTitle;
    @BindView(R.id.slider_layout)
    SliderLayout sliderLayout;
    @BindView(R.id.rv_activity)
    XRecyclerView rvActivity;
    @BindView(R.id.tv_activity_posted)
    TextView tvActivityPosted;
    private CustomerDialog progressDialog;
    private HomeNsrlv3Adapter homeNsrlv3Adapter;
    private boolean isRefresh = false;
    private int page;
    List<ActivityHomeBean.ActivitylistBean> listAll = new ArrayList<>();

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_registration2);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getBanner(5);
        getData(1, 10);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        rvActivity.setLayoutManager(new LinearLayoutManager(this));
        homeNsrlv3Adapter = new HomeNsrlv3Adapter(this);
        rvActivity.setAdapter(homeNsrlv3Adapter);
        rvActivity.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvActivity.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                page = 1;
                getData(page, 10);
                rvActivity.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                page++;
                getData(page, 10);
                rvActivity.loadMoreComplete();
            }
        });
    }

    private void initTitle() {
        activityTitle.setBack(true);
        activityTitle.setTitle("活动报名");
    }

    @OnClick(R.id.tv_activity_posted)
    public void onViewClicked() {
        Intent intent = new Intent(this, ActivityPostedActivity.class);
        startActivity(intent);
    }

    public void getData(int page, int limit) {
        Observable observer = RetrofitUtils.getInstance().getActivityHome(page, limit);
        showProgressDialog();
        observer.subscribe(new Subscriber<ActivityHomeBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivityHomeBean homeBean) {
                if (homeBean.getErrCode() == 0) {
                    getDataSucc(homeBean);
                } else {
                    Log.e("ActivityHomeBean", homeBean.getMsg());
                }
            }
        });
    }

    private void getDataSucc(ActivityHomeBean homeBean) {
        if (homeBean != null) {
            if (isRefresh == true) {
                listAll.clear();
            }
            listAll.addAll(homeBean.getActivitylist());
            homeNsrlv3Adapter.setData(listAll);
        }
    }

    @Override
    public void setData(BannerResponse.ServiceInfoBean bean) {
        if (bean != null && !TextUtils.isEmpty(bean.getTopphoto())) {
            String[] strings = bean.getTopphoto().split(",");
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < strings.length; i++) {
                map.put("", strings[i]);
            }
            sliderLayout.removeAllSliders();
            for (String desc : map.keySet()) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView.description(desc).image(map.get(desc));
                sliderLayout.addSlider(textSliderView);
            }
        }

        // TODO 弹温馨出提示窗
        if (!SharedPrefHelper.getInstance().getServiceHint(Constants.TEAMACTIVE)) {
            final ShowHintDialog pop = new ShowHintDialog(this, bean.getRemind(), Constants.TEAMACTIVE);
            pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                @Override
                public void onSetting(TextView content) {
                    pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(ActivityRegistrationActivity.this, 280));
                }
            });
            pop.show();
        }
    }


    @Override
    public BannerModel createModel() {
        return new BannerModelImpl();
    }

    @Override
    public BannerPresenter createPresenter() {
        return new BannerPresenter();
    }
}
