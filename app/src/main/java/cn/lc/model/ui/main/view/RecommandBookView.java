package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.LibraryHomeBean;
import cn.lc.model.ui.main.bean.RecommandBookBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface RecommandBookView extends MvpView{

void getRecommandResult(RecommandBookBean recommandBookBean);

}
