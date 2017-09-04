package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.DoctorDetailModel;
import cn.lc.model.ui.main.model.DoctorListModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class DoctorDetailModelImpl implements DoctorDetailModel {

    @Override
    public Observable getData(int id) {
        Log.e("DoctorDetailModelImpl","请求了数据");
        Observable observer = RetrofitUtils.getInstance().getDoctorDetail(id);
        return observer ;
    }
}
