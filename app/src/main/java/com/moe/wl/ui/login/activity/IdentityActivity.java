package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.bean.Auth;
import com.moe.wl.ui.login.bean.CarInfo;
import com.moe.wl.ui.login.model.AuthModel;
import com.moe.wl.ui.login.modelimpl.AuthModelImpl;
import com.moe.wl.ui.login.presenter.AuthPresenter;
import com.moe.wl.ui.login.view.AuthView;
import com.moe.wl.ui.main.activity.me.CarTypeActivity;
import com.moe.wl.ui.main.activity.me.DepartmentActivity;
import com.moe.wl.ui.main.activity.me.NativesActivity;
import com.moe.wl.ui.main.activity.me.OfficeidActivity;
import com.moe.wl.ui.main.activity.me.SexActivity;
import com.moe.wl.ui.main.adapter.CarAdapter;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;
import com.moe.wl.ui.mywidget.TimeSelecterDayDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.VerifyCheck;

import static java.lang.reflect.Modifier.NATIVE;

public class IdentityActivity extends BaseActivity<AuthModel, AuthView, AuthPresenter> implements AuthView {

    private static final int REQUESTPOSTIONCODE = 10;
    private static final int SEXREQUESTCODE = 11;
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.tv_native)
    TextView tvNative;
    @BindView(R.id.rl_native)
    RelativeLayout rlNative;
    @BindView(R.id.tv_birth)
    TextView tvBirth;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.iv_position)
    ImageView ivPosition;
    @BindView(R.id.rl_positon)
    RelativeLayout rlPositon;
    @BindView(R.id.tv_department_num)
    TextView tvDepartmentNum;
    @BindView(R.id.rl_department_num)
    RelativeLayout rlDepartmentNum;
    @BindView(R.id.tv_officeid)
    TextView tvOfficeid;
    @BindView(R.id.rl_officeid)
    RelativeLayout rlOfficeid;
    @BindView(R.id.et_add_time)
    TextView etAddTime;
    @BindView(R.id.et_come_depart_time)
    TextView etComeDepartTime;
    @BindView(R.id.tv_room_num)
    EditText tvRoomNum;
    @BindView(R.id.tv_office_phone)
    EditText tvOfficePhone;
    @BindView(R.id.rv_chepaihao)
    NoSlideRecyclerView rvChepaihao;
    @BindView(R.id.tv_add_car_num)
    TextView tvAddCarNum;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.et_build_num)
    EditText etBuildNum;
    @BindView(R.id.tv_e_mail)
    EditText e_mail;

    private String name;
    private String phone;
    private String roomNum;
    private String officePhone;
    //    private String carType;
