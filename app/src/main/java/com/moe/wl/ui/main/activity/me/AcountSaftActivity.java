package com.moe.wl.ui.main.activity.me;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.activity.RegistStep2Activity;
import com.moe.wl.ui.main.activity.Base2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

import com.moe.wl.ui.login.bean.CaptchaBean;

import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

public class AcountSaftActivity extends Base2Activity {


    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_send_code)
    TextView tvSendCode;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.bt_next)
    TextView btNext;
    @BindView(R.id.activity_acount_saft)
    LinearLayout activityAcountSaft;
    private Handler handler = new Handler();
    private int totalSecond = MAX_TIME;
    private static final int MAX_TIME = 60;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_acount_saft);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        title.setTitle("验证手机号");
        title.setBack(true);
    }

    @OnClick(R.id.tv_send_code)
    public void onViewClicked() {
        String mobile = etMobile.getText().toString();
        if (!isPhoneChecked(mobile)) {
            return;
        }
        getCaptcha(mobile);
        doTimer();
    }

    private void getCaptcha(String mobile) {
        Observable observable = RetrofitUtils.getInstance().getCaptcha(mobile, Constants.REGIST);
        showProgressDialog();
        observable.subscribe(new Subscriber<CaptchaBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("获取验证码失败了", e.getMessage());
            }

            @Override
            public void onNext(CaptchaBean captchaBean) {
                if(captchaBean.errCode==0){
                    Intent intent = new Intent(AcountSaftActivity.this, RegistStep2Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void doTimer() {
        if (runnable == null) {
            runnable = new MyRunnable();
        }
        handler.post(runnable);
        tvSendCode.setClickable(false);
    }

    void stopTimmer() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
            runnable = null;
        }
        totalSecond = MAX_TIME;
        // 倒计时完成后让按钮可点击
        tvSendCode.setEnabled(true);
        tvSendCode.setClickable(true);
        tvSendCode.setText("重新获取验证码");
    }

    public MyRunnable runnable;

    public class MyRunnable implements Runnable {

        @SuppressLint("NewApi")
        @Override
        public void run() {
            handler.postDelayed(runnable, 1000);
            tvSendCode.setText(totalSecond + "s后重新发送");
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
}
