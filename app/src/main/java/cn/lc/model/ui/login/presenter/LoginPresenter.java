package cn.lc.model.ui.login.presenter;

import cn.lc.model.framework.spfs.SharedPrefHelper;
import cn.lc.model.framework.utils.LogUtils;
import cn.lc.model.ui.home.bean.LoginBean;
import cn.lc.model.ui.login.model.LoginModel;
import cn.lc.model.ui.login.view.LoginView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class LoginPresenter extends MvpRxPresenter<LoginModel, LoginView> {

    public void getData(String s1, String s2) {
        LogUtil.log("LoginPresenter发出请求");
        getView().showProgressDialog();
        getModel().login(s1, s2).subscribe(new Subscriber<LoginBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(LoginBean loginBean) {
                if (loginBean.getErrCode() == 0) {
                    getView().loginSuccess(loginBean);
                } else {
                    SharedPrefHelper.getInstance().setPassword("");
                    LogUtils.d("登录失败码：" + loginBean.getErrCode() + "");
                    getView().showToast(loginBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}
