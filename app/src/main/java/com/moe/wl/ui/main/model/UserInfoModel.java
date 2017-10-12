package com.moe.wl.ui.main.model;

import java.io.File;

import mvp.cn.rx.MvpModel;
import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public interface UserInfoModel extends MvpModel {

    Observable getUserInfo();
    Observable changeUserInfo(int sex,String nickname,String photo,int positionid,
                              String tel,String roomnum,String mobile,String buildNum);
    Observable upLoadHeader(File file);

}
