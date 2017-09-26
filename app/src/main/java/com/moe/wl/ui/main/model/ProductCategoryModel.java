package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface ProductCategoryModel extends MvpModel {

    Observable getSpCategory(String s1, String s2, String s3);

}
