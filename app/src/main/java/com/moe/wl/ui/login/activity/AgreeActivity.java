package com.moe.wl.ui.login.activity;


import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.model.MainModel;
import com.moe.wl.ui.main.modelimpl.MainModelImpl;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.ui.main.presenter.MainPresenter;
import com.moe.wl.ui.main.view.MainView;


public class AgreeActivity extends BaseActivity<MainModel,MainView,MainPresenter> implements MainView{


    // Content View Elements

    private TitleBar mTitleBar;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_agree);
    }


    @Override
    public void initView() {
        bindViews();
    }

    // End Of Content View Elements

    private void bindViews() {
        mTitleBar = (TitleBar) findViewById(R.id.mTitleBar);
        mTitleBar.setBack(true);
        mTitleBar.setTitle("阅读协议");
    }


    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

}
