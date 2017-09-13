package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.InformationBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/11 0011
 */

public interface InformationView extends MvpView {

    void getInformationSucc(InformationBean bean);

}
