package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.bean.RegistBean;
import com.moe.wl.ui.login.model.RegistStep2Model;
import com.moe.wl.ui.login.modelimpl.RegistStep2ModelImpl;
import com.moe.wl.ui.login.presenter.RegistStep2Presenter;
import com.moe.wl.ui.login.view.RegistStep2View;

import mvp.cn.util.CommonUtil;
import mvp.cn.util.ToastUtil;


public class RegistStepTwoActivity extends BaseActivity<RegistStep2Model, RegistStep2View, RegistStep2Presenter> implements RegistStep2View, View.OnClickListener {


    // Content View Elements

    private TitleBar mTitleBar;
    private EditText et_password;
    private EditText et_repassword;
    private TextView bt_next;
    private String mMobile;
    private String mCptcha;
    private int from;
    private String thirdType;
    private String thirdNum;
    private String pwd1;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist_2);
    }

    @Override
    public void initView() {
        bindViews();
        getPerformData();
        initLayout();
    }

    @Override
    public RegistStep2Model createModel() {
        return new RegistStep2ModelImpl();
    }

    @Override
    public RegistStep2Presenter createPresenter() {
        return new RegistStep2Presenter();
    }

    private void getPerformData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mMobile = extras.getString("mobile");
            mCptcha = extras.getString("captcha");
            from = extras.getInt("from");
            thirdType = extras.getString("thirdType");
            thirdNum = extras.getString("thirdNum");
        }
    }

    private void initLayout() {
        mTitleBar.setBack(true);
        if (from == Constants.REGIST) {
            mTitleBar.setTitle("设置密码");
            bt_next.setText("注册");
        } else if (from == Constants.FORGET) {
            mTitleBar.setTitle("设置密码");
            bt_next.setText("确认");
        }else if (from == Constants.BIND){
            mTitleBar.setTitle("设置密码");
            bt_next.setText("确认");
        }
    }

    private void bindViews() {
        mTitleBar = (TitleBar) findViewById(R.id.mTitleBar);
        et_password = (EditText) findViewById(R.id.et_password);
        et_repassword = (EditText) findViewById(R.id.et_repassword);
        bt_next = (TextView) findViewById(R.id.bt_next);
        bt_next.setOnClickListener(this);
    }

    /**
     * 返回
     */
    public void doBack() {
        finish();
    }

    /**
     * 注册
     */
    public void doRegist() {
        pwd1 = et_password.getText().toString().trim();
        String pwd2 = et_repassword.getText().toString().trim();
        if (!isOtherChecked(pwd1, pwd2)) {
            return;
        }
        CommonUtil.closeSoftKeyboard(this, et_password);

        if (from == Constants.FORGET) {
            // TODO 修改密码
            getPresenter().changePassWord(mMobile, mCptcha, pwd1);
        } else if (from == Constants.REGIST){
            // TODO 注册
            getPresenter().getData(mMobile, mCptcha, pwd1);
        }else if (from == Constants.BIND){
            getPresenter().bindPhone(thirdType, mMobile,thirdNum,"1",pwd1, mCptcha);
        }
    }

    /**
     * 其他校验
     *
     * @param pwd1
     * @param pwd2
     * @return
     */
    private boolean isOtherChecked(String pwd1, String pwd2) {

        if (TextUtils.isEmpty(pwd1) || TextUtils.isEmpty(pwd2)) {
            showToast("请输入密码");
            return false;
        } else if (pwd1.length() < 6) {
            showToast("密码长度不能小于6位");
            return false;
        } else if (pwd1.length() > 20) {
            showToast("密码长度不能大于20位");
            return false;
        }
        if (!pwd1.equals(pwd2)) {
            showToast("密码输入不一致");
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                doRegist();
                break;
        }
    }

    @Override
    public void registSuccess(RegistBean registBean) {
        int errCode = registBean.getErrCode();
        Log.e("注册", registBean.getMsg());
        /*1004 ：验证码已过期
        1005 ： 用户已注册
        1006 ： 验证码错误*/
        LogUtils.d("注册返回码：" + registBean.getErrCode() + "");
//        if (errCode == 1004) {
//            showToast("验证码已过期");
//        } else if (errCode == 1005) {
//            showToast("用户已注册");
//        } else if (errCode == 1006) {
//            showToast("验证码错误");
//        } else if (errCode == 0) {
        showToast("注册成功");
        Intent intent = new Intent(this, RegistSuccessActivity.class);
        startActivity(intent);
//        Intent intent1 = new Intent();
//        intent1.putExtra("mobile", mMobile);
//        intent1.putExtra("pwd", pwd1);
//        setResult(RESULT_OK, intent1);
        finish();
//        }
    }

    @Override
    public void changePassWord(RegistBean registBean) {
        ToastUtil.showToast(this, "修改密码成功！");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void bindSuccess(RegistBean registBean) {
        getPresenter().getData(mMobile, mCptcha, pwd1);
    }

    /**
     * 提交,设置密码,根据from来判断是忘记密码提交,还是注册提交
     *
     * @param pwd
     * @param captcha
     * @param mobile
     */
    /*private void doResistRequest(final String mobile, String captcha, String pwd) {
        String md5Pwd = null;
        try {
            md5Pwd = CrcUtil.MD5(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        showProgressDialog();
        Request request = RequestMaker.getInstance().getRegistRequest(mobile, captcha, md5Pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                showToast("注册成功");
                SoftApplication.softApplication.setUserInfo(result.data);
//                SoftApplication.softApplication.setSignId(result.token);

                if (from == Constants.REGIST) {
                    UIManager.turnToAct(RegistStep2Activity.this, CompleteInfoActivity.class);
                    finishActivityAndAboveIt(LoginActivity.class.getName());
                } else if (from == Constants.FORGET) {
                    //关闭前边的两个界面
                    finishActivityAndAboveIt(RegistStep1Activity.class.getName());
                }

            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                super.onCompleted(result, resultString);
                dismissProgressDialog();
            }
        });
    }


    *//**
     * 校验验证码,下一步
     *
     *//*
    private void doBindRequest(final String mobile, final String captcha, String pwd) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "1", pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                super.onCodeError(result);
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/


}
