package com.moe.wl.ui.main.activity.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.moe.wl.R;
import com.moe.wl.framework.contant.Constants;
import com.moe.wl.framework.network.retrofit.RetrofitUtils;
import com.moe.wl.framework.utils.OtherUtils;
import com.moe.wl.framework.widget.CustomerDialog;
import com.moe.wl.framework.widget.TitleBar;
import com.moe.wl.ui.home.activity.MyBaseActivity;
import com.moe.wl.ui.main.activity.ordering.CancelOrderingActivity;
import com.moe.wl.ui.main.activity.property_maintenance.PropertyAintenanceActivity;
import com.moe.wl.ui.main.bean.CollectBean;
import com.moe.wl.ui.main.fragment.OrderRepairDetailOneFragment;
import com.moe.wl.ui.main.fragment.OrderRepairDetailTwoFragment;
import com.moe.wl.ui.mywidget.AlertDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mvp.cn.util.CallPhoneUtils;
import rx.Observable;
import rx.Subscriber;

/**
 * 类描述：报修订单详情页
 * 作者：Shixhe On 2017/10/17 0017
 */
public class OrderRepairDetailActivity extends MyBaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.right)
    TextView right;
    @BindView(R.id.left)
    TextView left;

    private int orderID;
    private String mobile;

    private int state;
    private CustomerDialog progressDialog;

    private OrderRepairDetailOneFragment fragment1;
    private OrderRepairDetailTwoFragment fragment2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_repair_detail);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        progressDialog = new CustomerDialog(this, R.style.dialog_style);
        orderID = getIntent().getIntExtra("OrderID", 0);
        state = getIntent().getIntExtra("State", 0);
        mobile = getIntent().getStringExtra("Mobile");
        titleBar.setBack(true);
        titleBar.setTitle("订单详情");

        Bundle bundle = new Bundle();
        bundle.putInt("OrderID", orderID);
        fragment1 = new OrderRepairDetailOneFragment();
        fragment1.setArguments(bundle);
        fragment2 = new OrderRepairDetailTwoFragment();
        fragment2.setArguments(bundle);
        addFragment(fragment1);
        addFragment(fragment2);
        hideFragment(fragment2);

        right.setVisibility(View.VISIBLE);
        switch (state) {
            case 1: // 1: 已下单
                right.setText("取消订单");
                break;
            case 2: // 2：服务中
                left.setVisibility(View.VISIBLE);
                right.setText("拨打电话");
                break;
            case 3: // 3：待评价
                right.setText("立即评价");
                break;
            case 4: // 4：已完成
                right.setText("再次预订");
                break;
            case 5: // 5：已取消
                right.setText("删除订单");
                break;
        }

    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame, fragment);
        transaction.commit();
    }

    private void hideFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commit();
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    @OnClick({R.id.left, R.id.right, R.id.one, R.id.two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right:
                switch (state) {
                    case 1:
                        showAlertDialog("是否取消订单");
                        break;
                    case 2:
                        showAlertDialog("是否拨打电话");
                        break;
                    case 3:
                        OtherUtils.gotoComment(OrderRepairDetailActivity.this, orderID, Constants.PROPERRY);
                        break;
                    case 4:
                        startActivity(new Intent(OrderRepairDetailActivity.this, PropertyAintenanceActivity.class));
                        break;
                    case 5:
                        showAlertDialog("是否删除订单");
                        break;
                }
                break;

            case R.id.left:
                // TODO 在线沟通
                break;

            case R.id.one:
                if (state == 1) {
                    return;
                }
                state = 1;
                one.setTextColor(getResources().getColor(R.color.blue));
                two.setTextColor(getResources().getColor(R.color.tv_black));
                showFragment(fragment1);
                hideFragment(fragment2);
                break;
            case R.id.two:
                if (state == 2) {
                    return;
                }
                state = 2;
                one.setTextColor(getResources().getColor(R.color.tv_black));
                two.setTextColor(getResources().getColor(R.color.blue));
                hideFragment(fragment1);
                showFragment(fragment2);
                break;
        }
    }

    private void showAlertDialog(String s) {
        new AlertDialog(this).builder()
                .setBigMsg(s)
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (state == 1) {
                            Intent intent = new Intent(OrderRepairDetailActivity.this, CancelOrderingActivity.class);
                            intent.putExtra("from", Constants.PROPERRY);
                            intent.putExtra("OrderingID", orderID);
                            startActivity(intent);
                        } else if (state == 2) {
                            // TODO 联系客服
                            CallPhoneUtils.callPhone(mobile, OrderRepairDetailActivity.this);
                        } else if (state == 5) {
                            // 删除订单
                            deleteOrder(orderID);
                        }
                    }
                })
                .setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
    }

    // 删除订单接口
    private void deleteOrder(int orderID) {
        Observable observable = RetrofitUtils.getInstance().deleteOrder(Constants.PROPERRY, orderID);
        showProgressDialog();
        observable.subscribe(new Subscriber<CollectBean>() {
            @Override
            public void onCompleted() {
                dismissProgressDialog();
            }

            @Override
            public void onError(Throwable e) {
                dismissProgressDialog();
            }

            @Override
            public void onNext(CollectBean orderBean) {
                if (orderBean.getErrCode() == 0) {
                    finish();
                }
            }
        });
    }

    private void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    private void showProgressDialog() {
        progressDialog.show();
    }
}
