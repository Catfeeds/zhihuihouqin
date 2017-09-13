package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.InformationModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationModelImpl implements InformationModel {

    @Override
    public Observable getInformationData(int typeId, int isRecommend, String keyword, int page) {
        Observable observable = RetrofitUtils.getInstance().getInformation(typeId, isRecommend, keyword, page);
        return observable;
    }
}
