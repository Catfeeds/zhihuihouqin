package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.OfficeSupplies.OrderRemarkActivity;
import com.moe.wl.ui.main.activity.OfficeSupplies.SpPayActivity;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.ordering.AddressManagerActivity;
import com.moe.wl.ui.main.adapter.ComfirmOrderWaterAdapter;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.mywidget.BottomTimeDialog;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;
import com.moe.wl.ui.mywidget.OrderWaterPayDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmOrderActivity extends AppCompatActivity {

    private static final int ADDRESSREQUESTCODE = 10;
    private static final int REMARKREUQESTCODE = 20;
    @BindView(R.id.title)
    TitleBar titleBar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_sex)
    TextView tvSex;
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
    @BindView(R.id.rv_order_item)
    NoSlideRecyclerView rvOrderItem;
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
    private List<QueryWaterListBean.PageBean.ListBean> list;
    private ComfirmOrderWaterAdapter spOrderAdapter;
    private String address;
    private String remark;
    private int sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp_confirm_order);
        ButterKnife.bind(this);
        initTitle();
        Intent intent = getIntent();
        String address = intent.getStringExtra("address");
        String time = intent.getStringExtra("time");
        tvAddress.setText(address);
        tvTime.setText(time);
        String json = intent.getStringExtra("json");
        Gson gson = new Gson();
        list = gson.fromJson(json,
                new TypeToken<List<QueryWaterListBean.PageBean.ListBean>>() {
                }.getType());
        initData();
        initList();
    }

    private void initList() {
        rvOrderItem.setLayoutManager(new NoScrollLinearLayoutManager(this));
        spOrderAdapter = new ComfirmOrderWaterAdapter(this, list);
        rvOrderItem.setAdapter(spOrderAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确定订单");
    }

    private void initData() {
        tvUsername.setText(SharedPrefHelper.getInstance().getRealName());
        tvPhone.setText(SharedPrefHelper.getInstance().getPhoneNumber());
        String sex = SharedPrefHelper.getInstance().getSex();
        if ("男".equals(sex)) {
            tvSex.setText("先生");
        } else if ("女".equals(sex)) {
            tvSex.setText("女士");
        }
        sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int count = list.get(i).getCount();
            int price = list.get(i).getPrice();
            sum += count * price;
        }
        tvShopAmout.setText("￥" + sum);
        tvHowMuch.setText("实际付款￥" + sum);

    }

    @OnClick({R.id.ll_address, R.id.rl_expect_time, R.id.ll_write_other, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                startActivityForResult(intent1, ADDRESSREQUESTCODE);
                break;
            case R.id.rl_expect_time:
                showPop();
                break;
            case R.id.ll_write_other:
                Intent intent = new Intent(this, OrderRemarkActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_now_pay:
                showPayDialog();

                break;
        }
    }

    private void showPayDialog() {
        OrderWaterPayDialog payDialog = new OrderWaterPayDialog(this, R.style.dialog_style) ;
            payDialog.show();
            payDialog.setListener(new OrderWaterPayDialog.OnConfirmClickListener() {
                @Override
                public void onConfirmClickListener(boolean isPersonal) {
                    if (isPersonal) {//个人支付
                        Intent intent = new Intent(ConfirmOrderActivity.this, PayFiveJiaoActivity.class);
                        intent.putExtra("pay", sum);
                        startActivity(intent);

                    } else {//对公支付
                        Intent intent2 = new Intent(ConfirmOrderActivity.this, SpPayActivity.class);
                        intent2.putExtra("money", sum + "");
                        startActivity(intent2);
                    }
                }
            });

    }
    private void showPop() {
        BottomTimeDialog dialog = new BottomTimeDialog(this, R.style.dialog_style);
        dialog.show();
        dialog.setListener2(new BottomTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int n_year, int n_month, int n_day, int n_min, int n_sec) {
                LogUtils.i(n_year + " " + n_month + " " + n_day + " " + n_min + " " + n_sec);
                tvTime.setText(n_year + "-" + n_month + "-" + n_day + " " + n_min + ":" + n_sec);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADDRESSREQUESTCODE) {
                if (data != null) {
                    address = data.getStringExtra("Address");
                    tvAddress.setText("送至：" + address);
                }
            }
        } else if (resultCode == Constants.REMARK) {
            if (requestCode == REMARKREUQESTCODE) {
                if (data != null) {
                    remark = data.getStringExtra("remark");
                }
            }
        }
    }
}
