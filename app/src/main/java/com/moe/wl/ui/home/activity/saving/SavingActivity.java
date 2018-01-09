package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.home.adapter.saving.InformationAdapter;
import com.moe.wl.ui.home.adapter.saving.InformationsAdapter;
import com.moe.wl.ui.home.bean.SaveHomeBanner;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;
import com.moe.wl.ui.home.model.saving.InformationModel;
import com.moe.wl.ui.home.modelimpl.saving.InformationModelImpl;
import com.moe.wl.ui.home.presenter.saving.InformationPresenter;
import com.moe.wl.ui.home.view.saving.InformationView;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 节能减排
 */
public class SavingActivity extends BaseActivity<InformationModel, InformationView, InformationPresenter> implements InformationView,View.OnClickListener{

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.ll_slider)
    SliderLayout sliderLayout;
    @BindView(R.id.ll_statistics)
    LinearLayout llStatistics;
    @BindView(R.id.ll_ranking)
    LinearLayout llRanking;
    @BindView(R.id.ll_comparison)
    LinearLayout llComparison;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.rv_content)
    RecyclerView lvContent;

    private InformationsAdapter adapter;
    private List<SaveHomeListBean.NewsBean> news;


    @Override
    public InformationPresenter createPresenter() {
        return new InformationPresenter();
    }

    @Override
    public InformationModel createModel() {
        return new InformationModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        lvContent.setLayoutManager(new LinearLayoutManager(this));
        adapter=new InformationsAdapter(this);
        lvContent.setAdapter(adapter);
       adapter.setListener(new InformationsAdapter.ClickListener() {
           @Override
           public void setOnItemClickListener(int position) {
               Intent intent = new Intent(SavingActivity.this, InfoDetailsActivity.class);
               if(news!=null&&news.size()>0){
                   SaveHomeListBean.NewsBean newsBean = news.get(position);
                   //intent.putExtra("info",intent.putExtra("info",newsBean));
                   intent.putExtra("info",newsBean.getId());
                   startActivity(intent);
               }
           }
       });
        //获得资讯列表
        getPresenter().getHomeList(1,10);
    }


    @OnClick({R.id.ll_back,R.id.ll_statistics,R.id.ll_ranking,R.id.ll_comparison})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;

            case R.id.ll_statistics:
                startActivity(new Intent(this,StatisticsActivity.class));
                break;
            case R.id.ll_ranking:
                startActivity(new Intent(this,RankingActivity.class));
                break;
            case R.id.ll_comparison:
                startActivity(new Intent(this,ComparisonActivity.class));
                break;
        }
    }
    //获得信息
    @Override
    public void setData() {


    }
    //资讯列表
    @Override
    public void getHomeList(SaveHomeListBean homeListBean) {
        //设置轮播图
        List<SaveHomeListBean.RollingPicBean> data = homeListBean.getRollingPic();
        if(data!=null&&data.size()>0){
            sliderLayout.removeAllSliders();
            for (int i = 0; i < data.size(); i++) {
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView.description("").image(data.get(i).getImgUrl());
                sliderLayout.addSlider(textSliderView);
            }
        }
        //设置列表
        news = homeListBean.getNews();
        adapter.setData(news);
    }
}
