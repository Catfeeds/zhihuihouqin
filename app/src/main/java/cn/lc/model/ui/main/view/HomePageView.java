package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.HomePageBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public interface HomePageView extends MvpView {

    void getHomePageSucc(HomePageBean bean);

}
