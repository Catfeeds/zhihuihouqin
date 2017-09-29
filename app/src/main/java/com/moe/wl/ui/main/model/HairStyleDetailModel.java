package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface HairStyleDetailModel extends MvpModel {

    Observable getData(int workid);
    Observable collect(int type,int i);

}
