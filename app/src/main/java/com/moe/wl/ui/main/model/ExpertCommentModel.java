package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public interface ExpertCommentModel extends MvpModel {

    // 获取专家评论列表
    Observable getExpertCommentList(int id, int page, int pageSize);

}
