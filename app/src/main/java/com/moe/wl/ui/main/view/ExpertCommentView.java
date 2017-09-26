package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ExpertCommentBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public interface ExpertCommentView extends MvpView {

    void getExpertCommentListSucc(ExpertCommentBean bean);

}
