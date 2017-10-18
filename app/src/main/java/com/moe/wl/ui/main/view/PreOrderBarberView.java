package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.CreateorderBean;
import com.moe.wl.ui.main.bean.PreOrderBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface PreOrderBarberView extends MvpView{

void getBarberInfo(PreOrderBean preOrderBean);
void createOrederResult(CreateorderBean bean);

}
