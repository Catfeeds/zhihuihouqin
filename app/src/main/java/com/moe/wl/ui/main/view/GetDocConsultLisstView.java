package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.DexpertnoticeBean;
import com.moe.wl.ui.main.bean.ExpertnoticelistBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface GetDocConsultLisstView extends MvpView {

    void getListResult(ExpertnoticelistBean bean);
    void sendResult(DexpertnoticeBean bean);
}
