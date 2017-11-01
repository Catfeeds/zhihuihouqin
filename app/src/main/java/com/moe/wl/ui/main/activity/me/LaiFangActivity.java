package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.Base2Activity;
import com.moe.wl.ui.main.activity.Library.JieYueSuccActivity;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.mywidget.CenterTimeDialog;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.DateUtil;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;
import rx.Observable;
import rx.Subscriber;

public class LaiFangActivity extends Base2Activity {

    private static final int LAIFANGSHIYOUREQUEST = 10;

    @BindView(R.id.laifang_title)
    TitleBar laifangTitle;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_room_num)
    EditText etRoomNum;
    @BindView(R.id.arrave_time)
    TextView arraveTime;
    @BindView(R.id.rl_revice_time)
    RelativeLayout rlReviceTime;
    @BindView(R.id.leave_time)
    TextView leaveTime;
    @BindView(R.id.rl_likai_time)
    RelativeLayout rlLikaiTime;
    @BindView(R.id.rb_once)
    RadioButton rbOnce;
    @BindView(R.id.rb_a_week)
    RadioButton rbAWeek;
    @BindView(R.id.rb_bangeyue)
    RadioButton rbBangeyue;
    @BindView(R.id.rb_long)
    RadioButton rbLong;
    @BindView(R.id.RG)
    RadioGroup rg;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et_car_type)
    EditText etCarType;
    @BindView(R.id.rl_car_type)
    LinearLayout rlCarType;
    @BindView(R.id.tv_shengfen)
    TextView tvShengfen;
    @BindView(R.id.et_chepaihao)
    EditText etChepaihao;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_lai_fang)
    RelativeLayout activityLaiFang;
    @BindView(R.id.text_reason)
    TextView textReason;

    private String str;
    private List<String> lists = Arrays.asList("京", "津", "冀", "晋", "蒙", "辽",
            "吉", "黑", "沪", "苏", "浙", "皖"
            , "闽", "赣", "鲁", "豫", "鄂", "湘"
            , "粤", "桂", "琼", "川", "贵", "云"
            , "渝", "藏", "陕", "甘", "青", "宁"
            , "新");
    private List<String> listTwo = Arrays.asList("A", "B", "C", "D", "E", "F"
            , "G", "H", "I", "J", "K", "L"
            , "M", "N", "O", "P", "Q", "R", "X"
            , "Y", "Z");
    private String shiyou;
    private CenterTimeDialog dialog;
    private int MAX_NUM = 5;
    private int visitperiod;
    private String arriveTime;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_lai_fang);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        laifangTitle.setBack(true);
        laifangTitle.setTitle("来访人员");
        //来访时间
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
        //车牌号字数的监听
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
                if (s.length() > MAX_NUM) {
                    s.delete(MAX_NUM, s.length());
                }
            }
        };
        etChepaihao.addTextChangedListener(watcher);
    }

    @OnClick({R.id.tv_commit, R.id.tv_shengfen, R.id.rl_laifangshiyou, R.id.rl_revice_time, R.id.rl_likai_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                postInfo();
                break;
            case R.id.tv_shengfen:
                showShengFenDialog();
                break;
            case R.id.rl_laifangshiyou:
                Intent intent = new Intent(this, LaifangshiyouActivity.class);
                intent.putExtra("arriveReason", shiyou);
                startActivityForResult(intent, LAIFANGSHIYOUREQUEST);
                break;
            case R.id.rl_revice_time:
                showTime(true);
                break;
            case R.id.rl_likai_time:
                showTime(false);
                break;
        }
    }

    private void postInfo() {
        String username = etName.getText().toString().trim();
        String mobile = etPhone.getText().toString().trim();
        String roomunm = etRoomNum.getText().toString().trim();
        String shengfen = tvShengfen.getText().toString().trim();
        String chepaihao = etChepaihao.getText().toString().trim();
        String arriveTime = arraveTime.getText().toString().trim();
        String tvleaveTime = leaveTime.getText().toString().trim();
        String carType = etCarType.getText().toString().trim();

        String str = shengfen + chepaihao;

        if (!VerifyCheck.isMobilePhoneVerify(mobile)) {
            showToast("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(arriveTime) || TextUtils.isEmpty(roomunm)
                || TextUtils.isEmpty(shiyou) || TextUtils.isEmpty(tvleaveTime)) {
            showToast("请将信息填写完整");
            return;
        }
        Observable observable = RetrofitUtils.getInstance().postBaifagnInfo(username, mobile, roomunm, shiyou, arriveTime, tvleaveTime, visitperiod + "", carType, str);
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
                    Intent intent = new Intent(LaiFangActivity.this, JieYueSuccActivity.class);
                    intent.putExtra("from", 2);
                    startActivity(intent);
                    finish();
                } else {
                    showToast(postBean.getMsg());
                }
            }
        });
    }

    private void showTime(final boolean isArrive) {
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
                if (isArrive) {
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
                } else {
                    switch (DateUtil.compareDate(i1 + "-" + i2 + "-" + i3 + " " + hour + ":" + minute, arriveTime)) {
                        case 1:
                            leaveTime.setText(i1 + "-" + i2 + "-" + i3 + " " + hour + ":" + minute);
                            break;
                        case 2:
                            ToastUtil.showToast(LaiFangActivity.this, "离开时间不能早于到达时间！");
                            break;
                        default:
                            leaveTime.setText(i1 + "-" + i2 + "-" + i3 + " " + hour + ":" + minute);
                            break;
                    }
                }

            }
        });
    }

    private void showShengFenDialog() {
        show(true);
    }

    private void show(final boolean isFirst) {
        final AlertDialog dlg = new AlertDialog.Builder(this).create();
        dlg.show();
        Window window = dlg.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,alertdialog.xml文件中定义view内容
        window.setContentView(R.layout.view_car_alertdialog);
        GridView gv = (GridView) window.findViewById(R.id.gv_car_num);
        ArrayAdapter<String> adapter;
        if (isFirst) {
            str = "";
//            tvShengfen.setText(str);
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, R.id.tv_item, lists);
        } else {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, R.id.tv_item, listTwo);

        }
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isFirst) {
                    str += lists.get(position);
                    show(false);
                } else {
                    str += listTwo.get(position);
                    tvShengfen.setText(str);
                }
                dlg.cancel();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == LAIFANGSHIYOUREQUEST && resultCode == Constants.SHIYOU) {
                shiyou = data.getStringExtra("shiyou");
                textReason.setText(shiyou);
            }
        }
    }
}
