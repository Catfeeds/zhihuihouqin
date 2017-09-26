package com.moe.wl.ui.main.activity.nutritionalmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.moe.wl.framework.widget.SimpleImageBanner;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.presenter.NutritionPresenter;
import com.moe.wl.ui.main.view.NutritionView;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.adapter.NutritionAdapter;
import com.moe.wl.ui.main.adapter.RecipeAdapter;
import com.moe.wl.ui.main.bean.NutritionBean;
import com.moe.wl.ui.main.fragment.NutritionFragment;
import com.moe.wl.ui.main.model.NutritionModel;
import com.moe.wl.ui.main.modelimpl.NutritionModelImpl;

/**
 * 类描述：营养套餐主页面
 * 作者：Shixhe On 2017/9/5 0005
 */

public class NutritionActivity extends BaseActivity<NutritionModel, NutritionView, NutritionPresenter> implements NutritionView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.today_recipe)
    ViewPager today_recipe;
    @BindView(R.id.nutrition)
    RecyclerView nutritionListView;
    @BindView(R.id.today_more)
    TextView today_more;
    @BindView(R.id.nutrition_more)
    TextView nutrition_more;
    @BindView(R.id.view_pager)
    SimpleImageBanner banner;
    @BindView(R.id.tab_book)
    TabLayout tabBook;

    // 今日食谱adapter
//    private TodayRecipeAdapter recipeAdapter;
    private NutritionAdapter nutritionAdapter;

    private List<Fragment> fragments;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_nutrition);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("营养套餐");

        fragments = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("Type", i);
            bundle.putInt("isMore", 0);
            NutritionFragment fragment = new NutritionFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        RecipeAdapter pagerAdapter = new RecipeAdapter(getSupportFragmentManager());
        today_recipe.setAdapter(pagerAdapter);
        tabBook.setupWithViewPager(today_recipe);
        pagerAdapter.setFragments(fragments);
        // 获取banner
        getPresenter().getNutritionBanner();
        // 获取早餐菜谱
//        getPresenter().getTodayRecipe(1, 1);
        // 获取营养套餐
        getPresenter().getNutritionData(2);

//        nutritionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // TODO 跳转详情页
//            }
//        });

    }

    @OnClick({R.id.today_more, R.id.nutrition_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.today_more:
                // 跳转今日更多食谱
                startActivity(new Intent(NutritionActivity.this, TodayRecipeActivity.class));
                break;

            case R.id.nutrition_more:
                // 跳转更多套餐
                startActivity(new Intent(NutritionActivity.this, NutritionMoreActivity.class));
                break;
        }
    }

    @Override
    public void getNutritionList(NutritionBean bean) {
        if (bean == null || bean.getPage() == null || bean.getPage().getList() == null) {
            return;
        }
        if (nutritionAdapter == null) {
            nutritionAdapter = new NutritionAdapter(this, bean.getPage().getList(), 0);
            nutritionListView.setLayoutManager(new NoScrollLinearLayoutManager(NutritionActivity.this));
            nutritionListView.setAdapter(nutritionAdapter);
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
