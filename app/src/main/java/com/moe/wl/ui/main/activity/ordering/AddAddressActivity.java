package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.model.AddAddressModel;
import com.moe.wl.ui.main.modelimpl.AddAddressModelImpl;
import com.moe.wl.ui.main.presenter.AddAddressPresenter;
import com.moe.wl.ui.main.view.AddAddressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/7 0007
 */

public class AddAddressActivity extends BaseActivity<AddAddressModel, AddAddressView, AddAddressPresenter> implements AddAddressView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_mobile)
    EditText mobile;
    @BindView(R.id.et_address)
    EditText et_address;
    @BindView(R.id.btn_add)
    Button add;
    @BindView(R.id.delete)
    TextView delete;

    private int id;
    private String userName;
    private String phoneNumber;
    private String address;

    private int type; // 0 编辑地址  1 新建地址


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        type = getIntent().getIntExtra("Type", 1);
        if (type == 0) {
            // 编辑地址
            titleBar.setTitle("编辑地址");
            id = getIntent().getIntExtra("Id", 0);
            userName = getIntent().getStringExtra("Name");
            phoneNumber = getIntent().getStringExtra("Mobile");
            address = getIntent().getStringExtra("Address");
            delete.setVisibility(View.VISIBLE);
            et_name.setText(userName);
            mobile.setText(phoneNumber);
            et_address.setText(address);
        } else {
            // 添加地址
            titleBar.setTitle("新建地址");
            delete.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.delete, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.delete:
                getPresenter().deleteAddress(new int[]{id});
                break;

            case R.id.btn_add:
                if (type == 0) {
                    // 编辑地址
                    addOrEditAddress(0);
                } else {
                    // 请求添加地址
                    addOrEditAddress(1);
                }
                break;
        }
    }

    /**
     * 添加地址或编辑地址
     *
     * @param type 1添加地址  0编辑地址
     */
    private void addOrEditAddress(int type) {
        if (et_name.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "姓名不能为空！");
            return;
        }

        if (!OtherUtils.phoneNumber(mobile.getText().toString().trim())) {
            ToastUtil.showToast(this, "手机号信息不正确");
            return;
        }

        if (et_address.getText().toString().trim().length() == 0) {
            ToastUtil.showToast(this, "地址不能为空！");
            return;
        }

        if (type == 1)
            getPresenter().addAddress(et_name.getText().toString().trim(), mobile.getText().toString().trim(), et_address.getText().toString().trim());
        else
            getPresenter().editAddress(id, et_name.getText().toString().trim(), mobile.getText().toString().trim(), et_address.getText().toString().trim());

    }

    @Override
    public void addAddressSucc() {
        // 添加地址
        Intent intent = new Intent();
        intent.putExtra("Name", et_name.getText().toString().trim());
        intent.putExtra("Mobile", mobile.getText().toString().trim());
        intent.putExtra("Address", et_address.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void editAddressSucc() {
        // 编辑地址
        Intent intent = new Intent();
        intent.putExtra("ID", id);
        intent.putExtra("Type", 1);
        intent.putExtra("Name", et_name.getText().toString().trim());
        intent.putExtra("Mobile", mobile.getText().toString().trim());
        intent.putExtra("Address", et_address.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void deleteAddressSucc() {
        // 删除地址
        Intent intent = new Intent();
        intent.putExtra("ID", id);
        intent.putExtra("Type", 0);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public AddAddressModel createModel() {
        return new AddAddressModelImpl();
    }

    @Override
    public AddAddressPresenter createPresenter() {
        return new AddAddressPresenter();
    }
}
