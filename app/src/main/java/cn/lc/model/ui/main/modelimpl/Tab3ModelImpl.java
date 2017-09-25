package cn.lc.model.ui.main.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.Tab3Model;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class Tab3ModelImpl implements Tab3Model {
    @Override
    public Observable getData() {
        Observable observer = RetrofitUtils.getInstance().getMessage();
        return observer ;
    }

    @Override
    public Observable login(String s, String s1) {
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }
}
