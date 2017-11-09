package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.ActivityHomeBean;
import com.moe.wl.ui.main.bean.BarberProductCollect;
import com.moe.wl.ui.main.bean.BookCollect;
import com.moe.wl.ui.main.bean.InfolistBean;
import com.moe.wl.ui.main.bean.InforMationCollect;
import com.moe.wl.ui.main.bean.OfficeCollect;

import java.util.List;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface McNoticeView extends MvpView {

    void getCollect1(List<InforMationCollect.ListBean> list);

    void getCollect2(List<OfficeCollect.ListBean> list);

    void getCollect3(List<BarberProductCollect.ListBean> worklist);

    void getCollect4(List<BookCollect.ListBean> booklist);

    void getCollect5(List<InfolistBean> infolist);

    void getCollect6(List<ActivityHomeBean.ActivitylistBean> activitylist);

    void getCollect7(List<BarberProductCollect.ListBean> barberlist);
}
