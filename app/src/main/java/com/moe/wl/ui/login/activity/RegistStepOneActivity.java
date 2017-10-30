package com.moe.wl.ui.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.framework.widget.bean.BindPhoneBean;
import com.moe.wl.ui.login.bean.CaptchaBean;
import com.moe.wl.ui.login.model.RegistStep1Model;
import com.moe.wl.ui.login.modelimpl.RegistStep1ModelImpl;
import com.moe.wl.ui.login.presenter.RegistStep1Presenter;
import com.moe.wl.ui.login.view.RegistStep1View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;


public class RegistStepOneActivity extends BaseActivity<RegistStep1Model, RegistStep1View, RegistStep1Presenter> implements RegistStep1View {

    @BindView(R.id.regist_title)
    TitleBar registTitle;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_uname)
    ImageView ivUname;
    @BindView(R.id.rl_uname)
    LinearLayout rlUname;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.btn_getcode)
    TextView btnGetcode;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_agreen)
    TextView tvAgreen;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;
    @BindView(R.id.complaint)
    TextView complaint;
    @BindView(R.id.ll_complain)
    LinearLayout ll_complain;
    @BindView(R.id.ll_pact)
    LinearLayout ll_pact;

    private Handler handler = new Handler();
    public static final int MAX_TIME = 60;// 按钮 60秒内不能点击
    private int totalSecond = MAX_TIME;// 按钮 60秒内不能点击
    private int from = -1;
    private String mobile;
    /**
     * 服务器返回的验证码
     */
    private String mCaptchaServer;
    private String thirdType;
    private String thirdNum;
    private String mCaptcha;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.login_regist1);
        ButterKnife.bind(this);
    }


    @Override
    public void initView() {
        tvAgreen.setText("《智慧后勤用户服务协议》");
        registTitle.setBack(true);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 0) {
                    ivUname.setVisibility(View.VISIBLE);
                } else {
                    ivUname.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getPerfromData();
    }

    @Override
    public RegistStep1Presenter createPresenter() {
        return new RegistStep1Presenter();
    }

    @Override
    public RegistStep1Model createModel() {
        return new RegistStep1ModelImpl();
    }

    private void getPerfromData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            from = extras.getInt("from");
            thirdType = extras.getString("thirdType");
            thirdNum = extras.getString("thirdNum");
            if (from == Constants.FORGET) {
                registTitle.setTitle("找回密码");
                ll_complain.setVisibility(View.VISIBLE);
            } else if (from == Constants.REGIST) {
                registTitle.setTitle("注册");
                ll_pact.setVisibility(View.VISIBLE);
            } else if (from == Constants.BIND) {
                registTitle.setTitle("绑定手机号");
            }
        }
    }

    @OnClick({R.id.btn_getcode, R.id.tv_agreen, R.id.tv_next_step, R.id.iv_uname, R.id.complaint})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_getcode:
                doGetCode();
                break;
            case R.id.tv_agreen:
                turnToAgreen();
                break;
            case R.id.tv_next_step:
                doNext();
                break;
            case R.id.iv_uname:
                etPhone.setText("");
                break;
            case R.id.complaint:
                startActivity(new Intent(RegistStepOneActivity.this, AccountComplainActivity.class));
                break;
        }
    }

    /**
     * 下一步
     */
    public void doNext() {
        //获取验证码和电话号码
        String captcha = etCode.getText().toString().trim();
        mobile = etPhone.getText().toString().trim();
        if (!isPhoneChecked(mobile)) {
            return;
        }
        if (TextUtils.isEmpty(captcha)) {
            showToast("请填写验证码");
            return;
        }

        if (!checkbox.isChecked()) {
            showToast("请阅读并同意服务协议");
            return;
        }
        if (mCaptcha == null || !mCaptcha.equals(captcha)) {
            showToast("验证码不正确");
            return;
        }

        CommonUtil.closeSoftKeyboard(this, etCode);
        // TODO 注册或修改密码
        turnToPwdSet(captcha);
    }

    private void turnToPwdSet(String captcha) {
        Bundle b = new Bundle();
        b.putString("mobile", mobile);
        b.putString("captcha", captcha);
        b.putString("thirdType", thirdType);
        b.putString("thirdNum", thirdNum);
        b.putInt("from", from);
        Intent intent = new Intent(this, RegistStepTwoActivity.class);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    private void turnToAgreen() {
        UIManager.turnToAct(this, AgreeActivity.class);
    }

    /**
     * 返回
     */
    public void doBack() {
        finish();
    }

    /**
     * 获取验证码
     */
    public void doGetCode() {
        mobile = etPhone.getText().toString();
        if (!isPhoneChecked(mobile)) {
            return;
        }
        if (from == 0) {
            getPresenter().getData(mobile, 0);
        } else {
            getPresenter().getData(mobile, 1);
        }
    }


    private void doTimer() {
        if (runnable == null) {
            runnable = new MyRunnable();
        }
        handler.post(runnable);
        btnGetcode.setClickable(false);
    }

    void stopTimmer() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        totalSecond = MAX_TIME;
        // 倒计时完成后让按钮可点击
        btnGetcode.setEnabled(true);
        btnGetcode.setClickable(true);
        btnGetcode.setText("重新获取验证码");
    }

    public MyRunnable runnable;

    @Override
    public void success(CaptchaBean captchaBean) {
        doTimer();
        if (captchaBean != null) {
            Log.e("captcha=====", captchaBean.captcha);
            this.mCaptcha = captchaBean.captcha;
        }
    }

    //获得绑定结果
    @Override
    public void bindResult(BindPhoneBean bindPhoneBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    public class MyRunnable implements Runnable {

        @SuppressLint("NewApi")
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            btnGetcode.setText(totalSecond + "s后重新发送");
            totalSecond--;
            if (totalSecond < 0) {
                stopTimmer();
            }
        }
    }

    /**
     * 手机号校验
     *
     * @param mobile
     * @return
     */
    private boolean isPhoneChecked(String mobile) {
        if (StringUtil.isNullOrEmpty(mobile)) {
            showToast("请输入手机号");
            return false;
        }
        if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
            showToast("请输入正确的手机号码");
            return false;
        }
        return true;
    }

    /**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doBindRequest(final String mobile, final String captcha) {
        showProgressDialog();
        String userId = softApplication.getUserInfo() == null ? null : softApplication.getUserInfo().uid;
        Request request = RequestMaker.getInstance().doBindRequest(thirdType, thirdNum, mobile, captcha, "0", null);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);
                finishActivityAndAboveIt(LoginActivity.class.getName());
            }


            @Override
            public void onCodeError(UserResponse result) {
                if (result.errCode == -7) {
                    turnToPwdSet(captcha);
                } else {
                    super.onCodeError(result);
                }
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }


    *//**
     * 校验验证码,下一步
     *
     * @param captcha
     * @param mobile
     *//*
    private void doNextRequest(final String mobile, final String captcha) {
        showProgressDialog();
        Request request = RequestMaker.getInstance().getCheckCaptchaRequest(mobile, captcha);
        getNetWorkDate(request, new SubBaseParser<SubBaseResponse>(SubBaseResponse.class), new OnCompleteListener<SubBaseResponse>(this) {
            @Override
            public void onSuccessed(SubBaseResponse result, String resultString) {
                Bundle b = new Bundle();
                b.putString("mobile", mobile);
                b.putString("captcha", captcha);
                b.putInt("from", from);
                UIManager.turnToAct(RegistStep1Activity.this, RegistStep2Activity.class, b);
            }

            @Override
            public void onCompleted(SubBaseResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }*/

}
