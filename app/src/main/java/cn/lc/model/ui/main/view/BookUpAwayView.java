package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BooklistBean;
import cn.lc.model.ui.main.bean.SearchBookListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BookUpAwayView extends MvpView{
    void getBookListSucc(SearchBookListBean booklistBean);

}
