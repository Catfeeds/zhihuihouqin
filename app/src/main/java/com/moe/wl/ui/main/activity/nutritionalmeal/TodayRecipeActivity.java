package com.moe.wl.ui.main.activity.nutritionalmeal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.moe.wl.R;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.RecipeAdapter;
import com.moe.wl.ui.main.fragment.NutritionFragment;

/**
 * 类描述：今日菜谱更多页面
 * 作者：Shixhe On 2017/9/5 0005
 */
public class TodayRecipeActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.today_recipe)
    ViewPager today_recipe;
    @BindView(R.id.tab_book)
    TabLayout tabBook;

    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_recipe_more);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("更多菜谱");

        fragments = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("Type", i + 1);
            bundle.putInt("isMore", 1);
            NutritionFragment fragment = new NutritionFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        RecipeAdapter pagerAdapter = new RecipeAdapter(getSupportFragmentManager());
        today_recipe.setAdapter(pagerAdapter);
        tabBook.setupWithViewPager(today_recipe);
        pagerAdapter.setFragments(fragments);
    }
}
