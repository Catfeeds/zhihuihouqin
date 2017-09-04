package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.MainModel;
import cn.lc.model.ui.main.model.ShopModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class ShopModelImpl implements ShopModel {

    @Override
    public Observable getData() {
        Log.e("ShopModel请求数据-->","---");
        Observable observer = RetrofitUtils.getInstance().getShopInfo();
        return observer;
    }
}
