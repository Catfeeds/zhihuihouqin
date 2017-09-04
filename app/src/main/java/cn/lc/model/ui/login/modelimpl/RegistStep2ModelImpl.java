package cn.lc.model.ui.login.modelimpl;

import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.ui.login.model.LoginModel;
import cn.lc.model.ui.login.model.RegistStep2Model;
import mvp.cn.util.LogUtil;
import rx.Observable;

/**
 * Created by hh on 2017/5/12.
 */

public class RegistStep2ModelImpl implements RegistStep2Model {
    @Override
    public Observable getData() {
        LogUtil.log("MainModel请求数据");
        Observable observer = RetrofitUtils.getInstance().login("", "");
        return observer ;
    }

    @Override
    public Observable regist(String s, String code, String pas) {
        LogUtil.log("RegistStep2Model请求数据-->regist");
        Observable observer = RetrofitUtils.getInstance().register(s,code,pas);
        return observer ;

    }

}
