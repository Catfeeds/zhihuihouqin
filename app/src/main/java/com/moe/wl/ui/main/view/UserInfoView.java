package com.moe.wl.ui.main.view;

import com.moe.wl.ui.login.bean.UserInfo;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.AddressBean;
import com.moe.wl.ui.main.bean.UpLoadHeaderBean;
import com.moe.wl.ui.main.bean.UserInfoBean;

import mvp.cn.common.MvpView;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public interface UserInfoView extends MvpView {

    void getUserInfoSucc(UserInfoBean bean);
    void getChangeResult(ActivityPostBean bean);
    void upLoadHeaderResult(UpLoadHeaderBean bean);

}
