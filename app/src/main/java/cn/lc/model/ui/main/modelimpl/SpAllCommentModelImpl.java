package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.AddressModel;
import cn.lc.model.ui.main.model.SpAllCommentModel;
import cn.lc.model.ui.main.model.SpDetailModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class SpAllCommentModelImpl implements SpAllCommentModel {

    @Override
    public Observable getData(String id,String type,String page,String limit) {
        Observable observable = RetrofitUtils.getInstance().getSpAllComment(id,type,page,limit);
        return observable;
    }
}
