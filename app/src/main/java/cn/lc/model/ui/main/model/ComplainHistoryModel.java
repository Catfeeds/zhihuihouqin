package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public interface ComplainHistoryModel extends MvpModel {

    Observable getComplainHistory(int page, int pageCount);

}
