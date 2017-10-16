package com.moe.wl.ui.main.activity.ordering;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 作者：Shixhe On 2017/10/16 0016
 */
public class FixedOrderActivity extends BaseActivity<OrderingModel, OrderingView, OrderingPresenter> implements OrderingView {

    @BindView(R.id.all_sp_comment_title)
    TitleBar titleBar;
    @BindView(R.id.price_number)
    TextView priceNumber;
    @BindView(R.id.send)
    Button send;
    @BindView(R.id.minus)
    ImageView minus;
    @BindView(R.id.sarah_number)
    TextView sarahNumber;
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
    CheckBox oneWeek;
    @BindView(R.id.two_week)
    CheckBox twoWeek;
    @BindView(R.id.one_mouth)
    CheckBox oneMouth;

    private String userName;
    private String phoneNumber;

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
    }

    @Override
    public void createOrderingSucc(CollectBean bean) {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
