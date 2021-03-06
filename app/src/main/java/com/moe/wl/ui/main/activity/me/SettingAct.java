package com.moe.wl.ui.main.activity.me;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.DataClearUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.activity.LoginActivity;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import mvp.cn.util.StringUtil;

public class SettingAct extends Base2Activity {


    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.rl_account_saft)
    RelativeLayout rlAccountSaft;
    @BindView(R.id.rl_msg_setting)
    RelativeLayout rlMsgSetting;
    @BindView(R.id.rl_yijian)
    RelativeLayout rlYijian;
    @BindView(R.id.tv_kefu_phone)
    TextView tvKefuPhone;
    @BindView(R.id.iv_call)
    ImageView ivCall;
    @BindView(R.id.rl_kefu_phone)
    RelativeLayout rlKefuPhone;
    @BindView(R.id.tv_clear_cache)
    TextView tvClearCache;
    @BindView(R.id.rl_clear_cache)
    RelativeLayout rlClearCache;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rl_version)
    RelativeLayout rlVersion;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.activity_setting)
    LinearLayout activitySetting;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initTitle();
        setCacheSize();
        float versionCode = getVersionCode(this);
        tvVersion.setText("v" + versionCode + "");
    }

    private void initTitle() {
        title.setTitle("系统设置");
        title.setBack(true);
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    private float getVersionCode(Context context) {
        float versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = Float.valueOf(getPackageManager().getPackageInfo(getPackageName(), 0).versionCode);
            //getPackageManager().getPackageInfo(getPackageName(),0).versionCode
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 设置缓存大小
     */
    private void setCacheSize() {
        try {
            tvClearCache.setText(DataClearUtils.getAllCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.rl_account_saft, R.id.rl_msg_setting, R.id.rl_yijian, R.id.rl_kefu_phone, R.id.rl_clear_cache, R.id.rl_version, R.id.rl_about_us, R.id.bt_out_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_account_saft:
                Intent intent = new Intent(this, AcountSaftActivity.class);
                intent.putExtra("from", Constants.ACCOUNT_SAFT);
                startActivity(intent);
                break;
            case R.id.rl_msg_setting:
                Intent intent1 = new Intent(this, MsgSettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.rl_yijian:
                Intent intent2 = new Intent(this, LaifangshiyouActivity.class);
                intent2.putExtra("from", Constants.YIJIANFANKUI);
                startActivity(intent2);
                break;
            case R.id.rl_kefu_phone:
                callPhone();
                break;
            case R.id.rl_clear_cache:
                clearCache();
                break;
            case R.id.rl_version:
                Intent intent5 = new Intent(this, VersionActivity.class);
                startActivity(intent5);
                break;
            case R.id.rl_about_us:
                Intent intent6 = new Intent(this, AboutUsActivity.class);
                startActivity(intent6);
                break;
            case R.id.bt_out_login:
                AlertDialog dialog = new AlertDialog(SettingAct.this);
                dialog.builder()
                        .setTitle("提示")
                        .setMsg("是否退出登录？")
                        .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPrefHelper.getInstance().setToken("");
                        SharedPrefHelper.getInstance().setPassword("");
                        Intent intent7 = new Intent(SettingAct.this, LoginActivity.class);
                        intent7.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent7);
                    }
                }).setNegativeButton("否", null).show();
                break;
        }
    }

    private void callPhone() {
        final String phone = tvKefuPhone.getText().toString().trim();
        if (StringUtil.isNullOrEmpty(phone)) {
            //getPresenter().getServicePhone();
            return;
        }
        new AlertDialog(SettingAct.this).builder()
                .setTitle("提示")
                .setMsg("拨打电话" + phone)
                .setPositiveButton("确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CallPhoneUtils.callPhone(phone, SettingAct.this);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }

    private void clearCache() {
        AlertDialog builder = new AlertDialog(this);

        builder.builder()
                .setTitle("提示")
                .setMsg("是否清理缓存数据")
                .setPositiveButton("是", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog("正在清理,请稍后");
                DataClearUtils.cleanApplicationData(SettingAct.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();
                        setCacheSize();
                    }
                }, 1000);
            }
        }).setNegativeButton("否", null).show();
    }

}
