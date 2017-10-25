package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.InformationClazzBean;
import com.moe.wl.ui.main.bean.OfficeCollect;
import com.moe.wl.ui.main.bean.WorklistBean;

import java.util.List;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface McNoticeView extends MvpView {

    void getCollect1(List<InformationClazzBean.NoticeTypeListEntity> list);

    void getCollect2(List<OfficeCollect.ListBean> list);

    void getCollect3(List<WorklistBean> worklist);
}
