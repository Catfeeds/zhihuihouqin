package cn.lc.model.ui.login.view;

import cn.lc.model.ui.login.bean.CaptchaBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RegistStep1View extends MvpView{


    void success(CaptchaBean captchaBean);
}
