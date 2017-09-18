package cn.lc.model.ui.main.modelimpl;

import android.util.Log;

import java.io.File;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.CancelOrderingModel;
import cn.lc.model.ui.main.model.DryToCommentModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class DryToCommentModelImpl implements DryToCommentModel {

    @Override
    public Observable dryToComment(int oid, double stars, String content, String isAnonymous, File imgFile) {
        Observable observable = RetrofitUtils.getInstance().dryToComment(oid, stars, content, isAnonymous, imgFile);
        return observable;
    }
}
