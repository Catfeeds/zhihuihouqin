package com.moe.wl.ui.main.activity.me;

import android.util.Log;
import android.view.View;
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

public class ForgetPayPwdActivity extends BaseActivity<ChangePayPwdModel,ChangePayPwdView,ChangePayPwdPresenter>implements ChangePayPwdView {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_change_des)
    TextView tvChangeDes;
    @BindView(R.id.pswView)
    GridPasswordView pswView;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    private boolean isFirst=true;
    private String firstPwd;

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
        tvChangeDes.setText("请输入钱包交易密码");

        final GridPasswordView passwordView = (GridPasswordView) findViewById(R.id.pswView);
        passwordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if (psw.length() == 6 && isFirst){
                    passwordView.clearPassword();
                    isFirst = false;
                    firstPwd = psw;
                    tvChangeDes.setText("请再次输入钱包交易密码");
                }else if (psw.length() == 6 && !isFirst){
                    if (psw.equals(firstPwd)){
                        Log.e("MainActivity", "The password is: " + psw);
                        tvFinish.setVisibility(View.VISIBLE);
                    }else {
                        showToast("你输入的密码不一致");
                        passwordView.clearPassword();
                        isFirst = true;
                        tvChangeDes.setText("请输入钱包交易密码");
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
        title.setTitle("忘记支付密码");
    }
    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
        getPresenter().modifyCode(firstPwd);
    }

    @Override
    public void modifyCodeResult(ActivityPostBean bean) {
        showToast("设置密码成功");
        finish();
    }

    @Override
    public void checkOldPasswordResult(ActivityPostBean bean) {
//没用
    }
}
