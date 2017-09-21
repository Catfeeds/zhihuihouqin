package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.HealthServiceRvAdapter;
import cn.lc.model.ui.main.bean.InfolistBean;
import cn.lc.model.ui.main.bean.MoreListBean;
import cn.lc.model.ui.main.model.MoreListModel;
import cn.lc.model.ui.main.modelimpl.MoreListModelImpl;
import cn.lc.model.ui.main.presenter.MoreListPresenter;
import cn.lc.model.ui.main.view.MoreListView;

/**
 * 健康咨询更多页面
 */
public class MoreHealthConsultActivity extends BaseActivity<MoreListModel, MoreListView, MoreListPresenter> implements MoreListView {

    @BindView(R.id.rv_more_health_consult)
    XRecyclerView recyclerView;
    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    private HealthServiceRvAdapter rvAdapter;
    private int currPage = 1;
    private List<InfolistBean> data = new ArrayList<>();

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_health_consult);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        initRecycler();
        getPresenter().getData(currPage, 20, "");
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("健康咨询");
    }

    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new HealthServiceRvAdapter(this);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/9/5 0005 刷新的时候存在问题
                currPage = 1;
                getPresenter().getData(currPage, 20, "");
            }

            @Override
            public void onLoadMore() {
                currPage++;
                getPresenter().getData(currPage, 20, "");
            }
        });
    }

    @OnClick(R.id.iv_more_health_consult_search)
    public void onViewClicked() {
        Intent intent = new Intent(this, HealthSearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void getMoreList(MoreListBean moreListBean) {
        if (moreListBean.getInfolist() == null)
            return;
        if (currPage == 1) {
            data.clear();
            recyclerView.refreshComplete();
        } else {
            recyclerView.loadMoreComplete();
        }
        data.addAll(moreListBean.getInfolist());
        rvAdapter.setData(data);
    }

    @Override
    public MoreListPresenter createPresenter() {
        return new MoreListPresenter();
    }

    @Override
    public MoreListModel createModel() {
        return new MoreListModelImpl();
    }
}
