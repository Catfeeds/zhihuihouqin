package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface SpOrderModel extends MvpModel {

    Observable getData(String addressid,String expectedTime,String remark,String productList,
                          String skuid,String count);

}
