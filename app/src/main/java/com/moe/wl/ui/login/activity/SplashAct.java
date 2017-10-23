package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.moe.wl.R;
import com.moe.wl.framework.base.FullscreenActivity;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.ui.home.model.office.TestModel;
import com.moe.wl.ui.home.modelimpl.office.TestModelImpl;
import com.moe.wl.ui.home.presenter.office.TestPresenter;
import com.moe.wl.ui.home.view.office.TestView;
import com.moe.wl.ui.main.activity.MainActivity;

/**
 * 启动页
 */
public class SplashAct extends FullscreenActivity<TestModel, TestView, TestPresenter> implements TestView {

    private ImageView iv_icon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否是第一次启动，默认返回false
        if (SharedPrefHelper.getInstance().getIsFirst()) {
            //是第一次启动
            UIManager.turnToAct(SplashAct.this, GuideAct.class);
            finish();
        } else {
            setContentView(R.layout.act_splash);
            initView();
        }
    }

    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public TestModel createModel() {
        return new TestModelImpl();
    }

    private void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(SharedPrefHelper.getInstance().getUserId()) && !TextUtils.isEmpty(SharedPrefHelper.getInstance().getToken())){
                    startActivity(new Intent(SplashAct.this, MainActivity.class));
                }else{
                    startActivity(new Intent(SplashAct.this, LoginActivity.class));
                }
                //参数1，第二个actvity进入时动画，参数2是第一个activity退出是动画
                /*SplashAct.this.overridePendingTransition(R.anim.first,R.anim.second); //指定划入，划出动画；*/
                finish();
            }
        },2000);

    }

}
