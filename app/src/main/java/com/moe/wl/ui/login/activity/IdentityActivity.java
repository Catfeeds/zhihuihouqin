package com.moe.wl.ui.login.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.moe.wl.ui.main.adapter.CarAdapter;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

import static java.lang.reflect.Modifier.NATIVE;

public class IdentityActivity extends BaseActivity<AuthModel, AuthView, AuthPresenter> implements AuthView {

    private static final int REQUESTPOSTIONCODE = 10;
    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_native)
    TextView tvNative;
    @BindView(R.id.rl_native)
    RelativeLayout rlNative;
    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_identity_num)
    EditText tvIdentityNum;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.iv_position)
    ImageView ivPosition;
    @BindView(R.id.rl_positon)
    RelativeLayout rlPositon;
    @BindView(R.id.et_build_num)
    EditText etBuildNum;
    @BindView(R.id.tv_room_num)
    EditText tvRoomNum;
    @BindView(R.id.tv_officeid)
    TextView tvOfficeid;
    @BindView(R.id.rl_officeid)
    RelativeLayout rlOfficeid;
    @BindView(R.id.tv_department_num)
    TextView tvDepartmentNum;
    @BindView(R.id.rl_department_num)
    RelativeLayout rlDepartmentNum;
    @BindView(R.id.tv_office_phone)
    EditText tvOfficePhone;
    /*@BindView(R.id.tv_car_type)
    TextView tvCarType;*/
   /* @BindView(R.id.rl_car_type)
    RelativeLayout rlCarType;*/
    @BindView(R.id.rv_chepaihao)
    NoSlideRecyclerView rvChepaihao;
    @BindView(R.id.tv_add_car_num)
    TextView tvAddCarNum;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private int positionId;
    private String name;
    private String phone;
    private String identityNum;
    private String roomNum;
    private String officePhone;
    private String carType;
    private String chePaiHao;
    private String str = "";
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
    private String preCarCode;
    private int MAX_NUM = 5;
    private String officeid;


    private List<CarInfo> carList;
    private CarAdapter carAdapter;
    public int nationId;
    private int departId;
    private CarInfo carInfos;
    public static final int DEPARTMENT=100;
    public static final int OFFICEID=101;
    public static final int CARTYPE=102;
    private int carTypeId;
    private int MAX_IDNUM=18;
    private int index;

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
        title.setTitle("身份认证");
        carList = new ArrayList<>();
        carList.add(new CarInfo("","",""));
        initList();
    }

    private void initList() {

        rvChepaihao.setLayoutManager(new NoScrollLinearLayoutManager(this));
        carAdapter = new CarAdapter(this);
        rvChepaihao.setAdapter(carAdapter);
        carAdapter.setData(carList);
        carAdapter.setListener(new CarAdapter.OnCarTypeItemClick() {
            @Override
            public void onCarTypeItemClick(CarInfo carInfo,int position) {
                showToast("点击了");
                index = position;
                Intent intent4 = new Intent(IdentityActivity.this, CarTypeActivity.class);
                startActivityForResult(intent4, CARTYPE);
                for (int i = 0; i < carList.size(); i++) {
                    if(i==position){
                        CarInfo carInfo1 = carList.get(position);
                        carInfo1.setCartypeid(carTypeId+"");
                    }
                }
            }
        });

    }

    @OnClick({R.id.tv_add_car_num,R.id.rl_native, R.id.rl_positon, R.id.rl_officeid, R.id.rl_department_num, /*R.id.rl_car_type,*/ R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add_car_num:
                carList.add(new CarInfo("","",""));
                carAdapter.setData(carList);

                break;
            case R.id.rl_native:
                Intent intent = new Intent(this, NativesActivity.class);
                startActivityForResult(intent, NATIVE);
                break;
            case R.id.rl_positon:
                Intent intent1 = new Intent(this, PositionActivity.class);
                startActivityForResult(intent1, REQUESTPOSTIONCODE);
                break;
            case R.id.rl_officeid:
                Intent intent2 = new Intent(this, OfficeidActivity.class);
                startActivityForResult(intent2, OFFICEID);
                break;
            case R.id.rl_department_num:
                Intent intent3 = new Intent(this, DepartmentActivity.class);
                startActivityForResult(intent3, DEPARTMENT);
                break;
            case R.id.tv_commit:
                name = tvName.getText().toString().trim();
                phone = tvPhone.getText().toString().trim();
                identityNum = tvIdentityNum.getText().toString().trim();
                roomNum = tvRoomNum.getText().toString().trim();
                officePhone = tvOfficePhone.getText().toString().trim();
                String buildNum = etBuildNum.getText().toString().trim();

                boolean mobilePhoneVerify = VerifyCheck.isMobilePhoneVerify(phone);
                if (!mobilePhoneVerify) {
                    showToast("请输入正确的手机号");
                    return;
                }
                boolean idCardVerify = VerifyCheck.isIDCardVerify(identityNum);
                if (!idCardVerify) {
                    showToast("请核对身份证号");
                    return;
                }
                Log.e("info", name + "==" + identityNum + "==" + positionId + "==" + roomNum + "==" + officePhone + "==" + carType + "=="
                        + preCarCode + "==" + chePaiHao);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(identityNum) || TextUtils.isEmpty(positionId + "")
                        || TextUtils.isEmpty(roomNum) || TextUtils.isEmpty(officePhone) || TextUtils.isEmpty(buildNum)) {
                    showToast("请将信息填写完整");
                    return;
                } else if (!isPhoneChecked(phone)) {
                    return;
                } else {//, natives
                    Auth auth = new Auth(officeid, departId+"", buildNum, roomNum, name, phone, identityNum, positionId + "", officePhone);
                    //List<CarInfo> carList = new ArrayList();
                    //carList.add(new CarInfo(carType, preCarCode, chePaiHao));
                    getPresenter().getData(auth, carList);
                    break;
                }

        }
    }

    //认证成功
    @Override
    public void authSucc() {
        SharedPrefHelper.getInstance().setRealName(name);
        SharedPrefHelper.getInstance().setPhoneNumber(phone);
        Intent intent = new Intent(this, AuthSuccessActivity.class);
        startActivity(intent);
        finish();
    }

    //电话检测
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUESTPOSTIONCODE:
                    if (data != null) {
                        Log.e("positionId", "职位类型id:" + positionId);
                        positionId = data.getIntExtra("positionId", 0);
                        String position = data.getStringExtra("position");
                        tvPosition.setText(position);
                    }
                    break;
                case NATIVE:
                    if (data != null) {
                        nationId = data.getIntExtra("id", 0);
                        String nation = data.getStringExtra("nation");
                        tvNative.setText(nation);
                    }
                    break;
                case DEPARTMENT:
                    if (data != null) {
                        departId = data.getIntExtra("id", 0);
                        String depart = data.getStringExtra("depart");
                        tvDepartmentNum.setText(depart);
                    }
                    break;
                case OFFICEID:
                    if (data != null) {
                        int bgypright = data.getIntExtra("bgypright", 0);
                        int departid = data.getIntExtra("departid", 0);
                         officeid = data.getIntExtra("id", 0)+"";
                        String officeName = data.getStringExtra("name");
                        tvOfficeid.setText(officeName);
                    }
                    break;
                case CARTYPE:
                    if (data != null) {
                        carTypeId = data.getIntExtra("id", 0);
                        String typename = data.getStringExtra("typename");
                        carAdapter.setTypeName(typename,index);
                    }
                    break;
            }
        }
    }
}
