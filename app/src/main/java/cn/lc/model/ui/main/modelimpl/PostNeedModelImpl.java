package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.PostNeedModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class PostNeedModelImpl implements PostNeedModel {

    @Override
    public Observable getData(String realname, String mobile, String remark, String productName, String count) {
        Observable observable = RetrofitUtils.getInstance().post(realname, mobile, remark, productName, count);
        return observable;
    }
}
