package cn.lc.model.ui.login.view;

import cn.lc.model.ui.login.bean.CaptchaBean;
import cn.lc.model.ui.login.bean.PositionListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface PositionListView extends MvpView{
    void getListSucc(PositionListBean positionListBean);
}
