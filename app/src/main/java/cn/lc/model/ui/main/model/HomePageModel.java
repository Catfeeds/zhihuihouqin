package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public interface HomePageModel extends MvpModel {

    Observable getHomePageData();

}
