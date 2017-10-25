package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.base.MessageEvent;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.OfficeSupplies.RemarkActivity;
import com.moe.wl.ui.main.activity.OfficeSupplies.SpPayActivity;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.activity.SubmitSuccessActivity;
import com.moe.wl.ui.main.activity.ordering.AddressManagerActivity;
import com.moe.wl.ui.main.adapter.ComfirmOrderWaterAdapter;
import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.ActivityPostBean;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.main.bean.UserDepositBean;
import com.moe.wl.ui.main.bean.WalletOrderBean;
import com.moe.wl.ui.main.model.MyDepositModel;
import com.moe.wl.ui.main.modelimpl.MyDepositModelImpl;
import com.moe.wl.ui.main.presenter.MyDepositPresenter;
import com.moe.wl.ui.main.view.MyDepositView;
import com.moe.wl.ui.mywidget.AlertDialog;
import com.moe.wl.ui.mywidget.NoScrollLinearLayoutManager;
import com.moe.wl.ui.mywidget.NoSlideRecyclerView;
import com.moe.wl.ui.mywidget.OrderWaterPayDialog;
import com.moe.wl.ui.mywidget.OrderWaterSelectTimePop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

public class ConfirmOrderActivity extends BaseActivity<MyDepositModel, MyDepositView, MyDepositPresenter> implements MyDepositView {

