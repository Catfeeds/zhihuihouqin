package com.moe.wl.ui.main.modelimpl;

import android.util.Log;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.DryCleanReserveInfoModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class DryCleanReserveInfoModelImpl implements DryCleanReserveInfoModel {

    @Override
    public Observable getData(String page,String limit) {
        Log.e("MedicalDetailModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().orderDryCleaner(page,limit);
        return observer ;
    }

    @Override
    public Observable CommitData(String mobile,double sum, String expectarrivaItme, String clothList) {
        Log.e("MedicalDetailModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().dryOrderCommit(mobile,sum,expectarrivaItme,
                clothList);
        return observer ;
    }

}
