package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BarberListsBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberListView extends MvpView{

void getBarberListSucc(BarberListsBean listBean);

}
