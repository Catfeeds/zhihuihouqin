package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderLayout;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.home.adapter.saving.InformationAdapter;
import com.moe.wl.ui.home.model.saving.InformationModel;
import com.moe.wl.ui.home.modelimpl.saving.InformationModelImpl;
import com.moe.wl.ui.home.presenter.saving.InformationPresenter;
import com.moe.wl.ui.home.view.saving.InformationView;

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
    SliderLayout llSlider;
    @BindView(R.id.ll_statistics)
    LinearLayout llStatistics;
    @BindView(R.id.ll_ranking)
    LinearLayout llRanking;
    @BindView(R.id.ll_comparison)
    LinearLayout llComparison;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.lv_content)
    NoSlidingListView lvContent;

    private InformationAdapter adapter;
    private List<String> mList;


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
        mList=new ArrayList<>();

        adapter=new InformationAdapter(this);
        adapter.setItemList(mList);
        lvContent.setAdapter(adapter);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(SavingActivity.this,InfoDetailsActivity.class));
            }
        });

        getPresenter().information();

    }


    @OnClick({R.id.ll_back,R.id.ll_statistics,R.id.ll_ranking,R.id.ll_comparison})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.ll_more:
                startActivity(new Intent(this,InformationActivity.class));
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

    @Override
    public void setData() {
        for (int i = 0; i < 10 ; i++) {
            mList.add("");
        }
        adapter.notifyDataSetChanged();
    }
}
