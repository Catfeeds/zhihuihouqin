package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.WuyeHomeModel;

import java.util.List;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class WuyeHomeModelImpl implements WuyeHomeModel {

    @Override
    public Observable getData(int menditem,String username,String mobile,
                              String invitetime,String serviceplace,String mendcontent,List<String> files) {
        Observable observer = RetrofitUtils.getInstance().getWuyeHomeInfo(
                menditem,username,mobile,invitetime,serviceplace,mendcontent,files);
        return observer ;
    }

    @Override
    public Observable getRepairItem() {
        Observable observer = RetrofitUtils.getInstance().getRepairItem();
        return observer ;
    }
}
