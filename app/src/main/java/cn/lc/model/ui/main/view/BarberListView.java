package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BarberListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberListView extends MvpView{

void getBarberListSucc(BarberListBean listBean);

}
