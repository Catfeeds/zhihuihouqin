package cn.lc.model.ui.login.presenter;

import android.util.Log;

import cn.lc.model.ui.login.bean.SubmitAuthBean;
import cn.lc.model.ui.login.model.AuthModel;
import cn.lc.model.ui.login.view.AuthView;
import mvp.cn.rx.MvpRxPresenter;
import mvp.cn.util.LogUtil;
import rx.Subscriber;

/**
 * Created by hh on 2017/5/12.
 */

public class AuthPresenter extends MvpRxPresenter<AuthModel, AuthView> {


    public void getData(String name, String mobile, String idCard, int positionid, String roomId,
                        String officetel, String cartypeId, String precarCode, final String suffixcarCode) {
        LogUtil.log("MainPresenter发出请求");
        getView().showProgressDialog();
        // TODO: 2017/9/5 0005 需要后端修改cartypeId
        getModel().submitAuth(name, mobile, idCard, positionid, roomId, officetel, cartypeId, precarCode, suffixcarCode).subscribe(new Subscriber<SubmitAuthBean>() {
            @Override
            public void onCompleted() {
                getView().dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                getView().dismissProgressDialog();
                Log.e("错误信息", e.getMessage());
            }

            @Override
            public void onNext(SubmitAuthBean submitAuthBean) {
                if (submitAuthBean.getErrCode() == 0) {
                    getView().authSucc();
                } else if (submitAuthBean.getErrCode() == 1015) {
                    getView().showToast(submitAuthBean.getMsg());
                } else if (submitAuthBean.getErrCode() == 1016) {
                    getView().showToast(submitAuthBean.getMsg());
                } else if (submitAuthBean.getErrCode() == 1017) {
                    getView().showToast(submitAuthBean.getMsg());
                }else{
                    getView().showToast(submitAuthBean.getMsg());
                }
            }
        });
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
    }
}