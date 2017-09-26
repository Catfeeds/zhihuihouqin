package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.SearchBookListBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BookUpAwayView extends MvpView{
    void getBookListSucc(SearchBookListBean booklistBean);

}
