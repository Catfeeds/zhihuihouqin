package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */

public interface VegetableMainModel extends MvpModel {

    /**
     * 获取蔬菜列表
     * @param page 当前页
     * @param keyword 搜索关键词
     * @return
     */
    Observable getVegetableData(int page, String keyword);

}
