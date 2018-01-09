package com.moe.wl.ui.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
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
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
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

    private String type;

    private final int ERROR = 0;
    private final int COMPLETE = 1;
    private final int CANCLE = 2;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ERROR:
                    showToast("登录失败");
                    break;
                case COMPLETE:
                    Platform platform = (Platform) msg.obj;
                    int action = msg.arg1;
                    if (action == Platform.ACTION_USER_INFOR) {
                        PlatformDb platformDb = platform.getDb();
                        String userId = platformDb.getUserId();
                        getPresenter().thirdLogin(userId, type);
                    }
                    platform.removeAccount(true);
                    break;
                case CANCLE:
                    showToast("取消登录");
                    break;
            }
        }
    };

    @Override
    public void setContentLayout() {
        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        controlKeyboardLayout(findViewById(R.id.scrollview), findViewById(R.id.l_cb_remenberPwd));
        if (/*SharedPrefHelper.getInstance().isRememberAccount()
                && */!TextUtils.isEmpty(SharedPrefHelper.getInstance().getUserId())
                && !TextUtils.isEmpty(SharedPrefHelper.getInstance().getToken())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        initTitle();
        //  ShareSDK.initSDK(this);
        etUname.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        if (SharedPrefHelper.getInstance().getPhoneNumber().length() > 0) {
            allCancle.setVisibility(View.VISIBLE);
        }
        etPsw.setText(SharedPrefHelper.getInstance().getPassword());
        etPsw.setSelection(SharedPrefHelper.getInstance().getPassword().length());
        if (SharedPrefHelper.getInstance().getPassword().length() > 0) {
            clearPassWord.setVisibility(View.VISIBLE);
        }
        etUname.setCursorVisible(false);
        etUname.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    etUname.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
        etPsw.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    etUname.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
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
//        titleBar.setBack(true);
        titleBar.setTitle("登录");
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
                wechatLogoin();
                break;
            case R.id.l_iv_weibo:
                sinaWeiboLogoin();
                break;
            case R.id.l_iv_qq:
                qqLogin();
                break;
        }
    }

    /**
     * 登录按钮的location坐标的y值，用来计算软键盘弹出后rootview向上滑动的高度
     */
    private int btnY = 0;

    /**
     * @param root         最外层布局，需要调整的布局
     * @param scrollToView 被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        Rect rect = new Rect();
                        // 获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);
                        // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                        int rootInvisibleHeight = root.getRootView()
                                .getHeight() - rect.bottom;
                        Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                        Log.i("tag", "bottom的高度" + rect.bottom);
                        // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                        if (rootInvisibleHeight > 100) {
                            //软键盘弹出来的时候
                            int[] location = new int[2];
                            // 获取scrollToView在窗体的坐标
                            scrollToView.getLocationInWindow(location);

                            //btnY的初始值为0，一旦赋过一次值就不再变化
                            if (btnY == 0) {
                                btnY = location[1];
                            }

                            // 计算root滚动高度，使scrollToView在可见区域的底部
                            int srollHeight = (btnY + scrollToView
                                    .getHeight()) - rect.bottom;

                            root.scrollTo(0, srollHeight);
                        } else {
                            // 软键盘没有弹出来的时候
                            root.scrollTo(0, 0);
                        }
                    }
                });
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
    public void showToast() {

    }

    @Override
    public void loginSuccess(LoginBean loginBean) {
        if (lCbRemenberPwd.isChecked()) {
            LogUtils.d("选中");
            SharedPrefHelper.getInstance().setRememberAccount(true);
            SharedPrefHelper.getInstance().setPassword(etPsw.getText().toString().trim());
        } else {
            LogUtils.d("未选中");
            SharedPrefHelper.getInstance().setRememberAccount(false);
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

    @Override
    public void binding(String loginType, String thirdNum) {
        //第三登录没有绑定手机号
        showToast("请绑定手机号");
        Bundle b = new Bundle();
        b.putInt("from", Constants.BIND);
        b.putString("thirdType", loginType);
        b.putString("thirdNum", thirdNum);
        UIManager.turnToAct(this, RegistStepOneActivity.class, b);
    }

    /**
     * 微信第三方登录
     */

    private void wechatLogoin() {
        type = "3";
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        wechat.SSOSetting(true);
        authorize(wechat);
    }

    /**
     * 新浪第三方登录
     */
    private void sinaWeiboLogoin() {
        type = "2";
        Platform sina = ShareSDK.getPlatform(SinaWeibo.NAME);
        sina.SSOSetting(true);
        authorize(sina);
    }

    /**
     * qq第三方登录
     */
    private void qqLogin() {
        type = "1";
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        qq.SSOSetting(true);
        authorize(qq);
    }


    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }
        //判断指定平台是否已经完成授权
        if (plat.isAuthValid()) {
            //如果用户已授权，则取消授权
            plat.removeAccount();
        }
        plat.setPlatformActionListener(new MyPlatformActionListener());
        //获取用户资料
        plat.showUser(null);
    }

    public class MyPlatformActionListener implements PlatformActionListener {

        @Override
        public void onCancel(Platform platform, int arg1) {
            handler.sendEmptyMessage(CANCLE);
        }

        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> map) {
            Message message = handler.obtainMessage();
            message.obj = platform;
            message.arg1 = action;
            message.what = COMPLETE;
            handler.sendMessage(message);

        }

        @Override
        public void onError(Platform platform, int arg1, Throwable arg2) {
            platform.removeAccount(true);
            handler.sendEmptyMessage(ERROR);
        }

    }


}
