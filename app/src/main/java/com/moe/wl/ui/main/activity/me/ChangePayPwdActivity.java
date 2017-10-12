package com.moe.wl.ui.main.activity.me;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.model.ChangePayPwdModel;
import com.moe.wl.ui.main.modelimpl.ChangePayPwdModelImpl;
import com.moe.wl.ui.main.presenter.ChangePayPwdPresenter;
import com.moe.wl.ui.main.view.ChangePayPwdView;
import com.moe.wl.ui.mywidget.gridpasswordview.GridPasswordView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePayPwdActivity extends BaseActivity<ChangePayPwdModel, ChangePayPwdView, ChangePayPwdPresenter> implements ChangePayPwdView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_change_des)
    TextView tvChangeDes;
    @BindView(R.id.pswView)
    GridPasswordView pswView;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.activity_change_pay_pwd)
    LinearLayout activityChangePayPwd;
    private boolean isFirst = true;
    private String firstPwd;
    private int count = 1;

    @Override
    public ChangePayPwdPresenter createPresenter() {
        return new ChangePayPwdPresenter();
    }

    @Override
    public ChangePayPwdModel createModel() {
        return new ChangePayPwdModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_change_pay_pwd);
        ButterKnife.bind(this);

    }

    @Override
    public void initView() {
        initTitle();
        final GridPasswordView passwordView = (GridPasswordView) findViewById(R.id.pswView);
        passwordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6 && count == 1) {
                    passwordView.clearPassword();
                    getPresenter().checkOldPassword(psw);

                } else if (psw.length() == 6 && count == 2) {
                    passwordView.clearPassword();
                    count = 3;
                    firstPwd = psw;
                    tvChangeDes.setText("请再次输入新的交易密码以确认");
                } else if (psw.length() == 6 && count == 3) {
                    if (psw.equals(firstPwd)) {
                        Log.e("MainActivity", "The password is: " + psw);
                        tvFinish.setVisibility(View.VISIBLE);
                    } else {
                        showToast("你输入的密码不一致");
                        passwordView.clearPassword();
                        tvChangeDes.setText("请设置支付密码,建议勿与银行卡密码相同");
                        count=2;
                    }
                }
            }

            @Override
            public void onInputFinish(String psw) {

            }
        });
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("修改交易密码");
    }

    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
        getPresenter().modifyCode(firstPwd);
    }

    @Override
    public void modifyCodeResult(ActivityPostBean bean) {
       finish();
    }
    //检测旧密码结果
    @Override
    public void checkOldPasswordResult(ActivityPostBean bean) {
        count = 2;
        tvChangeDes.setText("请设置支付密码,建议勿与银行卡密码相同");
    }
}
