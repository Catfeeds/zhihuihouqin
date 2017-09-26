package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface ComplainDetailModel extends MvpModel {

    Observable getComplainDetail(int id);


}
