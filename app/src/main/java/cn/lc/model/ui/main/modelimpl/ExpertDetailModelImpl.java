package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.ExpertDetailModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ExpertDetailModelImpl implements ExpertDetailModel {

    @Override
    public Observable getExpertDetail() {
        Observable observer = RetrofitUtils.getInstance().getExpertDetail();
        return observer ;
    }
}
