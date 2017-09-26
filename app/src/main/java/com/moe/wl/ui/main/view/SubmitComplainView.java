package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.LabellingBean;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public interface SubmitComplainView extends MvpView {

    void submitComplainSucc(CollectBean bean);

    void getLabelling(LabellingBean bean);
}
