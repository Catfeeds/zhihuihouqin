package com.moe.wl.ui.main.model;



import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public interface HealthServerceModel extends MvpModel {
    Observable getData();
}
