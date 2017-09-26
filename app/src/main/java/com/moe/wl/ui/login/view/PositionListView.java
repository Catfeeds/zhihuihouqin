package com.moe.wl.ui.login.view;

import com.moe.wl.ui.login.bean.PositionListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface PositionListView extends MvpView{
    void getListSucc(PositionListBean positionListBean);
}
