package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ComplainHistoryBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/6 0006
 */

public interface ComplainHistoryView extends MvpView {

    void getComplainHistorySucc(ComplainHistoryBean bean);

}
