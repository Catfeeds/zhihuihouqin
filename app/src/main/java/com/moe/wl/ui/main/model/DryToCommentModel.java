package com.moe.wl.ui.main.model;

import java.io.File;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface DryToCommentModel extends MvpModel {
    // 提交评论
    Observable dryToComment(int oid, double stars, String content, String isAnonymous, File imgFile);

}
