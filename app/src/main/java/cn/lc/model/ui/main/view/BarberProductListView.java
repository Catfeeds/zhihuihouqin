package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BarberListBean;
import cn.lc.model.ui.main.bean.BarberWorkListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberProductListView extends MvpView{

void getBarberListSucc(BarberWorkListBean listBean);

}
