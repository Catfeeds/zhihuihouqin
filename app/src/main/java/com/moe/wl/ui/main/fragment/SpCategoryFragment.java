package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.ui.main.bean.ProductCategoryBean;
import com.moe.wl.ui.main.model.ProductCategoryModel;
import com.moe.wl.ui.main.modelimpl.ProductCategoryModelImpl;
import com.moe.wl.ui.main.presenter.ProductCategoryPresenter;
import com.moe.wl.ui.main.view.ProductCategoryView;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.ui.main.adapter.SpAdapter;

/**
 * Created by 我的电脑 on 2017/9/18 0018.
 */

public class SpCategoryFragment extends BaseFragment<ProductCategoryModel, ProductCategoryView, ProductCategoryPresenter> implements ProductCategoryView {
    @BindView(R.id.sp_grid)
    XRecyclerView spGrid;
    Unbinder unbinder;
    private int page;
    private int limit = 10;
    private SpAdapter spAdapter;
    private int id;
    private boolean isRefresh;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_sp_category);
    }

    @Override
    public void initView(View v) {
        spGrid.setLayoutManager(new GridLayoutManager(getActivity(),2));
        spAdapter = new SpAdapter(getActivity());
        spGrid.setAdapter(spAdapter);
        Bundle bundle = getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
            page = 1;
            getPresenter().getSpCategory(id + "", page+"", limit+"");
        }
        spGrid.setLoadingListener(new XRecyclerView.LoadingListener() {

            @Override
            public void onRefresh() {
                isRefresh = true;
                page=1;
                getPresenter().getSpCategory(id+"",page+"",limit+"");
                spGrid.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                isRefresh = false;
                page++;
                getPresenter().getSpCategory(id+"",page+"",limit+"");
                spGrid.loadMoreComplete();
            }
        });
    }

    public static SpCategoryFragment getInstance(int categoryId) {
        SpCategoryFragment spCategoryFragment = new SpCategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", categoryId);
        spCategoryFragment.setArguments(bundle);
        return spCategoryFragment;
    }

    @Override
    public void getSpCategory(ProductCategoryBean bean) {
        if(bean!=null){
            List<ProductCategoryBean.PageBean.ListBean> list = bean.getPage().getList();
            if(list!=null&&list.size()>0){
                spAdapter.setData(list);
            }
        }else{
            LogUtils.i("获取到的bean为空");
        }
    }

    @Override
    public ProductCategoryModel createModel() {
        return new ProductCategoryModelImpl();
    }

    @Override
    public ProductCategoryPresenter createPresenter() {
        return new ProductCategoryPresenter();
    }

}
