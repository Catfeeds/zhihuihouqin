package com.moe.wl.ui.main.modelimpl;

import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.ui.main.model.AddressModel;
import com.moe.wl.ui.main.model.UserInfoModel;

import java.io.File;

import rx.Observable;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class UserInfoModelImpl implements UserInfoModel {

    @Override
    public Observable getUserInfo() {
        Observable observable = RetrofitUtils.getInstance().getUserInfo();
        return observable;
    }

    @Override
    public Observable changeUserInfo(int sex, String nickname, String photo, int positionid, String tel, String roomnum, String mobile, String buildNum) {
        Observable observable = RetrofitUtils.getInstance().changeUserInfo(sex,nickname,photo,positionid, tel,roomnum,mobile,buildNum);
        return observable;
    }

    @Override
    public Observable upLoadHeader(File file) {
        Observable observable = RetrofitUtils.getInstance().upLoadHeader(file);
        return observable;
    }
}
