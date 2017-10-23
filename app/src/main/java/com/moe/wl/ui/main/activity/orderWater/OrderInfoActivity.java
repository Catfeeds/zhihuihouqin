package com.moe.wl.ui.main.activity.orderWater;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.ordering.AddressManagerActivity;
import com.moe.wl.ui.main.bean.GenerateOrderWaterBean;
import com.moe.wl.ui.main.bean.OrderWaterTimeBean;
import com.moe.wl.ui.main.bean.QueryWaterListBean;
import com.moe.wl.ui.main.model.OrderInfoModel;
import com.moe.wl.ui.main.modelimpl.OrderInfoModelImpl;
import com.moe.wl.ui.main.presenter.OrderInfoPresenter;
import com.moe.wl.ui.main.view.OrderInfoView;
import com.moe.wl.ui.mywidget.OrderWaterSelectTimePop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderInfoActivity extends BaseActivity<OrderInfoModel, OrderInfoView, OrderInfoPresenter> implements OrderInfoView {

    private static final int REQUEST_ADDRESS = 1001;
    @BindView(R.id.all_sp_comment_title)
    TitleBar title;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_arrive_time)
    LinearLayout llArriveTime;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.activity_order_info)
    LinearLayout activityOrderInfo;

    private List<QueryWaterListBean.PageBean.ListBean> list;
    private int timeId;
    private String realName;
    private String phone;
    private String address;
    private int id;
    private String json;
    private Object[] arr;
    private String mTime;

    @Override
    public OrderInfoPresenter createPresenter() {
        return new OrderInfoPresenter();
    }

    @Override
    public OrderInfoModel createModel() {
        return new OrderInfoModelImpl();
    }

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
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

        if ("".equals(SharedPrefHelper.getInstance().getRealName()) || SharedPrefHelper.getInstance().getRealName() == null) {
            realName = SharedPrefHelper.getInstance().getNickname();
        } else {
            realName = SharedPrefHelper.getInstance().getRealName();
        }
        phone = SharedPrefHelper.getInstance().getPhoneNumber();
        userName.setText(realName);
        phoneNumber.setText(phone);
        phoneNumber.setSelection(phone.length());
        initTitle();
    }

    private void initTitle() {
        title.setBack(true);
        title.setTitle("预定信息");
    }

    @OnClick({R.id.ll_address, R.id.ll_arrive_time, R.id.confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_address:
                Intent intent = new Intent(this, AddressManagerActivity.class);
                startActivityForResult(intent, REQUEST_ADDRESS);
                break;
            case R.id.ll_arrive_time:
                getPresenter().getOrderTime();
                break;
            case R.id.confirm:
                String time = tvTime.getText().toString().trim();
                String remark = etRemark.getText().toString().trim();
                if (TextUtils.isEmpty(address)) {
                    showToast("请选择送货地址");
                    return;
                }
                if (TextUtils.isEmpty(time)) {
                    showToast("请选择期望送水的时间");
                    return;
                }
                //生成订单
                getPresenter().generateOrder(realName, phone, id, time, arr, remark);
                break;
        }
    }

    @Override
    public void getTimeSucc(OrderWaterTimeBean bean) {
        OrderWaterSelectTimePop pop = new OrderWaterSelectTimePop(this, bean, new OrderWaterSelectTimePop.OnSelectClick() {
            @Override
            public void onClick(int id, String time, boolean isAm) {
                timeId = id;
                mTime = time;
                tvTime.setText(time);
            }
        });
        pop.showAtLocation(findViewById(R.id.activity_order_info), Gravity.CENTER, 0, 0);
    }

    @Override
    public void generateOrderSucc(GenerateOrderWaterBean bean) {
        //生成订单成功
        if (bean != null) {
            Intent intent = new Intent(this, ConfirmOrderActivity.class);
            String ordercode = bean.getOrdercode();
            String ordertype = bean.getOrdertype() + "";
            intent.putExtra("ordercode", ordercode);
            intent.putExtra("ordertype", ordertype);
            intent.putExtra("json", json);
            intent.putExtra("address", address);
            String phone = phoneNumber.getText().toString().trim();
            intent.putExtra("phone", phone);
            intent.putExtra("time", mTime);
            startActivity(intent);
        } else {
            LogUtils.i("返回的bean为null");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_ADDRESS) {
                if (data != null) {
                    address = data.getStringExtra("Address");
                    id = data.getIntExtra("ID", 0);
                    tvAddress.setText(address);
                }
            }
        }
    }
}
