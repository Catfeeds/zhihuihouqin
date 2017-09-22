package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.HomePageModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/21 0021
 */

public class HomePageModelImpl implements HomePageModel {
    @Override
    public Observable getHomePageData() {
        Observable observable = RetrofitUtils.homePageData();
        return observable;
    }
}
