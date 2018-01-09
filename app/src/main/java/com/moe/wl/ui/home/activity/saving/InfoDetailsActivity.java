package com.moe.wl.ui.home.activity.saving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.imageload.GlideLoading;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.home.adapter.saving.CommentAdapter;
import com.moe.wl.ui.home.bean.saving.InforMationDetailBean;
import com.moe.wl.ui.home.bean.saving.SaveHomeListBean;
import com.moe.wl.ui.home.model.saving.InformationDetailModel;
import com.moe.wl.ui.home.modelimpl.saving.InformationDetailModelImpl;
import com.moe.wl.ui.home.presenter.saving.InformationDetailPresenter;
import com.moe.wl.ui.home.view.saving.InformationDetailView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资讯详情页面
 */
public class InfoDetailsActivity extends BaseActivity<InformationDetailModel, InformationDetailView, InformationDetailPresenter> implements InformationDetailView, View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_source)
    TextView tvSource;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_collect)
    ImageView llCollect;
    @BindView(R.id.ll_share)
    ImageView llShare;
   /* private CommentAdapter adapter;
    private List<String> mList;*/

    @Override
    public InformationDetailPresenter createPresenter() {
        return new InformationDetailPresenter();
    }

    @Override
    public InformationDetailModel createModel() {
        return new InformationDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_saving_info_details);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        int infoId = intent.getIntExtra("infoId", 1);
        //获得资讯详情
        getPresenter().getDetail(infoId);
    }

    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void getDetail(InforMationDetailBean bean) {
        LogUtils.i("获得详情成功了");
        if (bean != null) {
            InforMationDetailBean.DataBean newsBean = bean.getData();
            tvTitle.setText(newsBean.getTitle());
            tvTime.setText(newsBean.getCreatTime());
            tvSource.setText("来源:" + newsBean.getSourceName());
           // GlideLoading.getInstance().loadImgUrlNyImgLoader(this, newsBean.getImg(), ivIcon);
            Glide.with(this).load(newsBean.getImg()).into(ivIcon);
            tvContent.setText(newsBean.getContent());
        }
        //adapter.setItemList(mList);
        //adapter.notifyDataSetChanged();
    }

}