    private static final int ADDRESSREQUESTCODE = 10;
    private static final int REMARKREUQESTCODE = 20;
    @BindView(R.id.title)
    TitleBar titleBar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_user_info)
    RelativeLayout llUserInfo;
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
    @BindView(R.id.tv_other_need)
    TextView tvOtherNeed;
    @BindView(R.id.ll_yajin)
    LinearLayout llYajin;
    @BindView(R.id.tv_ya_jin)
    TextView tvYaJIn;
    @BindView(R.id.choose_address)
    TextView choose_address;
    @BindView(R.id.activity_office_sp_confirm_order)
    LinearLayout activityOfficeSpConfirmOrder;
    private List<QueryWaterListBean.PageBean.ListBean> list;
    private ComfirmOrderWaterAdapter spOrderAdapter;

    private String address;
    private int addressID;
    private String userName;
    private int timeID;
    private String phone;
    private String remark = "";
    private double sum;
    private int deposit;
    private String json;
    private Object[] arr;

    @Override
    public MyDepositPresenter createPresenter() {
        return new MyDepositPresenter();
    }

    @Override
    public MyDepositModel createModel() {
        return new MyDepositModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_sp_confirm_order);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        initTitle();
        //检测是否交押金
        getPresenter().getDepositInfo();
        Intent intent = getIntent();
        // 处理传递过来的衣服数据
        json = intent.getStringExtra("json");
        Gson gson = new Gson();
        list = gson.fromJson(json,
                new TypeToken<List<QueryWaterListBean.PageBean.ListBean>>() {
                }.getType());
        arr = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            QueryWaterListBean.PageBean.ListBean listBean = list.get(i);
            int id = listBean.getId();
            int count = listBean.getCount();
            Map<String, Integer> map = new HashMap<>();
            map.put("goodsid", id);
            map.put("count", count);
            arr[i] = map;
        }
        // 设置数据
        sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int count = list.get(i).getCount();
            int price = list.get(i).getPrice();
            sum += count * price;
        }
        tvShopAmout.setText("￥" + sum);
        tvHowMuch.setText("实际付款￥" + sum);
        // 初始化 列表
        rvOrderItem.setLayoutManager(new NoScrollLinearLayoutManager(this));
        spOrderAdapter = new ComfirmOrderWaterAdapter(this, list);
        rvOrderItem.setAdapter(spOrderAdapter);
    }

    private void initTitle() {
        titleBar.setBack(true);
        titleBar.setTitle("确定订单");
    }

    @OnClick({R.id.ll_address, R.id.rl_expect_time, R.id.ll_write_other, R.id.tv_now_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                intent1.putExtra("ID", addressID);
                intent1.putExtra("addressName", address);
                intent1.putExtra("Name", userName);
                startActivityForResult(intent1, ADDRESSREQUESTCODE);
                break;
            case R.id.rl_expect_time:
                getPresenter().getOrderTime();
                break;
            case R.id.ll_write_other:
                Intent intent = new Intent(this, RemarkActivity.class);
                intent.putExtra("remark", remark);
                startActivityForResult(intent, REMARKREUQESTCODE);
                break;
            case R.id.tv_now_pay:
                if (deposit <= 0) { //没有交押金,
                    showIsHasYajin();
                } else {
                    getData();
                }
                break;
        }
    }

    // 提交订单
    private void getData() {
        if (addressID == 0) {
            ToastUtil.showToast(this, "请选择收货地址");
            return;
        }
        if (timeID == 0) {
            ToastUtil.showToast(this, "请选择送货时间");
            return;
        }
        getPresenter().generateOrder(userName, phone, addressID, timeID, arr, remark);
    }

    private void showPayDialog(final GenerateOrderWaterBean bean) {
        OrderWaterPayDialog payDialog = new OrderWaterPayDialog(this, R.style.dialog_style);
        payDialog.show();
        payDialog.setListener(new OrderWaterPayDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(boolean isPersonal) {
                if (isPersonal) {//个人支付
                    Intent intent = new Intent(ConfirmOrderActivity.this, PayFiveJiaoActivity.class);
                    intent.putExtra("pay", sum);
                    intent.putExtra("from", Constants.ORDERWATER);
                    intent.putExtra("orderid", bean.getOrderid());
                    intent.putExtra("ordercode", bean.getOrdercode());
                    intent.putExtra("ordertype", bean.getOrdertype());
                    startActivity(intent);
                } else {//对公支付
                    Intent intent2 = new Intent(ConfirmOrderActivity.this, SpPayActivity.class);
                    intent2.putExtra("money", sum + "");
                    startActivity(intent2);
                }
            }
        });
    }

    // 缴纳押金弹窗
    private void showIsHasYajin() {
        AlertDialog builder = new AlertDialog(this).builder();
        builder.setTitle("温馨提示")
                .setMsg("您好，您订的桶装水首次下单需缴纳人民币" + 50 + "元押金,押金可在最后申请退还，给您带来的不便请谅解")
                .setPositiveButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setNegativeButton("去缴纳", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ConfirmOrderActivity.this, PayDepositActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == ADDRESSREQUESTCODE) {
                if (data != null) {
                    choose_address.setVisibility(View.GONE);
                    llUserInfo.setVisibility(View.VISIBLE);
                    address = data.getStringExtra("Address");
                    addressID = data.getIntExtra("ID", 0);
                    userName = data.getStringExtra("Name");
                    phone = data.getStringExtra("Mobile");
                    tvAddress.setText("送至：" + address);
                    tvUsername.setText(userName);
                    tvPhone.setText(phone);
                }
            }
        } else if (resultCode == Constants.REMARK) {
            if (requestCode == REMARKREUQESTCODE) {
                if (data != null) {
                    remark = data.getStringExtra("remark");
                    tvOtherNeed.setText(remark);
                }
            }
        }
    }

    @Override
    public void getUserDepositResult(UserDepositBean bean) {
        if (bean != null) {
            deposit = bean.getDeposit().getDeposit();
        }
    }

    @Override
    public void generateOrderSucc(GenerateOrderWaterBean bean) {
        showPayDialog(bean);
    }

    @Override
    public void getOrderResult(WalletOrderBean bean) {

    }

    @Override
    public void getTimeSucc(OrderWaterTimeBean bean) {
        OrderWaterSelectTimePop pop = new OrderWaterSelectTimePop(this, bean, new OrderWaterSelectTimePop.OnSelectClick() {
            @Override
            public void onClick(int id, String time, boolean isAm) {
                timeID = id;
//                mTime = time;
                tvTime.setText(time);
            }
        });
        pop.showAtLocation(findViewById(R.id.activity_office_sp_confirm_order), Gravity.CENTER, 0, 0);
    }

    @Override
    public void backDepositResult(ActivityPostBean bean) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(MessageEvent event) {
        if (event != null) {
            if (event != null && event.getCode() == MessageEvent.WECHAT_PAY) {
                LogUtils.d("--------------支付订单----------");
                showToast("支付订单");
                Intent intent = new Intent(this, SubmitSuccessActivity.class);
                intent.putExtra("isPay", true);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
