package cn.lc.model.ui.login.view;

import cn.lc.model.ui.login.bean.RegistBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep2View extends MvpView{

   // void showToast();
    void registSuccess(RegistBean registBean);
}
