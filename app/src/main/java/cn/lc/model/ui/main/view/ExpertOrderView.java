package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ExpertOrderBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/20 0020
 */

public interface ExpertOrderView extends MvpView {

    void submitExpertOrderSucc(ExpertOrderBean bean);

}