//    private String chePaiHao;
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

    private List<CarInfo> carList;
    private CarAdapter carAdapter;
    public int nationId; // 民族ID
    private int positionId; // 职务ID
    private int departId; // 部门ID
    private int officeId; // 处室ID
    public static final int DEPARTMENT = 100;
    public static final int OFFICEID = 101;
    public static final int CARTYPE = 102;
    private int index;
    private int roomnum;
    private String nation;
    private String sex;

    @Override
    public AuthPresenter createPresenter() {
        return new AuthPresenter();
    }

    @Override
    public AuthModel createModel() {
        return new AuthModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_identity);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        title.setBack(true);
        title.setTitle("身份信息");
        carList = new ArrayList<>();
        carList.add(new CarInfo());
        initList();
    }

    private void initList() {
        rvChepaihao.setLayoutManager(new NoScrollLinearLayoutManager(this));
        carAdapter = new CarAdapter(this, carList);
        rvChepaihao.setAdapter(carAdapter);
//        carAdapter.setData(carList);
        carAdapter.setChooseCarListener(new CarAdapter.OnChooseCarListener() {
            @Override
            public void onChooseCarClick(int position) {
                index = position;
                Intent intent = new Intent(IdentityActivity.this, CarTypeActivity.class);
                startActivityForResult(intent, CARTYPE);
            }
        });
        carAdapter.setOnChooseNumberListener(new CarAdapter.OnChooseNumberListener() {
            @Override
            public void onChooseNumberClick(int position) {
                index = position;
                show(true, position);
            }
        });
        carAdapter.setTextListener(new CarAdapter.OnTextListener() {
            @Override
            public void onTextChange(String content, int position) {
                carList.get(position).setSuffixcarcode(content);
            }
        });
    }

    private String str;

    private void show(final boolean isFirst, final int position) {
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
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, R.id.tv_item, lists);
        } else {
            adapter = new ArrayAdapter<>(this, R.layout.simple_list_item_1, R.id.tv_item, listTwo);
        }
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int po, long id) {
                if (isFirst) {
                    str += lists.get(po);
                    show(false, position);
                } else {
                    str += listTwo.get(po);
                    carList.get(position).setPrecarcode(str);
                    carAdapter.notifyDataSetChanged();
                }
                dlg.cancel();
            }
        });
    }

    //时间选择器
    private void selectTime(final int type) {
        TimeSelecterDayDialog dialog = new TimeSelecterDayDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new TimeSelecterDayDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, String i2, String i3) {
                String time = i1 + "-" + i2 + "-" + i3;
                if (type == 1) {
                    tvBirth.setText(time);
                } else if (type == 2) {
                    etAddTime.setText(time);
                } else {
                    etComeDepartTime.setText(time);
                }
            }
        });
    }

    //认证成功
    @Override
    public void authSucc() {
        SharedPrefHelper.getInstance().setAuthStatus(1);
        Intent intent = new Intent(this, AuthSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTPOSTIONCODE:
                    if (data != null) {
                        positionId = data.getIntExtra("positionId", 0);
                        String position = data.getStringExtra("position");
                        tvPosition.setText(position);
                    }
                    break;
                case SEXREQUESTCODE:
                    if (data != null) {
                        sex = data.getStringExtra("sex");
                        tvSex.setText(sex);
                    }
                    break;
                case NATIVE:
                    if (data != null) {
                        nationId = data.getIntExtra("id", 0);
                        nation = data.getStringExtra("nation");
                        tvNative.setText(nation);
                    }
                    break;
                case DEPARTMENT:
                    if (data != null) {
                        departId = data.getIntExtra("id", 0);
                        String depart = data.getStringExtra("depart");
                        tvDepartmentNum.setText(depart);
                        showToast("请重新选择处室");
                        officeId = 0;
                        tvOfficeid.setText("");
                    }
                    break;
                case OFFICEID:
                    if (data != null) {
                        officeId = data.getIntExtra("id", 0);
                        String officeName = data.getStringExtra("name");
                        tvOfficeid.setText(officeName);
                    }
                    break;
                case CARTYPE:
                    if (data != null) {
                        int carTypeId = data.getIntExtra("id", 0);
                        String carName = data.getStringExtra("typename");
                        carList.get(index).setCartypeid(carTypeId);
                        carList.get(index).setCarName(carName);
                        carAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.rl_sex, R.id.rl_native, R.id.rl_positon, R.id.rl_department_num, R.id.rl_officeid, R.id.tv_add_car_num, R.id.tv_commit            , R.id.ll_birth, R.id.ll_join_job, R.id.ll_arrive_office})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_sex: // 选择性别
                Intent intent = new Intent(this, SexActivity.class);
//                intent.putExtra("sex", tvSex.getText().toString().trim());
                startActivityForResult(intent, SEXREQUESTCODE);
                break;

            case R.id.rl_native: // 选择民族
                Intent intent4 = new Intent(this, NativesActivity.class);
                startActivityForResult(intent4, NATIVE);
                break;

            case R.id.rl_positon: // 选择职务
                Intent intent1 = new Intent(this, PositionActivity.class);
                startActivityForResult(intent1, REQUESTPOSTIONCODE);
                break;

            case R.id.rl_department_num: // 选择部门
                Intent intent3 = new Intent(this, DepartmentActivity.class);
                startActivityForResult(intent3, DEPARTMENT);
                break;

            case R.id.rl_officeid: // 选择处室
                if (departId == 0) {
                    showToast("请先选择部门");
                    return;
                }
                Intent intent2 = new Intent(this, OfficeidActivity.class);
                intent2.putExtra("DepartId", departId);
                startActivityForResult(intent2, OFFICEID);
                break;

            case R.id.ll_birth:
                selectTime(1);
                break;

            case R.id.ll_join_job:
                selectTime(2);
                break;

            case R.id.ll_arrive_office:
                selectTime(3);
                break;

            case R.id.tv_add_car_num:
                carList.add(new CarInfo());
                carAdapter.notifyDataSetChanged();
                break;

            case R.id.tv_commit:
                name = tvName.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    showToast("请填写姓名");
                    return;
                }
                String sex = tvSex.getText().toString().trim();
                if (TextUtils.isEmpty(sex)) {
                    showToast("请选择性别");
                    return;
                }
                if (nationId == 0) {
                    showToast("请选择民族");
                    return;
                }
                String birth = tvBirth.getText().toString().trim();
                if (TextUtils.isEmpty(birth)) {
                    showToast("请选择出生日期");
                    return;
                }
                phone = tvPhone.getText().toString().trim();
                if (!VerifyCheck.isMobilePhoneVerify(phone)) {
                    showToast("请输入正确的手机号码");
                    return;
                }
                if (positionId == 0) {
                    showToast("请选择职务");
                    return;
                }
                if (departId == 0) {
                    showToast("请选择部门");
                    return;
                }
                if (officeId == 0) {
                    showToast("请选择处室");
                    return;
                }
                String joinJobTime = etAddTime.getText().toString().trim();
                if (TextUtils.isEmpty(joinJobTime)) {
                    showToast("请选择参加工作时间");
                    return;
                }
                String arriveTime = etComeDepartTime.getText().toString().trim();
                if (TextUtils.isEmpty(arriveTime)) {
                    showToast("请选择来部时间");
                    return;
                }
                String buildNum = etBuildNum.getText().toString().trim();
                if (TextUtils.isEmpty(buildNum)) {
                    showToast("请填写楼号");
                    return;
                }
                roomNum = tvRoomNum.getText().toString().trim();
                if (TextUtils.isEmpty(roomNum)) {
                    showToast("请填写办公房间号");
                    return;
                }
                officePhone = tvOfficePhone.getText().toString().trim();
                if (TextUtils.isEmpty(officePhone)) {
                    showToast("请填写办公电话");
                    return;
                }
                String eMail = e_mail.getText().toString().trim();
                if (!VerifyCheck.isEmailVerify(eMail)) {
                    showToast("请填写正确的工作邮箱");
                    return;
                }
                Auth auth = new Auth(birth, buildNum, departId, eMail, arriveTime, phone, name, nationId,
                        officeId, officePhone, positionId, roomNum, "男".equals(sex) ? 1 : 0, joinJobTime);
//                for (int i = 0; i < carList.size(); i++) {
//                }
                getPresenter().getData(auth, carList);
                break;
        }
    }
}
