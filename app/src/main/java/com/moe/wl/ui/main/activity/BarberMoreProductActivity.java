package com.moe.wl.ui.main.activity;

import android.widget.GridView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.BarberProductAdapter;
import com.moe.wl.ui.main.bean.BarberDetailBean;
import com.moe.wl.ui.main.bean.BarberWorkListBean;
import com.moe.wl.ui.main.bean.WorklistBean;
import com.moe.wl.ui.main.model.BarberPoductListModel;
import com.moe.wl.ui.main.modelimpl.BarberProductListModelImpl;
import com.moe.wl.ui.main.presenter.BarberProductListPresenter;
import com.moe.wl.ui.main.view.BarberProductListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BarberMoreProductActivity extends BaseActivity<BarberPoductListModel, BarberProductListView, BarberProductListPresenter> implements BarberProductListView {

    @BindView(R.id.more_product_title)
    TitleBar titleBar;
    @BindView(R.id.more_product_grid)
    GridView moreProductGrid;
    @BindView(R.id.refresh)
    SmartRefreshLayout refreshLayout;
    private BarberProductAdapter barberProductAdapter;
    private BarberDetailBean detailBean;

    @Override
    public BarberProductListPresenter createPresenter() {
        return new BarberProductListPresenter();
    }

    @Override
    public BarberPoductListModel createModel() {
        return new BarberProductListModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_barber_more_product);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        int id = getIntent().getIntExtra("id", 0);
        getPresenter().getData(id + "", 1 + "", 10 + "");
        initTitle();
        initGrid();
    }

    private void initGrid() {
        barberProductAdapter = new BarberProductAdapter(this);
        moreProductGrid.setAdapter(barberProductAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("更多作品");
    }

    @Override
    public void getBarberListSucc(BarberWorkListBean listBean) {
        if (listBean != null) {
            List<WorklistBean> worklist = listBean.getWorklist();
            barberProductAdapter.setMoreData(worklist);
        }
    }
}
