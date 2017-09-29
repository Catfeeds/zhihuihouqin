package com.moe.wl.ui.main.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberMoreCommentrvAdapter;
import com.moe.wl.ui.main.adapter.DoctorDetailrvAdapter;
import com.moe.wl.ui.main.bean.BarberMoreCommentBean;
import com.moe.wl.ui.main.model.BarberMoreCommentModel;
import com.moe.wl.ui.main.modelimpl.BarberMoreCommentModelImpl;
import com.moe.wl.ui.main.presenter.BarberMoreCommentPresenter;
import com.moe.wl.ui.main.view.BarberMoreCommentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约专家更多评论
 */
public class MoreUSerCommentActivity extends BaseActivity<BarberMoreCommentModel, BarberMoreCommentView, BarberMoreCommentPresenter>
        implements BarberMoreCommentView {

    @BindView(R.id.more_user_comment_title)
    TitleBar titleBar;
    @BindView(R.id.rv_more_user_comment)
    XRecyclerView recyclerView;
    private List<BarberMoreCommentBean.CommentlistBean> data;
    private int page = 1;
    private int id;
    private BarberMoreCommentrvAdapter rvAdapter;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_user_comment);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", -1);
        getPresenter().getDat(id, page, 20);
        initTitle();
        initRecycler();
    }

    private void initRecycler() {
        data = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new BarberMoreCommentrvAdapter(this, data);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getDat(id, page, 20);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getDat(id, page, 20);
            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多评论");
    }

    @Override
    public void getDataSucc(BarberMoreCommentBean bean) {
        if (bean.getCommentlist() == null)
            return;
        if (page == 1) {
            data.clear();
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
        List<BarberMoreCommentBean.CommentlistBean> commentlist = bean.getCommentlist();
        data.addAll(commentlist);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public BarberMoreCommentModel createModel() {
        return new BarberMoreCommentModelImpl();
    }

    @Override
    public BarberMoreCommentPresenter createPresenter() {
        return new BarberMoreCommentPresenter();
    }
}
