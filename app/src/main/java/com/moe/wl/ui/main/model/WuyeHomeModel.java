package com.moe.wl.ui.main.model;

import java.util.List;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public interface WuyeHomeModel extends MvpModel {

    Observable getData(int menditem, String username, String mobile,
                       String invitetime, String serviceplace, String mendcontent, List<String> files);

    Observable getRepairItem();

}
