package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public interface InformationModuleModel extends MvpModel {

    Observable getInformationClass(int user);

    Observable updataInformationClass(int[] ids);

}
