package com.moe.wl.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.framework.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.moe.wl.R;

public class BianJiAddressActivity extends AppCompatActivity {

    @BindView(R.id.delivery_address_title)
    TitleBar titleBar;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_telphone)
    EditText etTelphone;
    @BindView(R.id.et_service_address)
    EditText etServiceAddress;
    @BindView(R.id.tv_save_address)
    TextView tvSaveAddress;
    @BindView(R.id.activity_bian_ji_address)
    RelativeLayout activityBianJiAddress;
    private String address;
    private String telPhone;
    private String contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bian_ji_address);
        ButterKnife.bind(this);
        initTitle();
        contact = etContact.getText().toString().trim();
        telPhone = etTelphone.getText().toString().trim();
        address = etServiceAddress.getText().toString().trim();
    }

    private void initTitle() {
        titleBar.setTitle("编辑地址");
        titleBar.setBack(true);
    }

    @OnClick({R.id.tv_delete, R.id.tv_save_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_delete:
                etContact.setText("");
                etTelphone.setText("");
                etServiceAddress.setText("");
                break;
            case R.id.tv_save_address:
                Intent data = new Intent();
                data.putExtra("contact",contact);
                data.putExtra("telPhone",telPhone);
                data.putExtra("address",address);
                setResult(RESULT_OK,data);
                finish();
                break;
        }
    }
}
