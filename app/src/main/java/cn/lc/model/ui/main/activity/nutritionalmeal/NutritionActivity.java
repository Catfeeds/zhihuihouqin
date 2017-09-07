package cn.lc.model.ui.main.activity.nutritionalmeal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.NoSlidingListView;
import cn.lc.model.framework.widget.SimpleImageBanner;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.NutritionAdapter;
import cn.lc.model.ui.main.adapter.RecipeAdapter;
import cn.lc.model.ui.main.bean.NutritionBean;
import cn.lc.model.ui.main.fragment.NutritionFragment;
import cn.lc.model.ui.main.model.NutritionModel;
import cn.lc.model.ui.main.modelimpl.NutritionModelImpl;
import cn.lc.model.ui.main.presenter.NutritionPresenter;
import cn.lc.model.ui.main.view.NutritionView;

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
    NoSlidingListView nutritionListView;
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

        Bundle b1 = new Bundle();
        b1.putInt("Type", 1);
        b1.putInt("isMore", 0);
        NutritionFragment fragment1 = new NutritionFragment();
        fragment1.setArguments(b1);
        fragments.add(fragment1);

        Bundle b2 = new Bundle();
        b2.putInt("Type", 2);
        b2.putInt("isMore", 0);
        NutritionFragment fragment2 = new NutritionFragment();
        fragment2.setArguments(b2);
        fragments.add(fragment2);

        Bundle b3 = new Bundle();
        b3.putInt("Type", 3);
        b3.putInt("isMore", 0);
        NutritionFragment fragment3 = new NutritionFragment();
        fragment3.setArguments(b3);
        fragments.add(fragment3);
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

        nutritionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO 跳转详情页
            }
        });

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
        if (nutritionAdapter == null) {
            nutritionAdapter = new NutritionAdapter(this, bean, 0);
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
