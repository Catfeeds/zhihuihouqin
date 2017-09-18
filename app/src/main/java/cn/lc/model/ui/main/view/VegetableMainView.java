package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.VegetableBean;
import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/15 0015
 */

public interface VegetableMainView extends MvpView {

    void getVegetableDataSucc(VegetableBean bean);

}
