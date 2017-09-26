package com.moe.wl.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import com.moe.wl.framework.base.BaseFragment;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.ui.main.adapter.TodayRecipeAdapter;
import com.moe.wl.ui.main.bean.NutritionBean;
import com.moe.wl.ui.main.model.RecipeModel;
import com.moe.wl.ui.main.modelimpl.RecipeModelImpl;
import com.moe.wl.ui.main.presenter.RecipePresenter;
import com.moe.wl.ui.main.view.RecipeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;

/**
 * 类描述：今日菜谱Fragment（早餐 午餐 晚餐）
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionFragment extends BaseFragment<RecipeModel, RecipeView, RecipePresenter> implements RecipeView {

    View view;
    @BindView(R.id.list_view)
    NoSlidingListView listView;

    private TodayRecipeAdapter adapter;

    @Override
    public void setContentLayout(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_nutrition);
    }

    @Override
    public void initView(View v) {
        ButterKnife.bind(this, v);
        getPresenter().getTodayRecipe(1, getArguments().getInt("Type"));
    }

    @Override
    public void getTodayRecipe(NutritionBean bean) {
        if (adapter == null) {
            if (getArguments().getInt("isMore") == 0) {
                adapter = new TodayRecipeAdapter(getActivity(), bean, 0);
            } else {
                adapter = new TodayRecipeAdapter(getActivity(), bean, 1);
            }
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public RecipeModel createModel() {
        return new RecipeModelImpl();
    }

    @Override
    public RecipePresenter createPresenter() {
        return new RecipePresenter();
    }
}
