package com.moe.wl.ui.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.githang.statusbar.StatusBarCompat;
import com.moe.wl.R;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.ui.login.activity.LoginActivity;

import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/23 0023
 */

public class MyBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white), true);
    }

    public void reLogin(String msg) {
        if (!TextUtils.isEmpty(msg))
            ToastUtil.showToast(this, msg);
        SharedPrefHelper.getInstance().setPassword("");
        SharedPrefHelper.getInstance().setToken("");
        UIManager.turnToAct(this, LoginActivity.class);
        finish();
    }
}
