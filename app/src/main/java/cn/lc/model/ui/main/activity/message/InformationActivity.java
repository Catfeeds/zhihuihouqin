package cn.lc.model.ui.main.activity.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.adapter.InformationAdapter;
import cn.lc.model.ui.main.bean.InformationClazzBean;
import cn.lc.model.ui.main.fragment.InformationFragment;
import cn.lc.model.ui.main.model.InformationClassModel;
import cn.lc.model.ui.main.modelimpl.InformationClassModelImpl;
import cn.lc.model.ui.main.presenter.InformationClassPresenter;
import cn.lc.model.ui.main.view.InformationClassView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public class InformationActivity extends BaseActivity<InformationClassModel, InformationClassView, InformationClassPresenter> implements InformationClassView {

    private static final int REQUEST_MODULE = 10001;

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.tab_book)
    TabLayout tabBook;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.add_module)
    ImageView add_module;

//    private int id;
    private List<String> className;

    private List<Fragment> fragments;
    private InformationAdapter adapter;

    private int log = 0;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_information);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("信息公告");
        className = new ArrayList<>();
//        id = getIntent().getIntExtra("userID", 0);
        getPresenter().getInformationClass(1);

        fragments = new ArrayList<>();
    }

    @Override
    public void getInformationClassSucc(InformationClazzBean bean) {
        if (bean == null || bean.getNoticeTypeList() == null) {
            return;
        }
        if (log == 1) {
            className.clear();
            fragments.clear();
        }
        className.add("推荐");

        Bundle b = new Bundle();
        b.putBoolean("isRecommend", true);
        InformationFragment fragmentOne = new InformationFragment();
        fragmentOne.setArguments(b);
        fragments.add(fragmentOne);

        for (int i = 0; i < bean.getNoticeTypeList().size(); i++) {
            className.add(bean.getNoticeTypeList().get(i).getName());
            Bundle bundle = new Bundle();
            bundle.putInt("typeID", bean.getNoticeTypeList().get(i).getId());
            bundle.putBoolean("isRecommend", false);
            InformationFragment fragment = new InformationFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        adapter = new InformationAdapter(getSupportFragmentManager(), className);
        viewPager.setAdapter(adapter);
        tabBook.setupWithViewPager(viewPager);
        adapter.setFragments(fragments);

        add_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(InformationActivity.this, InformationModuleManagerActivity.class), REQUEST_MODULE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {

        } else {
            if (requestCode == REQUEST_MODULE) {
                log = 1;
                getPresenter().getInformationClass(1);
            }
        }
    }

    @Override
    public InformationClassModel createModel() {
        return new InformationClassModelImpl();
    }

    @Override
    public InformationClassPresenter createPresenter() {
        return new InformationClassPresenter();
    }
}
