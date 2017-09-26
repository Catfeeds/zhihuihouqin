package cn.lc.model.ui.login.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.base.BaseActivity;
import cn.lc.model.ui.login.model.AuthModel;
import cn.lc.model.ui.login.modelimpl.AuthModelImpl;
import cn.lc.model.ui.login.presenter.AuthPresenter;
import cn.lc.model.ui.login.view.AuthView;
import mvp.cn.util.StringUtil;
import mvp.cn.util.VerifyCheck;

public class IdentityActivity extends BaseActivity<AuthModel, AuthView, AuthPresenter> implements AuthView {

    private static final int REQUESTPOSTIONCODE = 10;
    @BindView(R.id.tv_name)
    EditText etName;
    @BindView(R.id.tv_phone)
    EditText etPhone;
    @BindView(R.id.tv_identity_num)
    EditText etIdentityNum;
    @BindView(R.id.iv_position)
    ImageView ivPosition;
    @BindView(R.id.rl_positon)
    RelativeLayout rlPositon;
    @BindView(R.id.tv_room_num)
    EditText etRoomNum;
    @BindView(R.id.tv_office_phone)
    EditText etOfficePhone;
    @BindView(R.id.tv_car_type)
    EditText etCarType;
    @BindView(R.id.et_chepaihao)
    EditText etChepaihao;
    @BindView(R.id.tv_add_car_num)
    TextView tvAddCarNum;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.activity_identity)
    RelativeLayout activityIdentity;
    @BindView(R.id.tv_shengfen)
    TextView tvShengfen;
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

    }

    @OnClick({R.id.rl_positon, R.id.tv_add_car_num, R.id.tv_shengfen, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_positon:
                Intent intent = new Intent(this, PositionActivity.class);
                startActivityForResult(intent, REQUESTPOSTIONCODE);
                break;
            case R.id.tv_add_car_num:
                break;
            case R.id.tv_shengfen:
                showShengFenDialog();
                break;
            case R.id.tv_commit:
                name = etName.getText().toString().trim();
                phone = etPhone.getText().toString().trim();
                identityNum = etIdentityNum.getText().toString().trim();
                roomNum = etRoomNum.getText().toString().trim();
                officePhone = etOfficePhone.getText().toString().trim();
                carType = etCarType.getText().toString().trim();
                chePaiHao = etChepaihao.getText().toString().trim();
                preCarCode = tvShengfen.getText().toString().trim();
                Log.e("info",name+"=="+identityNum+"=="+positionId+"=="+roomNum+"=="+officePhone+"=="+carType+"=="
                +preCarCode+"=="+chePaiHao);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(identityNum) || TextUtils.isEmpty(positionId + "")
                        || TextUtils.isEmpty(roomNum) || TextUtils.isEmpty(officePhone) || TextUtils.isEmpty(carType) ||
                        TextUtils.isEmpty(preCarCode) || TextUtils.isEmpty(chePaiHao)) {
                    showToast("请将信息填写完整");
                    return;
                } else if (!isPhoneChecked(phone)) {
                    return;
                } else {
                    getPresenter().getData(name, phone, identityNum, positionId, roomNum,
                            officePhone, carType, preCarCode, chePaiHao);
                    break;
                }
        }
    }

    //认证成功
    @Override
    public void authSucc() {
        Intent intent = new Intent(this, AuthSuccessActivity.class);
        startActivity(intent);
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
        ArrayAdapter<String> adapter = null;
        if (isFirst) {
            str = "";
            tvShengfen.setText(str);
            adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, R.id.tv_item, lists);
        } else {
            adapter = new ArrayAdapter<String>(this, R.layout.simple_list_item_1, R.id.tv_item, listTwo);

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
                }
                dlg.cancel();
                tvShengfen.setText(str);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                Log.e("positionId", "职位类型id:" + positionId);
                positionId = data.getIntExtra("positionId", 0);
            }
        }
    }


}
