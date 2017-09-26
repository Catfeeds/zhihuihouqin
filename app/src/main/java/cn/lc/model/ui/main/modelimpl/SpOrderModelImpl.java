package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.SpOrderModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpOrderModelImpl implements SpOrderModel {

    @Override
    public Observable getData(String addressid,String expectedTime,String remark,String productList,
                              String skuid,String count) {
        Observable observable = RetrofitUtils.getInstance().spOrder(addressid,expectedTime,remark,productList,skuid,count);
        return observable;
    }
}
