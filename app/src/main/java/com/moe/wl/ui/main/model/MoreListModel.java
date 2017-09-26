package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface MoreListModel extends MvpModel {
    Observable getData(int i1, int i2, String s);
}
