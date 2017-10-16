package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.application.SoftApplication;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.manager.UIManager;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.bean.LoginBean;
import com.moe.wl.ui.login.model.LoginModel;
import com.moe.wl.ui.login.modelimpl.LoginModelImpl;
import com.moe.wl.ui.login.presenter.LoginPresenter;
import com.moe.wl.ui.login.view.LoginView;
import com.moe.wl.ui.main.activity.MainActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import lc.cn.thirdplatform.sharesdk.login.LoginApi;
import lc.cn.thirdplatform.sharesdk.login.OnLoginListener;
import mvp.cn.util.CommonUtil;
import mvp.cn.util.CrcUtil;


/**
 * 登录
 *
 * @author --FY
 * @version 创建时间：2015-8-3 上午11:07:24
 */
public class LoginActivity extends BaseActivity<LoginModel, LoginView, LoginPresenter> implements LoginView {

    @BindView(R.id.login_title)
    TitleBar titleBar;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_uname)
    EditText etUname;
    @BindView(R.id.iv_all_cancle)
    ImageView allCancle;
    @BindView(R.id.clear_pass_word)
    ImageView clearPassWord;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.l_tv_register)
    TextView lTvRegister;
    @BindView(R.id.l_tv_findPsw)
    TextView lTvFindPsw;
    @BindView(R.id.l_cb_remenberPwd)
    CheckBox lCbRemenberPwd;
    @BindView(R.id.l_iv_wecat)
    TextView lIvWecat;
    @BindView(R.id.l_iv_weibo)
    TextView lIvWeibo;
    @BindView(R.id.l_iv_qq)
    TextView lIvQq;

    private String mobile;
    private String pwd;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        initTitle();
        //  ShareSDK.initSDK(this);
        LogUtils.d("账号：" + SharedPrefHelper.getInstance().getPhoneNumber() + "密码：" + SharedPrefHelper.getInstance().getPassword());
        etUname.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        etPsw.setText(SharedPrefHelper.getInstance().getPassword());
        etPsw.requestFocus(); // 获取焦点 光标出现
        etPsw.setSelection(SharedPrefHelper.getInstance().getPassword().length());
        lCbRemenberPwd.setChecked(SharedPrefHelper.getInstance().isRememberPassWord());
        etUname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    allCancle.setVisibility(View.VISIBLE);
                } else {
                    allCancle.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0) {
                    clearPassWord.setVisibility(View.VISIBLE);
                } else {
                    clearPassWord.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("登陆");
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginModel createModel() {
        return new LoginModelImpl();
    }

    @OnClick({R.id.iv_all_cancle, R.id.clear_pass_word, R.id.l_tv_register, R.id.l_tv_findPsw,
            R.id.bt_login, R.id.l_iv_wecat, R.id.l_iv_weibo, R.id.l_iv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_all_cancle:
                etUname.setText("");
                break;
            case R.id.clear_pass_word:
                etPsw.setText("");
                break;
            case R.id.l_tv_register:
                turnToRegist();
                break;
            case R.id.l_tv_findPsw:
                turnToFindPwd();
                break;
            case R.id.bt_login:
                doLogin();
                break;
            case R.id.l_iv_wecat:
//                doLoginPlatForm("3", Wechat.NAME);
                doLoginThree(Wechat.NAME);
                break;
            case R.id.l_iv_weibo:
//                doLoginPlatForm("2", SinaWeibo.NAME);
                doLoginThree(SinaWeibo.NAME);
                break;
            case R.id.l_iv_qq:
//                doLoginPlatForm("1", QQ.NAME);
                doLoginThree(QQ.NAME);
                break;
        }
    }

    /**
     * 返回
     */
    public void doBack() {
        finish();
    }

    /**
     * 找回密码
     */
    public void turnToFindPwd() {
        Bundle b = new Bundle();
        b.putInt("from", Constants.FORGET);
        UIManager.turnToAct(this, RegistStepOneActivity.class, b);
    }

    /**
     * 注册
     */
    public void turnToRegist() {
        Bundle b = new Bundle();
        b.putInt("from", Constants.REGIST);
//        UIManager.turnToAct(this, RegistStep1Activity.class, b);
        UIManager.turnToAct(this, RegistStepOneActivity.class, b);
    }

    /**
     * 注册
     *
     * @param thirdType
     */
    public void turnToBind(String thirdType, String thirdNum) {
        Bundle b = new Bundle();
        b.putInt("from", Constants.BIND);
        b.putString("thirdType", thirdType);
        b.putString("thirdNum", thirdNum);
        UIManager.turnToAct(this, RegistStep1Activity.class, b);
    }

    /**
     * 登录
     */
    public void doLogin() {
        mobile = etUname.getText().toString().trim();
        pwd = etPsw.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            showToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        String md5Pwd = null;
        try {
            md5Pwd = CrcUtil.MD5(pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPrefHelper.getInstance().setPhoneNumber(etUname.getText().toString().trim());
        CommonUtil.closeSoftKeyboard(this, etUname);
        // doLoginRequest(mobile, md5Pwd);
        getPresenter().getData(mobile, md5Pwd);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);
        if (resultCode == RESULT_OK) {
            String mobile = it.getStringExtra("mobile");
            String pwd = it.getStringExtra("pwd");
            etUname.setText(mobile);
            etPsw.setText(pwd);
        }
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        if (lCbRemenberPwd.isChecked()) {
            LogUtils.d("选中");
            SharedPrefHelper.getInstance().setPassword(etPsw.getText().toString().trim());
        } else {
            LogUtils.d("未选中");
            SharedPrefHelper.getInstance().setPassword("");
        }
        SharedPrefHelper.getInstance().setRememberPassWord(lCbRemenberPwd.isChecked());
        if (loginBean.getErrCode() == 0) {
            SoftApplication.softApplication.setUserInfo(loginBean.getUserinfo());
            SharedPrefHelper.getInstance().setToken(loginBean.getToken());
            LogUtils.d("登陆返回Token():.....", loginBean.getToken() + "");
            SharedPrefHelper.getInstance().setSex(loginBean.getUserinfo().getSex());
            SharedPrefHelper.getInstance().setuserId(loginBean.getUserinfo().getUserId() + "");
            SharedPrefHelper.getInstance().setNickname(loginBean.getUserinfo().getNickname());
            SharedPrefHelper.getInstance().setuserPhoto(loginBean.getUserinfo().getPhoto());
            SharedPrefHelper.getInstance().setRealName(loginBean.getUserinfo().getRealname());
            SharedPrefHelper.getInstance().setNation(loginBean.getUserinfo().getNation());
            SharedPrefHelper.getInstance().setMobile(loginBean.getUserinfo().getMobile() + "");
            //getPresenter().getToke(loginBean.getUserId()+"");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.e("msg++++", loginBean.getMsg());
            showToast(loginBean.getMsg());
        }
    }

    // 三方登录
    private void doLoginThree(String platformName) {
        LoginApi api = new LoginApi();
        //设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) {
                // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                // 此处全部给回需要注册
                LogUtils.d("platform : " + platform);
                int type;
                if (QQ.NAME.equals(platform)) {
                    // QQ 1
                    type = 1;
                } else if (SinaWeibo.NAME.equals(platform)) {
                    // 微博 2
                    type = 2;
                } else if (Wechat.NAME.equals(platform)) {
                    // 微信 3
                    type = 3;
                }
                String id = (String) res.get("uid");



                return true;
            }

            @Override
            public boolean onRegister(Object info) {
                return false;
            }

            @Override
            public boolean onError() {
                return false;
            }

        });
        api.login(this);
    }

 /* *//**//*  private void doLoginRequest(final String mobile, final String md5Pwd) {
        showProgressDialog();
        Request request = RequestMaker.getInstance().getLoginRequest(mobile, md5Pwd);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {

            @Override
            public void onSuccessed(UserResponse result, String resultString) {

                SharedPrefHelper.getInstance().setUserInfo(resultString);

                SharedPrefHelper.getInstance().setLoginAccount(mobile);
                SharedPrefHelper.getInstance().setLoginPwd(etPsw.getText().toString().trim());
                softApplication.setUserInfo(result.data);
                softApplication.setAlias(String.format("jpush%suser", result.data.uid));
                *//**//**//**//**
     * 登录环信
     *//**//**//**//*
                loginHuanxinServer(mobile, "123456");

                //未完善资料的
//                isLoginAndAuthOk();
//                UIManager.turnToAct(LoginActivity.this, MainActivity.class);
                getNickAndAvatar();
                finish();
            }

            @Override
            public void onCompleted(UserResponse result, String resultString) {
                dismissProgressDialog();
            }
        });
    }

    public void loginHuanxinServer(final String uname, final String pwd) {
        LogUtil.log("jhys" + uname + "user" + "===========用户名======================");
        EMClient.getInstance().login("jhys" + uname + "user", pwd, new EMCallBack() {
            @Override
            public void onSuccess() {
                try {
                    EMClient.getInstance().chatManager().loadAllConversations();
                } catch (Exception e) {
                    e.printStackTrace();

                }
                softApplication.setHXAutoLogin(true);
            }

            @Override
            public void onProgress(int progress, String status) {
            }

            @Override
            public void onError(final int code, final String message) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        LogUtil.log("环信登录失败");
                        LogUtil.log("环信登录失败" + message);
                    }
                });
            }
        });
    }

    *//**//**//**//**
     * 三方登陆服务器
     *//**//**//**//*
    private void doLoginByThirdPlatformRequest(final String thirdNum, final String thirdType) {
//        showProgressDialog();
        Request request = RequestMaker.getInstance().getLoginByThirdPlatformRequest(thirdNum, thirdType);
        getNetWorkDate(request, new SubBaseParser<UserResponse>(UserResponse.class), new OnCompleteListener<UserResponse>(this) {

            *//**//**//**//**
     -3： "未绑定手机号"
     -2： "暂时未开通该三方登录方式"
     -1 ： "参数异常"
     -5 ： "该三方账号已绑定其他手机号"
     -6 ：  "该手机号已绑定其他账号"
     *//**//**//**//*
            @Override
            public void onSuccessed(UserResponse result, String resultString) {
                SoftApplication.softApplication.setUserInfo(result.data);

                getNickAndAvatar();

            }

            @Override
            public void onCodeError(UserResponse result) {
                if (result.errCode == -3) {
                    turnToBind(thirdType, thirdNum);
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


    *//**//*

    *//**/

    /**
     * 三方登录
     *//**//**//**//*
    private void doLoginPlatForm(final String thirdType, String platformName) {
        showProgressDialog("登录中");

        String num = new Random().nextInt(100) * 100 + "";

//        doLoginByThirdPlatformRequest("55555", thirdType);

        LoginApi api = new LoginApi(); // 设置登陆的平台后执行登陆的方法
        api.setPlatform(platformName);
        api.setOnLoginListener(new OnLoginListener() {
            public boolean onLogin(String platform, HashMap<String, Object> res) { // 在这个方法填写尝试的代码，返回true表示还不能登录，需要注册
                String thirdId = res.get("uid").toString();// ID
                // String tName = res.get("name").toString();// 用户名
                // String tDescription = res.get("description").toString();// 描述
                // String tVatar = res.get("profile_image_url").toString();//
                // 头像链接
                LogUtil.log("tId====" + thirdId);

                doLoginByThirdPlatformRequest(thirdId, thirdType);
                return true;
            }

            public boolean onRegister(UserResponse info) {
                // 填写处理注册信息的代码，返回true表示数据合法，注册页面可以关闭
                return true;
            }

            @Override
            public boolean onError() {
                dismissProgressDialog();
                return false;
            }
        });
        api.login(this);

    }
    /*daozhe */
    @Override
    public void showToast() {

    }


}