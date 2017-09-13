package cn.lc.model.ui.main.fragment;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseFragment;
import cn.lc.model.framework.widget.NoSlidingListView;
import cn.lc.model.ui.main.adapter.TodayRecipeAdapter;
import cn.lc.model.ui.main.bean.NutritionBean;
import cn.lc.model.ui.main.model.RecipeModel;
import cn.lc.model.ui.main.modelimpl.RecipeModelImpl;
import cn.lc.model.ui.main.presenter.RecipePresenter;
import cn.lc.model.ui.main.view.RecipeView;

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
