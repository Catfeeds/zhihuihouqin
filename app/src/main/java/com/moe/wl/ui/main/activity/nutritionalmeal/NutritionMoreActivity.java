package com.moe.wl.ui.main.activity.nutritionalmeal;

import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.NutritionBean;
import com.moe.wl.ui.main.model.NutritionModel;
import com.moe.wl.ui.main.presenter.NutritionPresenter;
import com.moe.wl.ui.main.view.NutritionView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.adapter.NutritionAdapter;
import com.moe.wl.ui.main.modelimpl.NutritionModelImpl;

/**
 * 类描述：营养套餐更多页面
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionMoreActivity extends BaseActivity<NutritionModel, NutritionView, NutritionPresenter> implements NutritionView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.list_view)
    XRecyclerView recyclerView;

    // 营养套餐Adapter
    private NutritionAdapter nutritionAdapter;
    private int page = 1;
    private int pageCount = 10;
    private List<NutritionBean.PageEntity.ListEntity> data;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_nutrition_more);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        data = new ArrayList<>();
        titleBar.setBack(true);
        titleBar.setTitle("营养套餐");
        // 获取营养套餐
        getPresenter().getNutritionData(2);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO 跳转详情页
//            }
//        });

        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
            }

            @Override
            public void onLoadMore() {
                page++;
            }
        });

    }

    @Override
    public void getNutritionList(NutritionBean bean) {
        if (bean == null || bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (page >= bean.getPage().getTotalPage()) {
            recyclerView.setLoadingMoreEnabled(false);
        } else {
            recyclerView.setLoadingMoreEnabled(true);
        }
        if (page == 1) {
            recyclerView.refreshComplete();
            data.clear();
            data = bean.getPage().getList();
        } else {
            recyclerView.loadMoreComplete();
            data.addAll(bean.getPage().getList());
        }
        if (nutritionAdapter == null) {
            nutritionAdapter = new NutritionAdapter(this, data, 1);
            recyclerView.setLayoutManager(new LinearLayoutManager(NutritionMoreActivity.this));
            recyclerView.setAdapter(nutritionAdapter);
        } else {
            nutritionAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getNutritionBanner() {
    }

    @Override
    public NutritionModel createModel() {
        return new NutritionModelImpl();
    }

    @Override
    public NutritionPresenter createPresenter() {
        return new NutritionPresenter();
    }
}
