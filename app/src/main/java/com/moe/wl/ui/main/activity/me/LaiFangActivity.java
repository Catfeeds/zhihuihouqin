package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.activity.SubmitSuccessActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.mywidget.CenterTimeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

public class LaiFangActivity extends Base2Activity {


    @BindView(R.id.laifang_title)
    TitleBar laifangTitle;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_room_num)
    EditText etRoomNum;
    @BindView(R.id.et_lname)
    EditText etLname;
    @BindView(R.id.et_identity_num)
    EditText etIdentityNum;
    @BindView(R.id.et_mobile)
    EditText etMobile;

    @BindView(R.id.rb_once)
    RadioButton rbOnce;
    @BindView(R.id.rb_a_week)
    RadioButton rbAWeek;
    @BindView(R.id.rb_bangeyue)
    RadioButton rbBangeyue;
    @BindView(R.id.rb_long)
    RadioButton rbLong;
    @BindView(R.id.RG)
    RadioGroup RG;

    @BindView(R.id.activity_lai_fang)
    RelativeLayout activityLaiFang;
    @BindView(R.id.et_person_num)
    EditText etPersonNum;
    @BindView(R.id.arrave_time)
    TextView arraveTime;
    @BindView(R.id.rl_revice_time)
    RelativeLayout rlReviceTime;
    @BindView(R.id.et_department)
    EditText etDepartment;
    private int visitperiod = 1;
    private CenterTimeDialog dialog;
    private String arriveTime;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_lai_fang);
        ButterKnife.bind(this);
    }

    public void showDialog() {
        dialog = new CenterTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new CenterTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, int i2, int i3, int i4, int i5) {
                String hour, minute;
                if (i4 < 10) {
                    hour = "0" + i4;
                } else {
                    hour = "" + i4;
                }
                if (i5 < 10) {
                    minute = "0" + i5;
                } else {
                    minute = "" + i5;
                }

                arriveTime = i1 + "-" + i2 + "-" + i3 + " " + hour + ":" + minute;
                switch (DateUtil.compareDate(arriveTime, DateUtil.getTimeyyyyMMddHHmm())) {
                    case 1:
                        arraveTime.setText(arriveTime);
                        break;
                    case 2:
                        ToastUtil.showToast(LaiFangActivity.this, "预计到达时间已过！");
                        break;
                    default:
                        arraveTime.setText(arriveTime);
                        break;
                }

            }
        });

    }

    @Override
    protected void initView() {
        laifangTitle.setBack(true);
        laifangTitle.setTitle("来访信息");
        String realName = SharedPrefHelper.getInstance().getRealName();
        etName.setText(realName);
        getUserInfo();//获取用户信息
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rbOnce.getId() == checkedId) {
                    visitperiod = 1;
                } else if (rbAWeek.getId() == checkedId) {
                    visitperiod = 2;
                } else if (rbBangeyue.getId() == checkedId) {
                    visitperiod = 3;
                } else if (rbLong.getId() == checkedId) {
                    visitperiod = 4;
                }
            }
        });
    }

    @OnClick({R.id.tv_commit,R.id.rl_revice_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                postInfo();
                break;
            case R.id.rl_revice_time:
                showDialog();
                break;
        }
    }

    private void postInfo() {
        //受访人
        String username = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String roomunm = etRoomNum.getText().toString().trim();
        //来访人
        String lName = etLname.getText().toString().trim();
        String identityNum = etIdentityNum.getText().toString().trim();
        String mobile = etMobile.getText().toString().trim();
        String personNum = etPersonNum.getText().toString().trim();
        String time = arraveTime.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        if(TextUtils.isEmpty(phone)){
            showToast("请输入办公电话");
            return;
        }
        if(TextUtils.isEmpty(roomunm)){
            showToast("请输入受访人员房间号");
            return;
        }
        if(TextUtils.isEmpty(lName) ){
            showToast("请输入来访人员姓名");
            return;
        }
        if(TextUtils.isEmpty(identityNum) ){
            showToast("请输入来访人员身份证号码");
            return;
        }
        if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
            showToast("请输入正确的手机号码");
            return;
        }
        if (!VerifyCheck.isIDCardVerify(identityNum)) {
            showToast("你输入的身份证号码有误");
            return;
        }
        if(TextUtils.isEmpty(time)){
            showToast("请选择预计到达时间");
            return;
        }
        if(TextUtils.isEmpty(department)){
            showToast("请输入来访人员单位");
            return;
        }
        Observable observable = RetrofitUtils.getInstance().postBaifagnInfo
                (username, phone, roomunm, lName, mobile, identityNum, personNum,time+":00",department);
        showProgressDialog();
        observable.subscribe(new Subscriber<ActivityPostBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                Log.e("提交拜访信息失败", e.getMessage());
            }

            @Override
            public void onNext(ActivityPostBean postBean) {
                if (postBean.getErrCode() == 0) {
                    Intent intent = new Intent(LaiFangActivity.this, SubmitSuccessActivity.class);
                    intent.putExtra("from", Constants.VISITORS);
                    startActivity(intent);
                    finish();
                } else {
                    showToast(postBean.getMsg());
                }
            }
        });
    }

    public void getUserInfo() {
        Observable observable = RetrofitUtils.getInstance().getUserInfo();
        observable.subscribe(new Subscriber<UserInfoBean> (){
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("Throwable=getuserinfo"+e.getMessage());
            }

            @Override
            public void onNext(UserInfoBean info) {
                if(info.getErrCode()==0){
                    UserInfoBean.UserinfoEntity userinfo = info.getUserinfo();
                    if(userinfo!=null){
                        String tel = userinfo.getTel();//座机号
                        String roomnum = userinfo.getRoomnum();//房间号
                        etPhone.setText(tel);
                        etRoomNum.setText(roomnum);
                    }
                }
            }
        });
    }
}
