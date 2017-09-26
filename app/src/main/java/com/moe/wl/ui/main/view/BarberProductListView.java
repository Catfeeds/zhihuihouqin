package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BarberWorkListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberProductListView extends MvpView{

void getBarberListSucc(BarberWorkListBean listBean);

}
