package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.DryCleanReserveInfoModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class DryCleanReserveInfoModelImpl implements DryCleanReserveInfoModel {

    @Override
    public Observable getData(String page,String limit) {
        Log.e("CollectModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().orderDryCleaner(page,limit);
        return observer ;
    }

    @Override
    public Observable CommitData(String mobile, String expectarrivaItme, String clothList) {
        Log.e("CollectModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().dryOrderCommit(mobile,expectarrivaItme,
                clothList);
        return observer ;
    }

}
