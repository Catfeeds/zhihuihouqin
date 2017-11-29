package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BannerResponse;
import com.moe.wl.ui.main.bean.OfficeIndexBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface OfficeIndexView extends MvpView {

    void getIndexInfo(OfficeIndexBean bean);

    void setData(BannerResponse.ServiceInfoBean serviceInfo);
}
