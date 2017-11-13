package com.moe.wl.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface DryCleanReserveInfoModel extends MvpModel {
    Observable getData(String page, String limit);

    Observable CommitData(String mobile,double sum, String expectarrivaItme,
                          String clothList);
}
