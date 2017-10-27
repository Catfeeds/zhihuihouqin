package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.model.OrderingModel;
import com.moe.wl.ui.main.modelimpl.OrderingModelImpl;
import com.moe.wl.ui.main.presenter.OrderingPresenter;
import com.moe.wl.ui.main.view.OrderingView;
import com.moe.wl.ui.mywidget.SelectTimePop;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/4 0004
 */

public class OrderingActivity extends BaseActivity<OrderingModel, OrderingView, OrderingPresenter> implements OrderingView {

    private static final int REQUEST_ADDRESS = 101;
//    private static final int REQUEST_TIME = 102;

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.minus)
    ImageView minus;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.sarah_number)
    TextView sarah_number;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.phone_number)
    EditText phone_number;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    @BindView(R.id.ll_arrive_time)
    LinearLayout ll_arrive_time;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.price_number)
    TextView price_number;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_name)
    TextView tv_name;
//    @BindView(R.id.send)
//    Button send;

    private int sarahNumber = 0; // 份数
    private int price = 30; // 每份价格

    private int addressId = 0; // 地址ID
    private String addressName; // 地址名
    private int timeId = 0; // 时间ID

    private String userName;
    private SelectTimePop pop;
    private String phoneNumber;
//    private SelectTimeBean timeBean;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_ordering);
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        titleBar.setBack(true);
        titleBar.setTitle("工作餐预订");
        titleBar.setTitleRight("固定餐");
        titleBar.setOnRightclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderingActivity.this, FixedOrderActivity.class));
            }
        });
        userName = SharedPrefHelper.getInstance().getRealName();
        user_name.setText(userName);
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        phone_number.setText(phoneNumber);
        phone_number.setSelection(phoneNumber.length());
    }

    @Override
    public void createOrderingSucc(CollectBean bean) {
        startActivity(new Intent(OrderingActivity.this, OrderingSuccessActivity.class));
        finish();
    }

    @Override
    public void getTime(SelectTimeBean bean) {
        pop = new SelectTimePop(OrderingActivity.this, bean, new SelectTimePop.OnSelectClick() {
            @Override
            public void onClick(int id, String time, boolean isAm) {
                timeId = id;
                tv_time.setText(time);
            }
        });
        pop.showAtLocation(findViewById(R.id.main), Gravity.CENTER, 0, 0);
    }

    @OnClick({R.id.minus, R.id.add, R.id.ll_address, R.id.ll_arrive_time, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.minus:
                sarahNumber--;
                if (sarahNumber == 0) {
                    minus.setVisibility(View.GONE);
                    sarah_number.setVisibility(View.GONE);
                }
                number.setText("共" + sarahNumber + "份");
                sarah_number.setText(sarahNumber + "");
                price_number.setText("¥" + sarahNumber * price);
                break;
            case R.id.add:
                sarahNumber++;
                minus.setVisibility(View.VISIBLE);
                sarah_number.setVisibility(View.VISIBLE);
                sarah_number.setText(sarahNumber + "");
                number.setText("共" + sarahNumber + "份");
                price_number.setText("¥" + sarahNumber * price);
                break;
            case R.id.ll_address:
                Intent intent = new Intent(this, AddressManagerActivity.class);
                intent.putExtra("ID", addressId);
                intent.putExtra("addressName", addressName);
                startActivityForResult(intent, REQUEST_ADDRESS);
                break;
            case R.id.ll_arrive_time:
                // TODO 跳转选择时间
                getPresenter().getTime();
                break;
            case R.id.send:
                getData();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {

        } else {
            switch (requestCode) {
                case REQUEST_ADDRESS:
                    addressId = data.getIntExtra("ID", 0);
                    addressName = data.getStringExtra("Address");
                    tv_name.setText(addressName);
                    break;
            }
        }
    }

    // 点击提交实现逻辑
    private void getData() {
        if (sarahNumber == 0) {
            ToastUtil.showToast(this, "工作餐份数为0");
            return;
        }
        if (!VerifyCheck.isMobilePhoneVerify(phone_number.getText().toString().trim())) {
            ToastUtil.showToast(this, "手机号信息不正确");
            return;
        }
        phoneNumber = phone_number.getText().toString().trim();
       /* if (addressId == 0) {
            ToastUtil.showToast(this, "请选择送货地址");
            return;
        }
        if (timeId == 0) {
            ToastUtil.showToast(this, "请选择送达时间");
            return;
        }*/
        getPresenter().getData(phoneNumber, sarahNumber, 1, "", 0);
    }

    @Override
    public OrderingModel createModel() {
        return new OrderingModelImpl();
    }

    @Override
    public OrderingPresenter createPresenter() {
        return new OrderingPresenter();
    }
}
