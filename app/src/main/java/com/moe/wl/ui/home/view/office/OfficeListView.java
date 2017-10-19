package com.moe.wl.ui.home.view.office;

import com.moe.wl.ui.home.bean.office.OfficeListResponse;

import java.util.List;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface OfficeListView extends MvpView {

    void setData(List<OfficeListResponse.ListBean> list,String img);

}
