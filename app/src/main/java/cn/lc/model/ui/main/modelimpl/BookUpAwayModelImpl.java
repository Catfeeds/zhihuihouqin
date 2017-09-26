package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.BookUpAwayModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BookUpAwayModelImpl implements BookUpAwayModel {
    @Override
    public Observable getBookList(String s, String s1, String s2) {
        Log.e("BookUpAwayModelImpl","获取数据");
        Observable observable = RetrofitUtils.getInstance().getSearchBookList(s, s1, s2);
        return observable;
    }
}
