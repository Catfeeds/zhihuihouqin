package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.QueryWaterListModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class QueryWaterListModelImpl implements QueryWaterListModel {


    @Override
    public Observable queryWaterList(int id, int page, int limit) {
        Observable observable = RetrofitUtils.getInstance().queryWaterList(id,page,limit);
        return observable;
    }
}
