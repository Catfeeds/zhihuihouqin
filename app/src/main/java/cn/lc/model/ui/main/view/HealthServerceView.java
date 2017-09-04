package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.HealthServerceHomeBean;
import mvp.cn.common.MvpView;

/**
 * Created by 我的电脑 on 2017/8/28 0028.
 */

public interface HealthServerceView extends MvpView{
    void success(HealthServerceHomeBean healthServerceHomeBean);
}
