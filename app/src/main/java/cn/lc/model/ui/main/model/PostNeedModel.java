package cn.lc.model.ui.main.model;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface PostNeedModel extends MvpModel {

    Observable getData(String realname,String mobile,String remark,String productName,
                       String count);

}
