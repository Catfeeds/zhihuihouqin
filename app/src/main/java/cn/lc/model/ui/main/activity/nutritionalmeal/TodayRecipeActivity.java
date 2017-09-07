package cn.lc.model.ui.main.activity.nutritionalmeal;

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
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.RecipeAdapter;
import cn.lc.model.ui.main.fragment.NutritionFragment;

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

        Bundle b1 = new Bundle();
        b1.putInt("Type", 1);
        b1.putInt("isMore", 1);
        NutritionFragment fragment1 = new NutritionFragment();
        fragment1.setArguments(b1);
        fragments.add(fragment1);

        Bundle b2 = new Bundle();
        b2.putInt("Type", 2);
        b2.putInt("isMore", 1);
        NutritionFragment fragment2 = new NutritionFragment();
        fragment2.setArguments(b2);
        fragments.add(fragment2);

        Bundle b3 = new Bundle();
        b3.putInt("Type", 3);
        b3.putInt("isMore", 1);
        NutritionFragment fragment3 = new NutritionFragment();
        fragment3.setArguments(b3);
        fragments.add(fragment3);
        RecipeAdapter pagerAdapter = new RecipeAdapter(getSupportFragmentManager());
        today_recipe.setAdapter(pagerAdapter);
        tabBook.setupWithViewPager(today_recipe);
        pagerAdapter.setFragments(fragments);
    }
}
