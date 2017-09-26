package com.moe.wl.ui.main.view;

import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.DoctorDetailBean;
import com.moe.wl.ui.main.bean.UserCommentBean;
import mvp.cn.common.MvpView;

/**
 * Created by hh on 2017/5/12.
 */

public interface DoctorDetailView extends MvpView{

void getDoctorDetailSucc(DoctorDetailBean listBean);
    void getUserCommentListSucc(UserCommentBean userCommentBean);
    void getCollectResult(CollectBean collectBean);

}
