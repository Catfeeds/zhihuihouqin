package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public interface InformationModel extends MvpModel {

    Observable getInformationData(int typeid, int isRecommend, String keyword, int page);

}
