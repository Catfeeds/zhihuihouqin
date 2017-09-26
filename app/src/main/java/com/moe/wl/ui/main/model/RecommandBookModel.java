package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface RecommandBookModel extends MvpModel{

    Observable getData(String title, String author, String publisher, String remark);
}
