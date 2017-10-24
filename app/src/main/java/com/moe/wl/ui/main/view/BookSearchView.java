package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BookSearchDataBean;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/24 0024
 */

public interface BookSearchView extends MvpView {

    void getSearchDataSucc(BookSearchDataBean bean);

    void clearHistorySucc(CollectBean bean);

}
