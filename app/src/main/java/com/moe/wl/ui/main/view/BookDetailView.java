package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.BookDetailBean;
import com.moe.wl.ui.main.bean.CollectBean;

import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BookDetailView extends MvpView{

void collectSucc(CollectBean detailBean);

}
