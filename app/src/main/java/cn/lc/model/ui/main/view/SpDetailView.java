package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.AddressBean;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.ShopCarInfoBean;
import cn.lc.model.ui.main.bean.SpDetailBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface SpDetailView extends MvpView {

    void getSpDetailSucc(SpDetailBean bean);
    void getCollectResult(CollectBean bean);
    void getShopCarInfo(ShopCarInfoBean bean);

}
