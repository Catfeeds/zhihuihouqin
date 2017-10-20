package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BannerResponse;

import mvp.cn.common.MvpView;

/**
 * 作者 Wang
 * 日期 2017/10/20.
 * 描述 轮播图
 */

public interface BannerView extends MvpView {

    void setData(BannerResponse.ServiceInfoBean bean);

}
