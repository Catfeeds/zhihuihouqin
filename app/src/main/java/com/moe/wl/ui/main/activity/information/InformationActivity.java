package com.moe.wl.ui.main.activity.information;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.adapter.InformationAdapter;
import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.InformationClazzBean;
import com.moe.wl.ui.main.fragment.InformationFragment;
import com.moe.wl.ui.main.model.InformationClassModel;
import com.moe.wl.ui.main.modelimpl.InformationClassModelImpl;
import com.moe.wl.ui.main.presenter.InformationClassPresenter;
import com.moe.wl.ui.main.view.InformationClassView;
import com.moe.wl.ui.mywidget.ShowHintDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DensityUtil;
import mvp.cn.util.ToastUtil;
import rx.Observable;
import rx.Subscriber;

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
    @BindView(R.id.hint)
    ImageView hint;

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
        if (!SharedPrefHelper.getInstance().getServiceHint(Constants.INFORMATION)) {
            getHint();
        }
        fragments = new ArrayList<>();
    }

    @Override
    public void getInformationClassSucc(InformationClazzBean bean) {
        if (bean == null || bean.getNoticeTypeList() == null) {
            return;
        }
        if (log == 1) {
            //clearCacheFragments();
        }
        className.clear();
        fragments.clear();
        className.add("推荐");

        Bundle b = new Bundle();
        b.putBoolean("isRecommend", true);
        InformationFragment fragmentOne = new InformationFragment();
        fragmentOne.setArguments(b);
        fragments.add(fragmentOne);
        for (int i = 0; i < bean.getNoticeTypeList().size(); i++) {
            className.add(bean.getNoticeTypeList().get(i).getName());
            Bundle bundle = new Bundle();
            LogUtils.d("typeID : " + bean.getNoticeTypeList().get(i).getId());
            bundle.putInt("typeID", bean.getNoticeTypeList().get(i).getId());
            bundle.putBoolean("isRecommend", false);
            InformationFragment fragment = new InformationFragment();
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        adapter = new InformationAdapter(getSupportFragmentManager(), className);
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabBook.setupWithViewPager(viewPager);

        add_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(InformationActivity.this, InformationModuleManagerActivity.class), REQUEST_MODULE);
            }
        });
    }



    private void clearCacheFragments() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(fragment);
                transaction.commit();
            }
        }

    }

    @OnClick({R.id.search, R.id.hint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                startActivity(new Intent(InformationActivity.this, SearchInformationActivity.class));
                break;
            case R.id.hint:
                SharedPrefHelper.getInstance().setServiceHint(Constants.INFORMATION, false);
                getHint();
                break;
        }
    }


    private void getHint() {
        Observable observable = RetrofitUtils.getInstance().getBanner(Constants.INFORMATION);
        observable.subscribe(new Subscriber<BannerResponse>() {

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(BannerResponse mResponse) {
                if (mResponse == null)
                    return;
                if (mResponse.errCode == 0) {

                    // TODO 弹温馨出提示窗
                    final ShowHintDialog pop = new ShowHintDialog(InformationActivity.this, mResponse.getServiceInfo().getRemind(), Constants.INFORMATION);
                    pop.setOnSetIKnowState(new ShowHintDialog.OnSetIKnowState() {
                        @Override
                        public void onSetting(TextView content) {
                            pop.setButtonStateNo(content.getHeight() <= DensityUtil.dip2px(InformationActivity.this, 280));
                        }
                    });
                    pop.show();
                } else {
                    ToastUtil.showToast(InformationActivity.this, mResponse.msg);
                }
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
