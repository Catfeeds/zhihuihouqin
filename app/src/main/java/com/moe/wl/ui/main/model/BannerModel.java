package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：轮播图
 */

public interface BannerModel extends MvpModel {

    Observable getBanner(int serviceType);

}
