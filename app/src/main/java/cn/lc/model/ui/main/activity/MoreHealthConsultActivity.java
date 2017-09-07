package cn.lc.model.ui.main.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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

public class MoreHealthConsultActivity extends BaseActivity<MoreListModel, MoreListView, MoreListPresenter> implements MoreListView {

    @BindView(R.id.rv_more_health_consult)
    XRecyclerView recyclerView;
    @BindView(R.id.more_health_consult_title)
    TitleBar titleBar;
    @BindView(R.id.iv_more_health_consult_search)
    ImageView ivMoreHealthConsultSearch;
    private HealthServiceRvAdapter rvAdapter;
    private int currPage;
    private List<InfolistBean> datas = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    public MoreListPresenter createPresenter() {
        return new MoreListPresenter();
    }

    @Override
    public MoreListModel createModel() {
        return new MoreListModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_more_health_consult);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        getPresenter().getData(1, 10, "");
        initTitle();
        initRecycler();
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
        ;
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // TODO: 2017/9/5 0005 刷新的时候存在问题
                currPage = 1;
                datas.clear();
                getPresenter().getData(currPage, 10, "");
                recyclerView.refreshComplete();
                //refresh data here
            }

            @Override
            public void onLoadMore() {
                currPage++;
                getPresenter().getData(currPage, 10, "");
                recyclerView.loadMoreComplete();
                //加载更多
            }
        });
        //getPresenter().HotAct(currPage+"","10","");
    }

    @OnClick(R.id.iv_more_health_consult_search)
    public void onViewClicked() {
        Intent intent = new Intent(this, HealthSearchActivity.class);
    }

    @Override
    public void getMoreList(MoreListBean moreListBean) {
        Log.e("moreListBean===", moreListBean + "");
        if (moreListBean != null) {
            datas.addAll(moreListBean.getInfolist());
            rvAdapter.setData(datas);
        }
    }
}
