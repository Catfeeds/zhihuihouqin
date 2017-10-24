package com.moe.wl.ui.main.activity.me;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.PayDetailAdapter;
import com.moe.wl.ui.main.bean.FindWalletLogBean;
import com.moe.wl.ui.main.model.PayDetailModel;
import com.moe.wl.ui.main.modelimpl.PayDetailModelImpl;
import com.moe.wl.ui.main.presenter.PayDetailPresenter;
import com.moe.wl.ui.main.view.PayDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayDetailActivity extends BaseActivity<PayDetailModel, PayDetailView, PayDetailPresenter> implements PayDetailView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rv_pay_detail)
    XRecyclerView rvPayDetail;
    private PayDetailAdapter adapter;
    private int page = 1;
    private int limit = 10;
    private boolean getMore;
    private List<FindWalletLogBean.PageBean.ListBean> listAll;

    @Override
    public PayDetailPresenter createPresenter() {
        return new PayDetailPresenter();
    }

    @Override
    public PayDetailModel createModel() {
        return new PayDetailModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_pay_detail);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        listAll = new ArrayList();
        getPresenter().getPayDetailInfo(page + "", limit + "");//请求获得明细信息
        rvPayDetail.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PayDetailAdapter(this);
        rvPayDetail.setAdapter(adapter);
        rvPayDetail.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvPayDetail.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        rvPayDetail.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                getMore = false;
                page = 1;
                getData(page + "", limit + "");
                rvPayDetail.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                getMore = true;
                page++;
                getData(page + "", limit + "");
                rvPayDetail.loadMoreComplete();
            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("明细");
    }

    @Override
    public void getPayDetailResult(FindWalletLogBean bean) {
        if (bean != null) {
            List<FindWalletLogBean.PageBean.ListBean> list = bean.getPage().getList();
            if (list != null) {
                if (!getMore) {
                    listAll.clear();
                } else {
                    LogUtils.i("获取更多数据成功");
                }
                listAll.addAll(list);
                adapter.setData(listAll);

            }
        }
    }

    public void getData(String page, String limit) {
        getPresenter().getPayDetailInfo(page, limit);
    }
}
