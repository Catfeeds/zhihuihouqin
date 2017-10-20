package com.moe.wl.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.model.CollectModel;
import com.moe.wl.ui.main.modelimpl.CollectModelImpl;
import com.moe.wl.ui.main.presenter.CollectPresenter;
import com.moe.wl.ui.main.view.CollectView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthConsultDetailActivity extends BaseActivity<CollectModel, CollectView, CollectPresenter> implements CollectView {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.scroll)
    ScrollView scroll;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_comment)
    TextView etComment;
    @BindView(R.id.collect)
    ImageView collect;
    @BindView(R.id.main)
    LinearLayout main;
    private int id;

    @Override
    public CollectPresenter createPresenter() {
        return new CollectPresenter();
    }

    @Override
    public CollectModel createModel() {
        return new CollectModelImpl();
    }

    @Override
    public void setContentLayout() {
        //setContentView(R.layout.activity_health_consult_detail);
        setContentView(R.layout.activity_information_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        id = intent.getIntExtra("id", 0);

    }

   /* private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserCommentRvAdapter rvAdapter = new UserCommentRvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
    }*/

    @OnClick({R.id.back, R.id.collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.collect:
                getPresenter().getData(8, id);
                break;
        }
    }
  /*  @OnClick({R.id.iv_health_consult_detail_back, R.id.tv_write_comment, R.id.tv_collect, R.id.tv_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_health_consult_detail_back:
                finish();
                break;
            case R.id.tv_write_comment://写评论
                View view1 = View.inflate(this, R.layout.comment_pop, null);
                break;
            case R.id.tv_collect://收藏
                getPresenter().getData(8, id);
                break;
            case R.id.tv_share://分享
                break;
        }
    }*/

    @Override
    public void getCollectResult(CollectBean collectBean) {
        if (collectBean.getStatus() == 1) {
            showToast("已收藏");
           collect .setImageResource(R.drawable.collect);
        } else {
            showToast("取消收藏");
            collect.setImageResource(R.drawable.collected);
        }
    }

}
