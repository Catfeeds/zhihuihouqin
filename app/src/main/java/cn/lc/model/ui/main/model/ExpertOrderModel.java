package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public interface ExpertOrderModel extends MvpModel {

    Observable submitExpertOrder(int doctorId, int timeId);

}
