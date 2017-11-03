package com.moe.wl.ui.main.activity.vegetable;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.base.BaseActivity;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.spfs.SharedPrefHelper;
import com.moe.wl.framework.utils.Arith;
import com.moe.wl.framework.widget.NoSlidingListView;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.main.activity.PayFiveJiaoActivity;
import com.moe.wl.ui.main.adapter.VegetableOrderAdapter;
import com.moe.wl.ui.main.bean.FoodBean;
import com.moe.wl.ui.main.bean.PayBean;
import com.moe.wl.ui.main.bean.VegetableBean;
import com.moe.wl.ui.main.model.ConfirmVegetableOrderModel;
import com.moe.wl.ui.main.modelimpl.ConfirmVegetableOrderModelImpl;
import com.moe.wl.ui.main.presenter.ConfirmVegetableOrderPresenter;
import com.moe.wl.ui.main.view.ConfirmVegetableOrderView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类描述：
 * 作者：Shixhe On 2017/9/18 0018
 */
public class ConfirmVegetableOrderActivity extends BaseActivity<ConfirmVegetableOrderModel, ConfirmVegetableOrderView, ConfirmVegetableOrderPresenter> implements ConfirmVegetableOrderView {

    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.list_view)
    NoSlidingListView listView;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.submit)
    Button submit;

    private VegetableOrderAdapter adapter;

    private double priceNum = 0;
    private int vegetableNum = 0;

    private int timeID = 0;
    private List<VegetableBean.PageEntity.ListEntity> data;
    private List<VegetableBean.PageEntity.ListEntity> list;

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_confirm_vegetable);
    }

    @Override
    public void initView() {
        titleBar.setTitle("确认订单");
        titleBar.setBack(true);
        timeID = getIntent().getIntExtra("TimeID", 0);
        if (SharedPrefHelper.getInstance().getRealName() == null || "".equals(SharedPrefHelper.getInstance().getRealName())) {
            userName.setText(SharedPrefHelper.getInstance().getNickname());
        } else {
            userName.setText(SharedPrefHelper.getInstance().getRealName());
        }
        tvTime.setText(getIntent().getStringExtra("Time"));
        phoneNumber.setText(getIntent().getStringExtra("PhoneNumber"));
        list = new ArrayList<>();
        data = (List<VegetableBean.PageEntity.ListEntity>) getIntent().getSerializableExtra("Data");
        for (int i = 0; i < data.size(); i++) {
            priceNum = Arith.add(priceNum, data.get(i).getPrice() * data.get(i).getNumber());
            vegetableNum += data.get(i).getNumber();
            if (data.get(i).getNumber() > 0) {
                list.add(data.get(i));
            }
        }
        DecimalFormat df = new DecimalFormat("###.00");
        price.setText("¥" + df.format(priceNum));
        submit.setText("去结算(" + vegetableNum + ")");
        adapter = new VegetableOrderAdapter(this, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void submitVegetableOrderSucc(PayBean bean) {
//        ToastUtil.showToast(this, "Type: " + bean.getOrdertype() + "  ID:" + bean.getOrdercode());
        // TODO 去支付
        Intent intent = new Intent(this, PayFiveJiaoActivity.class);
        intent.putExtra("from", Constants.VEGETABLE);
        intent.putExtra("pay", priceNum);
        intent.putExtra("orderid", "");
        intent.putExtra("ordercode", bean.getOrdercode());
        intent.putExtra("ordertype", bean.getOrdertype() + "");
        intent.putExtra("time",bean.getCreatetime());
        startActivity(intent);
    }

    @OnClick({R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submit:
                confirm();
                break;
        }
    }

    // 结算方法
    private void confirm() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("realname", SharedPrefHelper.getInstance().getRealName());
        map.put("mobile", SharedPrefHelper.getInstance().getPhoneNumber());
        map.put("getfoodtimeID", timeID);

        FoodBean[] listFood = new FoodBean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            FoodBean json = new FoodBean();
            json.setFoodid(list.get(i).getId());
            json.setCount(list.get(i).getNumber());
            listFood[i] = json;
        }
        map.put("foodList", listFood);
        getPresenter().ConfirmVegetableOrder(map);
    }

    @Override
    public ConfirmVegetableOrderModel createModel() {
        return new ConfirmVegetableOrderModelImpl();
    }

    @Override
    public ConfirmVegetableOrderPresenter createPresenter() {
        return new ConfirmVegetableOrderPresenter();
    }
}
