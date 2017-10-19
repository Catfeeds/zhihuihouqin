package com.moe.wl.ui.main.view;



import com.moe.wl.ui.main.bean.DepartsListBean;
import com.moe.wl.ui.main.bean.OfficeslistBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface DepartMentView extends MvpView {

    void getDepartResult(DepartsListBean bean);

}
