package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.HistoryAdapter;
import com.moe.wl.ui.main.bean.HistoryPostBean;
import com.moe.wl.ui.main.modelimpl.HistoryPostModelImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.model.HistoryPostModel;
import com.moe.wl.ui.main.presenter.HistoryPostPresenter;
import com.moe.wl.ui.main.view.HistoryPostView;

public class HistoryActivity extends BaseActivity<HistoryPostModel,HistoryPostView,HistoryPostPresenter> implements HistoryPostView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_hostory)
    XRecyclerView rvHostory;
    private int page=1;
    private int limit=10;
    private boolean isRefresh;
    private List<HistoryPostBean.PageBean.ListBean> mAllList=new ArrayList<>();
    private HistoryAdapter historyAdapter;

    @Override
    public HistoryPostPresenter createPresenter() {
        return new HistoryPostPresenter();
    }

    @Override
    public HistoryPostModel createModel() {
        return new HistoryPostModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initRecycler();
        initData();
    }

    private void initData() {
        getPresenter().getHistoryPostInfo(page+"",limit+"");
    }

    private void initRecycler() {
        rvHostory.setLayoutManager(new LinearLayoutManager(this));
        historyAdapter = new HistoryAdapter(this);
        rvHostory.setAdapter(historyAdapter);
        rvHostory.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvHostory.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvHostory.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                isRefresh = true;
                page=1;
                getPresenter().getHistoryPostInfo(page+"",limit+"");

            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                page++;
                getPresenter().getHistoryPostInfo(page+"",limit+"");

            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("历史需求");
    }

    @Override
    public void getHistoryPostSucc(HistoryPostBean bean) {
        if(bean!=null){
            List<HistoryPostBean.PageBean.ListBean> list = bean.getPage().getList();
            if(isRefresh==true){
                mAllList.clear();
                rvHostory.refreshComplete();
            }else{
                rvHostory.loadMoreComplete();
            }
            mAllList.addAll(list);
            historyAdapter.setData(mAllList);
        }
    }
}
