package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
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
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.UserInfoBean;
import com.moe.wl.ui.mywidget.CenterTimeDialog;
import com.moe.wl.ui.mywidget.LaiFangSMSPop;
import com.moe.wl.ui.mywidget.StringListDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.wechat.friends.Wechat;
import lc.cn.thirdplatform.sharesdk.onekeyshare.OnekeyShare;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

import static com.moe.wl.R.id.et_phone;

public class LaiFangActivity extends Base2Activity {

    @BindView(R.id.laifang_title)
    TitleBar laifangTitle;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_name)
    TextView etName;
    @BindView(et_phone)
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
    private StringListDialog shareDialog;
    private String arriveTime;
    private LaiFangSMSPop pop;

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
        laifangTitle.setTitleRight("分享");
        laifangTitle.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shareDialog == null) {
                    shareDialog = new StringListDialog(LaiFangActivity.this, R.style.dialog_style);
                    List<String> itemList = new ArrayList<>();
                    itemList.add("短信分享");
                    itemList.add("微信分享");
                    itemList.add("取消");
                    shareDialog.setData(itemList);
                    shareDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:// 短信分享
                                    shareDialog.dismiss();
                                    pop = new LaiFangSMSPop(LaiFangActivity.this);
                                    pop.showAtLocation(findViewById(R.id.activity_lai_fang), Gravity.CENTER, 0, 0);
                                    pop.setOnSureClick(new LaiFangSMSPop.OnSureClick() {
                                        @Override
                                        public void onClick(String content) {
                                            if (!VerifyCheck.isMobilePhoneVerify(content)) {
                                                showToast("手机号码格式不正确");
                                                return;
                                            } else {
                                                getShareUrl("1", content);
                                            }
                                        }
                                    });
                                    break;
                                case 1:// 微信分享
                                    shareDialog.dismiss();
                                    getShareUrl("2", "");
                                    break;
                                case 2:// 取消
                                    shareDialog.dismiss();
                                    break;
                            }
                        }
                    });
                }
                shareDialog.show();
            }
        });
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

    // 获取分享的url
    private void getShareUrl(final String type, String content) {
        if (etPhone.getText().toString().trim().length() == 0) {
            showToast("请填写办公电话");
            return;
        }
        if (etRoomNum.getText().toString().trim().length() == 0) {
            showToast("请填写房间号");
            return;
        }
        Observable observable = RetrofitUtils.getInstance().getLaiFangShareUrl(etRoomNum.getText().toString().trim(),
                content, type, etPhone.getText().toString().trim(), SharedPrefHelper.getInstance().getRealName());
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
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
            public void onNext(CollectBean bean) {
                if (bean.getErrCode() == 0) {
                    if ("2".equals(type)) {
                        showShare(bean.getUrl());
                    } else {
                        pop.dismiss();
                        showToast("分享成功");
                    }
                } else {
                    showToast(bean.getMsg());
                }
            }
        });
    }

    private void showShare(String url) {
        //快捷分享，没有九宫格，只有编辑页
        //Using onekeyshare to share which provide some ui
        OnekeyShare oks = new OnekeyShare();
        //分享时Notification的图标和文字
        //Setting the notification of picture and content on status bar
        //oks.setNotification(R.drawable.ic_launcher, "Gtpass");
        //设置编辑页的初始化选中平台，设置后，就没有九格宫
        //Setting the share of weibo platform
        oks.setPlatform(Wechat.NAME);
        oks.setTitle("教育部");
        //text是分享文本,the content to share
        oks.setText("来访人员信息简介");
        //网络图片地址,the picture to share
        oks.setImageUrl("http://casemeet.oss-cn-beijing.aliyuncs.com/2017080214260236353118.png");
        oks.setUrl(url);
        //设置platform后，silent=true,没有界面，直接分享；silent=false,就有编辑界面，没有就九格宫
        //开发者可以自己修改，玩玩
        //If the params of platform is setted ,and siletn param is true , then it will share on background
        oks.setSilent(false);
        //执行动作, Action share
        oks.show(LaiFangActivity.this);
    }

    @OnClick({R.id.tv_commit, R.id.rl_revice_time})
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
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入办公电话");
            return;
        }
        if (TextUtils.isEmpty(roomunm)) {
            showToast("请输入受访人员房间号");
            return;
        }
        if (TextUtils.isEmpty(lName)) {
            showToast("请输入来访人员姓名");
            return;
        }
        if (TextUtils.isEmpty(identityNum)) {
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
        if (TextUtils.isEmpty(time)) {
            showToast("请选择预计到达时间");
            return;
        }
        if (TextUtils.isEmpty(department)) {
            showToast("请输入来访人员单位");
            return;
        }
        Observable observable = RetrofitUtils.getInstance().postBaifagnInfo
                (username, phone, roomunm, lName, mobile, identityNum, personNum, time + ":00", department);
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
        observable.subscribe(new Subscriber<UserInfoBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
                LogUtils.i("Throwable=getuserinfo" + e.getMessage());
            }

            @Override
            public void onNext(UserInfoBean info) {
                if (info.getErrCode() == 0) {
                    UserInfoBean.UserinfoEntity userinfo = info.getUserinfo();
                    if (userinfo != null) {
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
