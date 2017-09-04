package cn.lc.model.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.BarberProductAdapter;
import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BarberWorkListBean;
import cn.lc.model.ui.main.model.BarberPoductListModel;
import cn.lc.model.ui.main.modelimpl.BarberProductListModelImpl;
import cn.lc.model.ui.main.presenter.BarberProductListPresenter;
import cn.lc.model.ui.main.view.BarberProductListView;

import static android.R.attr.id;

public class BarberMoreProductActivity extends BaseActivity<BarberPoductListModel,BarberProductListView,BarberProductListPresenter> implements BarberProductListView  {

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
        getPresenter().getData(id+"",1+"",10+"");
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
        if(listBean!=null){
            List<BarberWorkListBean.WorklistBean> worklist = listBean.getWorklist();
            barberProductAdapter.setMoreData(worklist);
        }
    }
}
