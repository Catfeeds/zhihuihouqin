package com.moe.wl.ui.main.activity.ordering;

import android.content.Intent;
import android.text.TextUtils;
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
import com.moe.wl.framework.utils.LogUtils;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.bean.SelectTimeBean;
import com.moe.wl.ui.main.model.OrderingModel;
import com.moe.wl.ui.main.modelimpl.OrderingModelImpl;
import com.moe.wl.ui.main.presenter.OrderingPresenter;
import com.moe.wl.ui.main.view.OrderingView;
import com.moe.wl.ui.mywidget.BottomymdTimeDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import mvp.cn.util.ToastUtil;
import mvp.cn.util.VerifyCheck;

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
    @BindView(R.id.tv_begain)
    TextView tvBegain;
    @BindView(R.id.tv_end)
    TextView tvEnd;


    private String userName;
    private String phoneNumber;

    private int sarahNumber = 0; // 份数
    private int price = 30; // 每份价格
    private long dayNum = 1; // 订餐天数
    private int typeNum = 1;//午餐 晚餐
    private String startTime;
    private String endTime;

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

        lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    boolean checked = dinner.isChecked();
                    if (checked) {
                        typeNum = 2;
                    } else {
                        typeNum = 1;
                    }

                } else {
                    dinner.setChecked(true);
                    typeNum = 1;
                }
                setTotalPrice(sarahNumber, typeNum, dayNum);
            }
        });
        dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    boolean checked = lunch.isChecked();
                    if (checked) {
                        typeNum = 2;
                    } else {
                        typeNum = 1;
                    }

                } else {
                    lunch.setChecked(true);
                    typeNum = 1;
                }
                setTotalPrice(sarahNumber, typeNum, dayNum);
            }
        });

       /* oneWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        });*/
    }

    @OnClick({R.id.minus, R.id.add, R.id.ll_address, R.id.ll_arrive_time, R.id.send, R.id.ll_select_begain, R.id.ll_select_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.minus:
                sarahNumber--;
                if (sarahNumber == 0) {
                    minus.setVisibility(View.GONE);
                    sarah_number.setVisibility(View.GONE);
                }
                sarah_number.setText(sarahNumber + "");
                //price_number.setText("¥" + sarahNumber * price);
                setTotalPrice(sarahNumber, typeNum, dayNum);
                break;
            case R.id.add:
                sarahNumber++;
                minus.setVisibility(View.VISIBLE);
                sarah_number.setVisibility(View.VISIBLE);
                sarah_number.setText(sarahNumber + "");
                //price_number.setText("¥" + sarahNumber * price);
                setTotalPrice(sarahNumber, typeNum, dayNum);
                break;
            case R.id.send:
                getData();
                break;
            case R.id.ll_select_begain:
                showBottomDialog(true);
                break;
            case R.id.ll_select_end:
                showBottomDialog(false);
                break;
        }
    }

    private void setTotalPrice(int number, int n, long daynum) {
        price_number.setText("¥" + number * price * n * daynum);
    }

    private void showBottomDialog(final boolean isBegain) {

        BottomymdTimeDialog timeDialog = new BottomymdTimeDialog(this, R.style.dialog_style);
        timeDialog.show();
        timeDialog.setListener2(new BottomymdTimeDialog.OnConfirmClickListener() {
            @Override
            public void onConfirmClickListener(int i1, int i2, int i3, String i4, String i5) {
                String str = i1 + "-" + i2 + "-" + i3;
                if (isBegain) {
                    tvBegain.setText(str);
                    String eTime = tvEnd.getText().toString().trim();
                    if (!TextUtils.isEmpty(eTime)) {
                        getApartDay();
                        LogUtils.i("相隔的天数为:==="+dayNum);
                        setTotalPrice(sarahNumber, typeNum, dayNum);
                    }
                } else {
                    tvEnd.setText(str);
                    String sTime = tvBegain.getText().toString().trim();
                    if (!TextUtils.isEmpty(sTime)) {
                        getApartDay();
                        LogUtils.i("相隔的天数为:==="+dayNum);
                        setTotalPrice(sarahNumber, typeNum, dayNum);
                    }
                }
            }
        });

    }

    // 点击提交实现逻辑
    private void getData() {

        if (sarahNumber == 0) {
            ToastUtil.showToast(this, "工作餐份数为0");
            return;
        }
        if (!VerifyCheck.isMobilePhoneVerify(et_phone.getText().toString().trim())) {
            ToastUtil.showToast(this, "手机号信息不正确");
            return;
        }
        if (TextUtils.isEmpty(startTime)) {
            showToast("请选择开始时间");
            return;
        }
        if (TextUtils.isEmpty(endTime)) {
            showToast("请选择结束时间");
            return;
        }
        phoneNumber = et_phone.getText().toString().trim();
       /* String fixedmealtype = "";
        if (lunch.isChecked() && dinner.isChecked()) {
            fixedmealtype = "1,2";
        } else if (lunch.isChecked()) {
            fixedmealtype = "1";
        } else if (dinner.isChecked()) {
            fixedmealtype = "2";
        } else {
            ToastUtil.showToast(this, "请选择订餐类型");
            return;
        }*/

       // getApartDay(startTime, endTime);
        if (dayNum < 0) {
            showToast("选择的固定餐的结束日期要大于开始日期");
            return;
        }

    /*    int duration = 0;
        if (oneWeek.isChecked()) {
            duration = 1;
        } else if (twoWeek.isChecked()) {
            duration = 2;
        } else if (oneMouth.isChecked()) {
            duration = 3;
        }*/

        //getPresenter().getData(phoneNumber, sarahNumber, 2, fixedmealtype, duration);
    }

    private void getApartDay(/*String startTime, String endTime*/) {
        startTime = tvBegain.getText().toString().trim();
        endTime = tvEnd.getText().toString().trim();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        try {
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);
            //相隔的天数
            long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000)+1;
            if(day>=1){
                dayNum=day;
            }else{
                showToast("选择的订餐结束日期一定要大于开始日期");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
