package com.moe.wl.ui.main.activity.complain;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.ComplainHistoryAdapter;
import com.moe.wl.ui.main.bean.ComplainHistoryBean;
import com.moe.wl.ui.main.modelimpl.ComplainHistoryModelImpl;
import com.moe.wl.ui.main.presenter.ComplainHistoryPresenter;
import com.moe.wl.ui.main.view.ComplainHistoryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

import com.moe.wl.ui.main.model.ComplainHistoryModel;

/**
 * 类描述：反馈历史页面
 * 作者：Shixhe On 2017/9/6 0006
 */
public class ComplainHistoryActivity extends BaseActivity<ComplainHistoryModel, ComplainHistoryView, ComplainHistoryPresenter> implements ComplainHistoryView {

    @BindView(R.id.recycleView)
    XRecyclerView recycleView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    private ComplainHistoryAdapter adapter;
    private List<ComplainHistoryBean.PageEntity.ListEntity> data;
    private int page = 1;
    private int pageCount = 20;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_complain_history);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        titleBar.setBack(true);
        titleBar.setTitle("反馈历史");
        getPresenter().getComplainHistoryData(page, pageCount);
//        recycleView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        recycleView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getPresenter().getComplainHistoryData(page, pageCount);
            }

            @Override
            public void onLoadMore() {
                page++;
                getPresenter().getComplainHistoryData(page, pageCount);
            }
        });
    }

    @Override
    public void getComplainHistorySucc(ComplainHistoryBean bean) {
        if (bean == null || bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
//        if (page == 1) {
//            data.clear();
//            recycleView.refreshComplete();
//        } else {
//            recycleView.loadMoreComplete();
//        }
        data.addAll(bean.getPage().getList());
        if (adapter == null) {
            adapter = new ComplainHistoryAdapter(this, data);
            //设置recycleView布局
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            //设置纵向
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycleView.setLayoutManager(linearLayoutManager);
            recycleView.setAdapter(adapter);
            recycleView.setPullRefreshEnabled(true);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new ComplainHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ComplainHistoryActivity.this, ComplainHistoryDetailActivity.class);
                intent.putExtra("ID", data.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public ComplainHistoryModel createModel() {
        return new ComplainHistoryModelImpl();
    }

    @Override
    public ComplainHistoryPresenter createPresenter() {
        return new ComplainHistoryPresenter();
    }
}
