package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.CollectModel;
import cn.lc.model.ui.main.model.MoreListModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class MoreListModelImpl implements MoreListModel {

    @Override
    public Observable getData(int page,int limit,String s) {
        Log.e("MoreListModelImpl","请求数据-->login");
        Observable observer = RetrofitUtils.getInstance().getMoreList(page,limit,s);
        return observer ;
    }
}
