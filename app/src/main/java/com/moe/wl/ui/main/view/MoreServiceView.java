package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.ServiceMyBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/22 0022
 */

public interface MoreServiceView extends MvpView {

    void getMyServiceSucc(ServiceMyBean bean);

    void submitMyServiceSucc(CollectBean bean);

}
