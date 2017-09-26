package com.moe.wl.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.ui.main.model.ExpertCommentModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.bean.CommentlistBean;
import com.moe.wl.ui.main.bean.ExpertCommentBean;
import com.moe.wl.ui.main.modelimpl.ExpertCommentModelImpl;
import com.moe.wl.ui.main.presenter.ExpertCommentPresenter;
import com.moe.wl.ui.main.view.ExpertCommentView;

/**
 * 预约专家更多评论
 */
public class MoreUSerCommentActivity extends BaseActivity<ExpertCommentModel, ExpertCommentView, ExpertCommentPresenter> implements ExpertCommentView {

    @BindView(R.id.more_user_comment_title)
    TitleBar titleBar;
    @BindView(R.id.rv_more_user_comment)
    XRecyclerView recyclerView;

    private DoctorDetailrvAdapter rvAdapter;
    private List<CommentlistBean> data;
    private int page = 1;
    private int id;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_user_comment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", -1);
        getPresenter().getExpertComment(id, page, 20);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        data = new ArrayList<>();
        rvAdapter = new DoctorDetailrvAdapter(this, data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getExpertComment(id, page, 20);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getExpertComment(id, page, 20);
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多评论");
    }

    @Override
    public void getExpertCommentListSucc(ExpertCommentBean bean) {
        if (bean.getCommentlist() == null)
            return;
        if (page == 1) {
            data.clear();
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
        data.addAll(bean.getCommentlist());
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public ExpertCommentModel createModel() {
        return new ExpertCommentModelImpl();
    }

    @Override
    public ExpertCommentPresenter createPresenter() {
        return new ExpertCommentPresenter();
    }
}
