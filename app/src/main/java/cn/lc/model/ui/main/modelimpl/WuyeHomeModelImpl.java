package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import java.io.File;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.MoreListModel;
import cn.lc.model.ui.main.model.WuyeHomeModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class WuyeHomeModelImpl implements WuyeHomeModel {

    @Override
    public Observable getData(int menditem,String username,int mobile,
                              String invitetime,String serviceplace,String mendcontent,File files) {
        Log.e("MoreListModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getWuyeHomeInfo(
                menditem,username,mobile,invitetime,serviceplace,mendcontent,files);
        return observer ;
    }
}
