package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ExpertDetailBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/19 0019
 */

public interface ExpertDetailView extends MvpView {

    void getExpertDetailSucc(ExpertDetailBean bean);

}
