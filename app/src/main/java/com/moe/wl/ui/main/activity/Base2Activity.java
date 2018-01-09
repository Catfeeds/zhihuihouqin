package com.moe.wl.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.ui.login.activity.LoginActivity;

/**
 * Created by 我的电脑 on 2017/9/7 0007.
 */

public abstract class Base2Activity extends AppCompatActivity {
    protected CustomerDialog progressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
        initView();
    }

    protected abstract void initLayout();

    protected abstract void initView();
    public void showProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        progressDialog.setMessage("加载中...");
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }
    public void showProgressDialog(String str) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        progressDialog.setMessage(str);
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
    public void reLogin(String msg) {
        if (!TextUtils.isEmpty(msg))
            showToast(msg);
        SharedPrefHelper.getInstance().setPassword("");
        SharedPrefHelper.getInstance().setToken("");
        UIManager.turnToAct(this, LoginActivity.class);
        finish();
    }
    public void showToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }
}
