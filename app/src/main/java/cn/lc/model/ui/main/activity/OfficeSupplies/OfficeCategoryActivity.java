package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.IndecatorAdapter;
import cn.lc.model.ui.main.adapter.OfficeSpAdapter;
import cn.lc.model.ui.main.adapter.SpCategoryAdapter;
import cn.lc.model.ui.main.bean.OfficeIndexBean;
import cn.lc.model.ui.main.bean.ProductCategoryBean;
import cn.lc.model.ui.main.fragment.SpCategoryFragment;
import cn.lc.model.ui.main.model.ProductCategoryModel;
import cn.lc.model.ui.main.presenter.ProductCategoryPresenter;
import cn.lc.model.ui.main.view.ProductCategoryView;

public class OfficeCategoryActivity extends AppCompatActivity{

    @BindView(R.id.sp_category_title)
    TitleBar titleBar;
    @BindView(R.id.tab_category)
    TabLayout tabCategory;
    @BindView(R.id.vp_page)
    ViewPager vpPage;
    @BindView(R.id.activity_office_category)
    LinearLayout activityOfficeCategory;
    private int position;
    private IndecatorAdapter indecatorAdapter;
    private List<Fragment> fragments;
    private SpCategoryAdapter categoryAdapter;
    private List<OfficeIndexBean.CategoryListBean> list;
    private ArrayList<String> categorys;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_category);
        ButterKnife.bind(this);
        initView();
    }
    public void initView() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        initTitle();
        initPager();
        vpPage.setCurrentItem(position);
        fragments = new ArrayList<>();
        String json = intent.getStringExtra("json");
        Gson gson = new Gson();
        list = gson.fromJson
                (json, new TypeToken<List<OfficeIndexBean.CategoryListBean>>() {
        }.getType());
        LogUtils.i("list ==="+list);
        categorys = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            OfficeIndexBean.CategoryListBean categoryListBean = list.get(i);
            if(categoryListBean!=null){
                String cname = categoryListBean.getCname();
                int id = categoryListBean.getId();
                LogUtils.i(cname+"=="+id);
                categorys.add(cname);
                fragments.add(SpCategoryFragment.getInstance(id));
            }
        }
        categoryAdapter.setData(fragments,categorys);

    }

    private void initPager() {
        categoryAdapter = new SpCategoryAdapter(getSupportFragmentManager());
        vpPage.setAdapter(categoryAdapter);
        tabCategory.setupWithViewPager(vpPage);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("商品分类");
    }
}
