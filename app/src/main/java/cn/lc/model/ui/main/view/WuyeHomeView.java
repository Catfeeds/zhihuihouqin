package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.ActivityPostBean;
import cn.lc.model.ui.main.bean.CollectBean;
import cn.lc.model.ui.main.bean.WuyeHomeBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface WuyeHomeView extends MvpView{

void getWuyeHomeResult(ActivityPostBean bean);

}
