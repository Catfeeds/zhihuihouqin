package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.CollectBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface CollectView extends MvpView{

void getCollectResult(CollectBean collectBean);

}
