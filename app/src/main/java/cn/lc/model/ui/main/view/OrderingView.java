package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.SelectTimeBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface OrderingView extends MvpView {

    void createOrderingSucc(CollectBean bean);

    void getTime(SelectTimeBean bean);

}
