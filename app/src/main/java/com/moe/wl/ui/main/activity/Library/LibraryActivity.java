package com.moe.wl.ui.main.activity.Library;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.adapter.BookPagerAdapter;
import com.moe.wl.ui.main.fragment.LatestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.bean.LibraryPicBean;
import com.moe.wl.ui.main.fragment.HottestFragment;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observable;
import rx.Subscriber;

public class LibraryActivity extends Base2Activity {

    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    @BindView(R.id.view_down)
    View viewDown;
    @BindView(R.id.iv_big_pic)
    ImageView ivBigPic;
    @BindView(R.id.tab_book)
    TabLayout tabBook;
    @BindView(R.id.vp_book)
    ViewPager vpBook;
    @BindView(R.id.civ_recommend)
    CircleImageView civRecommend;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.activity_library)
    RelativeLayout activityLibrary;
    private List<Fragment> fragments;
    private boolean again;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_library);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        getPicData();
        again = getIntent().getBooleanExtra("again", false);
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
    }

    private void initTitle() {
        titleBar.setTitle("图书借阅");
        titleBar.setBack(true);
    }

    @OnClick({R.id.iv_more_health_consult_search,R.id.civ_recommend})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.iv_more_health_consult_search:
                Intent intent1 = new Intent(this, BookSearchActivity.class);
                startActivity(intent1);
                break;
            case R.id.civ_recommend:
                if(again){
                    Intent intent11=new Intent(this,BookConfirmOrderActivity.class);
                    startActivity(intent11);
                }else{
                    Intent intent = new Intent(this, ReaderRecommendActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    public void getPicData() {
        showProgressDialog();
        Observable homePic = RetrofitUtils.getInstance().getLibraryHomePic();
        homePic.subscribe(new Subscriber<LibraryPicBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("getPicData", e.getMessage());
            }

            @Override
            public void onNext(LibraryPicBean picBean) {
                if (picBean.getErrCode() == 0) {
                    if (picBean != null) {
                        GlideLoading.getInstance().loadImgUrlNyImgLoader(LibraryActivity.this, picBean.getPhoto(),
                                ivBigPic);
                    }
                } else {
                    Log.e("获取图片", "失败了");
                }
            }
        });
    }
}
