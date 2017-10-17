package com.moe.wl.ui.home.activity.saving;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.home.adapter.saving.CommentAdapter;
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
 * 资讯详情页面
 */
public class InfoDetailsActivity extends BaseActivity<InformationModel, InformationView, InformationPresenter> implements InformationView, View.OnClickListener {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
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
    @BindView(R.id.lv_comment)
    NoSlidingListView lvComment;

    private CommentAdapter adapter;
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
        setContentView(R.layout.activity_saving_info_details);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        mList = new ArrayList<>();

        adapter = new CommentAdapter(this);
        adapter.setItemList(mList);
        lvComment.setAdapter(adapter);

        for (int i = 0; i < 10; i++) {
            mList.add("");
        }
        adapter.notifyDataSetChanged();

    }

    @OnClick({R.id.ll_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }


    @Override
    public void setData() {

    }

}
