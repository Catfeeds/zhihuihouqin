package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface SpAllCommentModel extends MvpModel {

    Observable getData(String id, String type, String page, String limit);

}
