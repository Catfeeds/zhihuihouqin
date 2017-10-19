package com.moe.wl.ui.home.presenter.office;

import com.moe.wl.ui.home.model.office.TestModel;
import com.moe.wl.ui.home.view.office.TestView;

import mvp.cn.rx.MvpRxPresenter;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class TestPresenter extends MvpRxPresenter<TestModel, TestView> {


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
