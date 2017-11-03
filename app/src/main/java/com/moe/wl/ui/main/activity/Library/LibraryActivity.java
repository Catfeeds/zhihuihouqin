package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.utils.TabIndicator;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BookPagerAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.BooklistBean;
import com.moe.wl.ui.main.fragment.HottestFragment;
import com.moe.wl.ui.main.fragment.LatestFragment;
import com.moe.wl.ui.main.model.BannerModel;
import com.moe.wl.ui.main.modelimpl.BannerModelImpl;
import com.moe.wl.ui.main.presenter.BannerPresenter;
import com.moe.wl.ui.main.view.BannerView;
import com.moe.wl.ui.mywidget.ShowHintPop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 图书馆首页
 */
public class LibraryActivity extends BaseActivity<BannerModel, BannerView, BannerPresenter> implements BannerView {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    @BindView(R.id.slider_layout)
    SliderLayout sliderLayout;
    @BindView(R.id.tab_book)
    TabLayout tabBook;
    @BindView(R.id.vp_book)
    ViewPager vpBook;
    @BindView(R.id.civ_recommend)
    ImageView civRecommend;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;

    private List<Fragment> fragments;
    private boolean again;  //是否是再次借阅
    public List<BooklistBean> bookList; //之前借阅的书籍
    private BannerResponse.ServiceInfoBean bean;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getBanner(3);
        again = getIntent().getBooleanExtra("again", false);
        bookList = (List<BooklistBean>) getIntent().getSerializableExtra("list");
        if (bookList != null)
            LogUtils.d("-------bookList----" + bookList.size());
        fragments = new ArrayList<>();
        fragments.add(LatestFragment.getInstance(again));
        fragments.add(HottestFragment.getInstance(again));
        initTitle();
        initPager();
    }

    private void initPager() {
        BookPagerAdapter pagerAdapter = new BookPagerAdapter(getSupportFragmentManager());
        vpBook.setAdapter(pagerAdapter);
        tabBook.setupWithViewPager(vpBook);
        pagerAdapter.setFragments(fragments);
        tabBook.post(new Runnable() {
            @Override
            public void run() {
                TabIndicator.setIndicator(tabBook, 60, 60);
            }
        });

    }

    private void initTitle() {
        titleBar.setTitle("图书借阅");
        titleBar.setBack(true);
    }

    @OnClick({R.id.iv_more_health_consult_search, R.id.civ_recommend,R.id.iv_hint})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.iv_more_health_consult_search:
                Intent intent1 = new Intent(this, BookNewSearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.iv_hint:
                SharedPrefHelper.getInstance().setServiceHint(Constants.BOOK,false);
                showHint(bean);
                break;
            case R.id.civ_recommend:
                if (again) {
                    Intent intent11 = new Intent(this, BookConfirmOrderActivity.class);
                    startActivity(intent11);
                } else {
                    Intent intent = new Intent(this, ReaderRecommendActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void setData(BannerResponse.ServiceInfoBean bean) {
        if (bean != null && !TextUtils.isEmpty(bean.getTopphoto())) {
            this.bean=bean;
            String[] strings = bean.getTopphoto().split(",");
            sliderLayout.removeAllSliders();
            for (int i = 0; i < strings.length; i++) {
                TextSliderView textSliderView = new TextSliderView(getActivity());
                textSliderView.description("").image(strings[i]);
                sliderLayout.addSlider(textSliderView);
            }
        }
        showHint(bean);

    }

    private void showHint(BannerResponse.ServiceInfoBean bean) {
        // TODO 弹温馨出提示窗
        if (!SharedPrefHelper.getInstance().getServiceHint(Constants.BOOK)) {
            ShowHintPop pop = new ShowHintPop(this, bean.getRemind(), Constants.BOOK);
            pop.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
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
