package cn.lc.model.ui.main.modelimpl;

import java.util.ArrayList;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.main.model.SubmitComplainModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public class SubmitComplainModelImpl implements SubmitComplainModel {

    @Override
    public Observable submitComplain(int id, String complaintContent, String suggestContent, int anonymous, ArrayList<String> paths) {
        Observable observable = RetrofitUtils.getInstance().UpImages(id, complaintContent, suggestContent, anonymous, paths);
        return observable;
    }

    @Override
    public Observable getLabelling() {
        Observable observable = RetrofitUtils.getInstance().getLabelling();
        return observable;
    }
}
