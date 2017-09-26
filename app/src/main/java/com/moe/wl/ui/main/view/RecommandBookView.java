package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.RecommandBookBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RecommandBookView extends MvpView{

void getRecommandResult(RecommandBookBean recommandBookBean);

}
