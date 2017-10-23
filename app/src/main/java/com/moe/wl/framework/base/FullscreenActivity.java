package com.moe.wl.framework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.moe.wl.R;
import com.moe.wl.framework.application.SoftApplication;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.ui.login.activity.LoginActivity;

import mvp.cn.common.MvpView;
import mvp.cn.rx.MvpModel;
import mvp.cn.rx.MvpRxActivity;
import mvp.cn.rx.MvpRxPresenter;

/**
 * 全屏显示
 */
public abstract class FullscreenActivity<M extends MvpModel, V extends MvpView, P extends MvpRxPresenter<M, V>>
        extends MvpRxActivity<M,V,P> {

    protected SoftApplication softApplication;
    private static Boolean isExit = false;
    private CustomerDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        softApplication = (SoftApplication) getApplicationContext();
        SoftApplication.unDestroyActivityList.add(this);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window=getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

    }


    /**
     * 显示正在加载的进度条
     */
    public void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(FullscreenActivity.this, R.style.dialog_style);
        progressDialog.setMessage("加载中...");
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    public void showProgressDialog(String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(FullscreenActivity.this, R.style.dialog_style);
        progressDialog.setMessage(msg);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 隐藏正在加载的进度条
     */
    public void dismissProgressDialog() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * 短时间显示Toast
     * @param info 显示的内容
     */
    public void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param info 显示的内容
     */
    public void showToastLong(String info) {
        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }

    public void reLogin(String msg) {
        if (!TextUtils.isEmpty(msg))
            showToast(msg);
        SharedPrefHelper.getInstance().setPassword("");
        SharedPrefHelper.getInstance().setToken("");
        UIManager.turnToAct(this, LoginActivity.class);
        finish();
    }

}
