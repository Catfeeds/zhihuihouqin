package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class DeliveryAddressActivity extends AppCompatActivity {

    private static final int BIANJIADDRESS = 10;
    @BindView(R.id.delivery_address_title)
    TitleBar titleBar;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.bianji)
    ImageView bianji;
    @BindView(R.id.new_build_address)
    TextView newBuildAddress;
    @BindView(R.id.activity_delivery_address)
    RelativeLayout activityDeliveryAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("送货地址");
    }

    @OnClick({R.id.iv_select, R.id.bianji, R.id.new_build_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_select:
                break;
            case R.id.bianji:
                Intent intent = new Intent(this, BianJiAddressActivity.class);
                startActivityForResult(intent,BIANJIADDRESS);
                break;
            case R.id.new_build_address:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
