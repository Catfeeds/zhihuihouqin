package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/22 0022
 */

public interface MoreServiceModel extends MvpModel {

    Observable getMyService();

    Observable submitMyService(int[] ids);

}
