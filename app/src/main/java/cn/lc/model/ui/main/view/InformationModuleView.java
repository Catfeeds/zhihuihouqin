package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.InformationClazzBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public interface InformationModuleView extends MvpView {

    void getInformationClassSucc(InformationClazzBean bean);

    void getAllInformationClassSucc(InformationClazzBean bean);

    void updataInformationClassSucc(CollectBean bean);

}
