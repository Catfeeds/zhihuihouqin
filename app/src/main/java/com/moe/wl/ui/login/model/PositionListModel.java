package com.moe.wl.ui.login.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface PositionListModel extends MvpModel{
    Observable getPositionList();
}
