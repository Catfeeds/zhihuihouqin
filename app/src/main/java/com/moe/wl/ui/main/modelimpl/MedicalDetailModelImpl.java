package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.MedicalDetailModel;

import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class MedicalDetailModelImpl implements MedicalDetailModel {

    @Override
    public Observable getData(int type, int entityid) {
        Observable observer = RetrofitUtils.getInstance().getHealthInfoColect(type, entityid);
        return observer;
    }

    @Override
    public Observable getDetail(int id) {
        Observable observer = RetrofitUtils.getInstance().getMedicalDetail(id);
        return observer;
    }

    @Override
    public Observable submitComment(int infoid, String content) {
        Observable observer = RetrofitUtils.getInstance().commentMedicalDetail(infoid, content);
        return observer;
    }
}
