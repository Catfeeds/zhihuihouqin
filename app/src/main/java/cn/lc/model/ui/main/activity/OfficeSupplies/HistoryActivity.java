package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.HistoryAdapter;
import cn.lc.model.ui.main.bean.HistoryPostBean;
import cn.lc.model.ui.main.model.HistoryPostModel;
import cn.lc.model.ui.main.modelimpl.HistoryPostModelImpl;
import cn.lc.model.ui.main.presenter.HistoryPostPresenter;
import cn.lc.model.ui.main.view.HistoryPostView;

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
                rvHostory.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                isRefresh=false;
                page++;
                getPresenter().getHistoryPostInfo(page+"",limit+"");
                rvHostory.loadMoreComplete();
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
            }
            mAllList.addAll(list);
            historyAdapter.setData(mAllList);
        }
    }
}
