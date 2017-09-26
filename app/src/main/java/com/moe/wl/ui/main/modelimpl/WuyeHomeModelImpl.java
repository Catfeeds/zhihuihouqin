package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import java.util.List;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.WuyeHomeModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class WuyeHomeModelImpl implements WuyeHomeModel {

    @Override
    public Observable getData(int menditem,String username,String mobile,
                              String invitetime,String serviceplace,String mendcontent,List<String> files) {
        Log.e("MoreListModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getWuyeHomeInfo(
                menditem,username,mobile,invitetime,serviceplace,mendcontent,files);
        return observer ;
    }
}
