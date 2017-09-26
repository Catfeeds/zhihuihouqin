package cn.lc.model.ui.main.activity.ActivityRegistration;

import android.os.SystemClock;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.network.retrofit.RetrofitUtils;
import cn.lc.model.framework.widget.TitleBar;
import cn.lc.model.ui.main.activity.Base2Activity;
import cn.lc.model.ui.main.bean.ActivitySignBean;
import rx.Observable;
import rx.Subscriber;

public class FillInfoActivity extends Base2Activity {

    @BindView(R.id.activity_title)
    TitleBar titleBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    EditText etPhone;
    @BindView(R.id.tv_sign_up)
    TextView tvSignUp;
    @BindView(R.id.activity_fill_info)
    LinearLayout activityFillInfo;
    private String name;
    private String phone;
    private int aId;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_fill_info);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        aId = getIntent().getIntExtra("aId", 0);
        initTitle();
        initData();
    }

    private void initData() {
        etPhone.setText(phone);
        etPhone.setSelection(etPhone.length());
        tvName.setText(name);

    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("填写信息");
    }

    @OnClick(R.id.tv_sign_up)
    public void onViewClicked() {
        getSignData(aId, name, phone);
    }
    private void getSignData(int aId, String realName, String phoneNumber) {
        Observable sign = RetrofitUtils.getInstance().getActivitySign(aId, realName, phoneNumber);
        showProgressDialog();
        sign.subscribe(new Subscriber<ActivitySignBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Throwable", e.getMessage());
            }

            @Override
            public void onNext(ActivitySignBean signBean) {
                if (signBean.getErrCode() == 0) {
                    signSucc("恭喜您报名成功");
                } else if (signBean.getErrCode() == 1300) {
                    signSucc(signBean.getMsg());
                } else if (signBean.getErrCode() == 1301) {
                    signSucc(signBean.getMsg());
                } else {
                    Log.e("ActivitySignBean", signBean.getMsg());
                }
            }
        });
    }
    //包名成功
    private void signSucc(String s) {
        showToast(s);
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                finish();
            }
        }).start();
    }
}
