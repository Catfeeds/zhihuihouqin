package cn.lc.model.ui.main.view;

import cn.lc.model.ui.main.bean.DoctorDetailBean;
import cn.lc.model.ui.main.bean.DoctorListBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DoctorDetailView extends MvpView{

void getDoctorDetailSucc(DoctorDetailBean listBean);

}