package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.BarberDetailBean;
import cn.lc.model.ui.main.bean.BookDetailBean;
import cn.lc.model.ui.main.model.BookDetailModel;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface BookDetailView extends MvpView{

void getBookDetailSucc(BookDetailBean detailBean);

}
