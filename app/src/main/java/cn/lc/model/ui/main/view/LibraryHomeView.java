package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.LibraryHomeBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface LibraryHomeView extends MvpView{

void getLibraryHomeSucc(LibraryHomeBean homeBean);

}
