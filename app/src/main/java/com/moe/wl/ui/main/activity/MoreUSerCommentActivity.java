package com.moe.wl.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ExpertsCommentAdapter;
import com.moe.wl.ui.main.bean.ExpertsCommentBean;
import com.moe.wl.ui.main.model.ExpertsCommentMoreModel;
import com.moe.wl.ui.main.modelimpl.ExpertsCommentMoreModelImpl;
import com.moe.wl.ui.main.presenter.ExpertsCommentMorePresenter;
import com.moe.wl.ui.main.view.ExpertsCommentMoreView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约专家更多评论
 */
public class MoreUSerCommentActivity extends BaseActivity<ExpertsCommentMoreModel, ExpertsCommentMoreView, ExpertsCommentMorePresenter>
        implements ExpertsCommentMoreView {

    @BindView(R.id.more_user_comment_title)
    TitleBar titleBar;
    @BindView(R.id.rv_more_user_comment)
    XRecyclerView recyclerView;
    private List<ExpertsCommentBean.CommentlistEntity> data;
    private int page = 1;
    private int id;
    private ExpertsCommentAdapter rvAdapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_user_comment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", -1);
        getPresenter().getDat(id, page);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new ExpertsCommentAdapter(this, data);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getDat(id, page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getDat(id, page);
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多评论");
    }

    @Override
    public void getDataSucc(ExpertsCommentBean bean) {
        if (bean.getCommentlist() == null)
            return;
        if (page == 1) {
            data.clear();
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
        List<ExpertsCommentBean.CommentlistEntity> commentlist = bean.getCommentlist();
        data.addAll(commentlist);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public ExpertsCommentMoreModel createModel() {
        return new ExpertsCommentMoreModelImpl();
    }

    @Override
    public ExpertsCommentMorePresenter createPresenter() {
        return new ExpertsCommentMorePresenter();
    }
}
