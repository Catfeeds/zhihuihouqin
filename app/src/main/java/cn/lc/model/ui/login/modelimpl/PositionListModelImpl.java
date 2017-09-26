package cn.lc.model.ui.login.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.login.model.PositionListModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class PositionListModelImpl implements PositionListModel {


    @Override
    public Observable getPositionList() {
        Log.e("PositionListModelImpl","--->获取了数据");
        Observable observer = RetrofitUtils.getInstance().getPostionList();
        return observer ;
    }

}
