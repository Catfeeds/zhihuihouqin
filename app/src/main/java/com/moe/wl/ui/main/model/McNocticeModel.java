package com.moe.wl.ui.main.model;

import java.util.List;
import java.util.Map;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface McNocticeModel extends MvpModel {

    Observable findUserFavorList(String type);
    Observable deleteFavorList(List<Map<String,Integer>> type);

}
