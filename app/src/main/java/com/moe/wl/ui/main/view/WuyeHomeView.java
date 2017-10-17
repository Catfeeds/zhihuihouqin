package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.RepairItmeBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface WuyeHomeView extends MvpView {

    void getWuyeHomeResult(ActivityPostBean bean);

    void getRepairItemSucc(RepairItmeBean bean);

}
