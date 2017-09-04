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

public class SpConfirmOrderActivity extends AppCompatActivity {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tv_send_to_address)
    TextView tvSendToAddress;
    @BindView(R.id.rl_user_detail_info)
    RelativeLayout rlUserDetailInfo;
    @BindView(R.id.iv_time)
    ImageView ivTime;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.iv_sp_pic)
    ImageView ivSpPic;
    @BindView(R.id.tv_sp_category)
    TextView tvSpCategory;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_write_other)
    TextView tvWriteOther;
    @BindView(R.id.tv_sp_money)
    TextView tvSpMoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_now_pay)
    TextView tvNowPay;
    @BindView(R.id.activity_sp_confirm_order)
    RelativeLayout activitySpConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_confirm_order);
        ButterKnife.bind(this);
        initTitle();
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确定订单");
    }

    @OnClick({R.id.rl_user_detail_info, R.id.tv_write_other, R.id.tv_pay, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_user_detail_info:
                Intent intent1 = new Intent(this, DeliveryAddressActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_write_other:
                Intent intent = new Intent(this, OrderRemarkActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_pay:
                break;
            case R.id.tv_now_pay:
                Intent intent2 = new Intent(this, SpPayActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
