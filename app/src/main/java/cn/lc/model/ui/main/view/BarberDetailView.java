package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BarberListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BarberDetailView extends MvpView{

void getBarberDetailSucc(BarberDetailBean detailBean);

}
