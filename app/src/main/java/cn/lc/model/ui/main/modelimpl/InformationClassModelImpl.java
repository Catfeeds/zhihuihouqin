package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.InformationClassModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class InformationClassModelImpl implements InformationClassModel {

    @Override
    public Observable getUserInformationClass(int user) {
        Observable observable = RetrofitUtils.getInstance().getInformationClass(user);
        return observable;
    }
}
