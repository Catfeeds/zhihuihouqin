package com.moe.wl.ui.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.login.model.AccountComplainModel;
import com.moe.wl.ui.login.modelimpl.AccountComplianModelImpl;
import com.moe.wl.ui.login.presenter.AccountComplainPresenter;
import com.moe.wl.ui.login.view.AccountComplainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/20 0020
 */
public class AccountComplainActivity extends BaseActivity<AccountComplainModel, AccountComplainView, AccountComplainPresenter> implements AccountComplainView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.id_card)
    EditText idCard;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.reason)
    EditText reason;
    @BindView(R.id.agreed)
    CheckBox agreed;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_account_complain);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("账号申诉");
    }

    @Override
    public void complainSucc() {
        ToastUtil.showToast(this, "提交成功!");
        finish();
    }

    @OnClick({R.id.sure, R.id.ty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure:
                getData();
                break;

            case R.id.ty:
                break;
        }
    }

    public void getData() {
        if (!(isEmpty(account) && isEmpty(newPassword) && isEmpty(userName)
                && isEmpty(idCard) && isEmpty(phoneNumber))) {
            ToastUtil.showToast(this, "请完善信息");
            return;
        }
        if (!VerifyCheck.isMobilePhoneVerify(phoneNumber.getText().toString().trim())) {
            ToastUtil.showToast(this, "请输入正确的手机号");
            return;
        }
        if (!VerifyCheck.isIDCardVerify(idCard.getText().toString().trim())) {
            ToastUtil.showToast(this, "请输入正确的身份证号");
            return;
        }
        if (!agreed.isChecked()) {
            ToastUtil.showToast(this, "请阅读并同意协议");
            return;
        }

        getPresenter().getData(getString(account), getString(newPassword), getString(idCard),
                getString(phoneNumber), getString(newPassword), getString(reason));
    }

    private String getString(EditText et) {
        return et.getText().toString().trim();
    }

    private boolean isEmpty(EditText et) {
        if (et.getText().toString().trim().length() > 0)
            return true;
        return false;
    }

    @Override
    public AccountComplainModel createModel() {
        return new AccountComplianModelImpl();
    }

    @Override
    public AccountComplainPresenter createPresenter() {
        return new AccountComplainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
