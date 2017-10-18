package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.model.OrderingModel;
import com.moe.wl.ui.main.modelimpl.OrderingModelImpl;
import com.moe.wl.ui.main.presenter.OrderingPresenter;
import com.moe.wl.ui.main.view.OrderingView;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/16 0016
 */
public class FixedOrderActivity extends BaseActivity<OrderingModel, OrderingView, OrderingPresenter> implements OrderingView {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.price_number)
    TextView price_number;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.minus)
    ImageView minus;
    @BindView(R.id.sarah_number)
    TextView sarah_number;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.phone_number)
    EditText et_phone;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_arrive_time)
    LinearLayout llArriveTime;
    @BindView(R.id.lunch)
    CheckBox lunch;
    @BindView(R.id.dinner)
    CheckBox dinner;
    @BindView(R.id.one_week)
    RadioButton oneWeek;
    @BindView(R.id.two_week)
    RadioButton twoWeek;
    @BindView(R.id.one_mouth)
    RadioButton oneMouth;
    @BindView(R.id.main)
    RelativeLayout main;

    private String userName;
    private String phoneNumber;

    private int sarahNumber = 0; // 份数
    private int price = 30; // 每份价格

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_order_fixation);
    }

    @Override
    public void initView() {
        titleBar.setTitle("固定餐预订");
        titleBar.setBack(true);

        userName = SharedPrefHelper.getInstance().getRealName();
        user_name.setText(userName);
        phoneNumber = SharedPrefHelper.getInstance().getPhoneNumber();
        et_phone.setText(phoneNumber);
        et_phone.setSelection(phoneNumber.length());

        oneWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    twoWeek.setChecked(false);
                    oneMouth.setChecked(false);
                }
            }
        });

        twoWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    oneWeek.setChecked(false);
                    oneMouth.setChecked(false);
                }
            }
        });

        oneMouth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    oneWeek.setChecked(false);
                    twoWeek.setChecked(false);
                }
            }
        });
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
                sarah_number.setText(sarahNumber + "");
                price_number.setText("¥" + sarahNumber * price);
                break;
            case R.id.add:
                sarahNumber++;
                minus.setVisibility(View.VISIBLE);
                sarah_number.setVisibility(View.VISIBLE);
                sarah_number.setText(sarahNumber + "");
                price_number.setText("¥" + sarahNumber * price);
                break;
            case R.id.send:
                getData();
                break;
        }
    }

    // 点击提交实现逻辑
    private void getData() {
        if (sarahNumber == 0) {
            ToastUtil.showToast(this, "工作餐份数为0");
            return;
        }
        if (!OtherUtils.phoneNumber(et_phone.getText().toString().trim())) {
            ToastUtil.showToast(this, "手机号信息不正确");
            return;
        }
        phoneNumber = et_phone.getText().toString().trim();
        String fixedmealtype = "";
        if (lunch.isChecked() && dinner.isChecked()) {
            fixedmealtype = "1,2";
        } else if (lunch.isChecked()) {
            fixedmealtype = "1";
        } else if (dinner.isChecked()) {
            fixedmealtype = "2";
        } else {
            ToastUtil.showToast(this, "请选择订餐类型");
            return;
        }
        int duration = 0;
        if (oneWeek.isChecked()) {
            duration = 1;
        } else if (twoWeek.isChecked()) {
            duration = 2;
        } else if (oneMouth.isChecked()) {
            duration = 3;
        }

        getPresenter().getData(phoneNumber, sarahNumber, 2, fixedmealtype, duration);
    }

    @Override
    public void createOrderingSucc(CollectBean bean) {
        startActivity(new Intent(FixedOrderActivity.this, OrderingSuccessActivity.class));
        finish();
    }

    @Override
    public void getTime(SelectTimeBean bean) {

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
