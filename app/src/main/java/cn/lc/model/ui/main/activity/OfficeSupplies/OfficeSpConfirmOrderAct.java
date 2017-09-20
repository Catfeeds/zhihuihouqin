package cn.lc.model.ui.main.activity.OfficeSupplies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.lc.model.R;
import cn.lc.model.framework.widget.TitleBar;

public class OfficeSpConfirmOrderAct extends AppCompatActivity {

    @BindView(R.id.title)
    TitleBar title;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;
    @BindView(R.id.iv_address_logo)
    ImageView ivAddressLogo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.iv_clock)
    ImageView ivClock;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_expect_time)
    RelativeLayout rlExpectTime;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.ll_write_other)
    LinearLayout llWriteOther;
    @BindView(R.id.tv_shop_amout)
    TextView tvShopAmout;
    @BindView(R.id.tv_how_much)
    TextView tvHowMuch;
    @BindView(R.id.tv_now_pay)
    TextView tvNowPay;
    @BindView(R.id.activity_office_sp_confirm_order)
    LinearLayout activityOfficeSpConfirmOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office_sp_confirm_order);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ll_address, R.id.rl_expect_time, R.id.ll_write_other, R.id.tv_how_much, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                break;
            case R.id.rl_expect_time:
                break;
            case R.id.ll_write_other:
                break;
            case R.id.tv_how_much:
                break;
            case R.id.tv_now_pay:
                Intent intent = new Intent(this,OfficeSpPayAct.class);
                startActivity(intent);
                break;
        }
    }
}
