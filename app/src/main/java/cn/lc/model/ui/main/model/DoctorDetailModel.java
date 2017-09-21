package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface DoctorDetailModel extends MvpModel {
    Observable getData(int id);

    Observable getCommentList(int id, int page, int limit);

    Observable collect(int type, int typeid);
}
