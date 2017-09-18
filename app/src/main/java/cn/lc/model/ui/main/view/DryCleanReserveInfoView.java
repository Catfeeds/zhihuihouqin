package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.OrderDryCleanBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DryCleanReserveInfoView extends MvpView{

void OrderDryCleaner(OrderDryCleanBean cleanBean,boolean getMore);
    void commitSucc();

}
