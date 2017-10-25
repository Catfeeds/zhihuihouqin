package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface MedicalDetailModel extends MvpModel {

    Observable getData(int i1, int i2);

    Observable getDetail(int id);

    Observable submitComment(int infoid, String content);

}
