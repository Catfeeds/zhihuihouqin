package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.MedicalDetailBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface CollectView extends MvpView {

    void getCollectResult(CollectBean collectBean);

    void submitCommentSucc(CollectBean bean);

    void getDetailSucc(MedicalDetailBean bean);
}
