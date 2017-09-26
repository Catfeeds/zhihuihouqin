package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.DoctorListBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DoctorListView extends MvpView{

void getDoctorListSucc(DoctorListBean listBean);

}
