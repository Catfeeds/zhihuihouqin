package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BarberListBean;
import cn.lc.model.ui.main.bean.PreOrderBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface PreOrderBarberView extends MvpView{

void getBarberInfo(PreOrderBean preOrderBean);

}
