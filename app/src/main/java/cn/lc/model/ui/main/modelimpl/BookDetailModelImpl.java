package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.BarberListModel;
import cn.lc.model.ui.main.model.BookDetailModel;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class BookDetailModelImpl implements BookDetailModel {

    @Override
    public Observable getData(int bookId) {
        Log.e("BookDetailModelImpl","请求数据-->bookdetail");
        Observable observer = RetrofitUtils.getInstance().getBookDetailResult(bookId);
        return observer ;
    }
}
